package com.risksense.converters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class XMLJSONConverter implements XMLJSONConverterI {

	@Override
	public void convertJSONtoXML(File json, File xml) throws IOException {

		String xmlMessage = unmarshalJSON(json);
		writeFile(xml, xmlMessage);

	}

	private void writeFile(File xml, String xmlMessage) throws IOException {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(xml))) {
			bufferedWriter.write(xmlMessage);
			bufferedWriter.flush();
		}
	}

	private String unmarshalJSON(File json) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(json))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(parsonJSON(line));
			}
		}
		return stringBuilder.toString();
	}

	// TODO: Factory Pattern
	private String parsonJSON(String line) {

		String JSONType = analyzeJSONLine(line);
		switch (JSONType) {
		case "openCurlyBracket":
			return openCurlyBracketType();
		case "closeCurlyBracket":
			return closeCurlyBracketType();
		case "number":
			return numberType(line.trim());
		case "nestedNumber":
			return nestedNumbersType(line.trim());
		case "booleanTrue":
			return booleanTypeTrue();
		case "booleanFalse":
			return booleanTypeFalse();
		case "nestedBooleanTrue":
			return nestedBooleanTypeTrue(line);
		case "nestedBooleanFalse":
			return nestedBooleanTypeFalse(line);
		case "null":
			return nullType();
		case "nestedNull":
			return nestedNullType(line);
		case "string":
			return stringType(line);
		case "nestedString":
			return nestedStringType(line);
		case "array":
			return arrayType(line);
		case "nestedArray":
			return nestedArrayType(line);
		case "nestedObject":
			return nestedObjectType(line);
		case "multiNestedArray":
			return multiNestedArrayType(line);
		}
		throw new RuntimeException("Parsing failed for: ".concat(line));
	}

	private String analyzeJSONLine(String line) {
		if (line.equals("{")) {
			return "openCurlyBracket";
		} else if (line.equals("}") || line.endsWith("},")) {
			return "closeCurlyBracket";
		} else if (NumberUtils.isCreatable(line.trim())) {
			return "number";
		} else if (NumberUtils.isCreatable(line.replaceAll("[*a-zA-Z:_,\" ]", ""))) {
			return "nestedNumber";
		} else if (line.trim().equals("true")) {
			return "booleanTrue";
		} else if (line.trim().equals("false")) {
			return "booleanFalse";
		} else if (line.contains("true") && !line.contains("[")) {
			return "nestedBooleanTrue";
		} else if (line.contains("false") && !line.contains("[")) {
			return "nestedBooleanFalse";
		} else if (line.trim().equals("null")) {
			return "null";
		} else if (line.contains("null") && !line.contains("[")) {
			return "nestedNull";
		} else if (line.replaceAll("[*a-zA-Z ]", "").equals("\"\"")) {
			return "string";
		} else if (line.replaceAll("[*a-zA-Z:, ]", "").equals("\"\"\"\"")) {
			return "nestedString";
		} else if (line.trim().startsWith("[") && line.trim().endsWith("]")) {
			return "array";
		} else if (line.replaceAll("[*a-zA-Z0-9,_\" ]", "").equals(":[]")) {
			return "nestedArray";
		} else if (line.contains(":") && (line.trim().endsWith("{"))) {
			return "nestedObject";
		} else if (line.replaceAll("[*a-zA-Z0-9,_\" ]", "").contains(":")) {
			return "multiNestedArray";
		}

		throw new RuntimeException("Parsing failed for: ".concat(line));
	}

	private String openCurlyBracketType() {
		return "<object>";
	}

	private String closeCurlyBracketType() {
		return "</object>";
	}

	private String numberType(String line) {
		return "<number>".concat(line).concat("</number>");
	}

	private String nestedNumbersType(String line) {
		return "<number name=\"".concat(StringUtils.substringBetween(line, "\"", "\"")).concat("\">")
				.concat(line.replaceAll("[*a-zA-Z:_,\" ]", "")).concat("</number>");
	}

	private String booleanTypeTrue() {
		return "<boolean>true</boolean>";
	}

	private String booleanTypeFalse() {
		return "<boolean>false</boolean>";
	}

	private String nestedBooleanTypeTrue(String line) {
		return "<boolean name=\"".concat(StringUtils.substringBetween(line, "\"", "\"").concat("\">true</boolean>"));
	}

	private String nestedBooleanTypeFalse(String line) {
		return "<boolean name=\"".concat(StringUtils.substringBetween(line, "\"", "\"").concat("\">false</boolean>"));
	}

	private String nullType() {
		return "<null/>";
	}

	private String nestedNullType(String line) {
		return "<null name=".concat(StringUtils.substringBefore(line, ":").trim()).concat("/>");
	}

	private String stringType(String line) {
		return "<string>".concat(line.replace("\"", "")).concat("</string>");
	}

	private String nestedStringType(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line, ":");
		Assert.isTrue(tokenizer.hasMoreElements(), "Nested String must have value. Check ".concat(line));
		String firsttoken = "<string name=".concat(tokenizer.nextToken().trim()).concat(">");
		Assert.isTrue(tokenizer.hasMoreElements(), "Nested String must have value. Check ".concat(line));
		return firsttoken
				.concat(StringUtils.substringBetween(tokenizer.nextToken().trim(), "\"", "\"").concat("</string>"));
	}

	private String arrayType(String line) {
		StringBuilder builder = new StringBuilder("<array>");
		StringTokenizer tokenizer = new StringTokenizer(StringUtils.substringBetween(line, "[", "]"), ",");
		return builder.append(parseJSONArray(tokenizer)).append("</array>").toString();
	}

	private String nestedArrayType(String line) {
		StringBuilder builder = new StringBuilder("<array name=");
		builder.append(StringUtils.substringBefore(line, ":").trim()).append(">");
		String arrayValues = StringUtils.substringAfter(line, ":").trim();
		StringTokenizer tokenizer = new StringTokenizer(StringUtils.substringBetween(arrayValues, "[", "]"), ",");
		return builder.append(parseJSONArray(tokenizer)).append("</array>").toString();
	}

	private String nestedObjectType(String line) {
		return "<object name=".concat(StringUtils.substringBefore(line, ":").trim()).concat(">");
	}

	private String multiNestedArrayType(String line) {
		StringBuilder builder = new StringBuilder("<array name=");
		builder.append(StringUtils.substringBefore(line, ":").trim()).append(">");
		String arrayValues = StringUtils.substringAfter(line, ":").trim();
		arrayValues = StringUtils.removeEnd(RegExUtils.removeFirst(arrayValues, Pattern.compile(".?")), "]");
		// TODO: Implement project specific tokenizer
		arrayValues = arrayValues.replaceAll("\",\"", "\"--\"");
		StringTokenizer tokenizer = new StringTokenizer(arrayValues, ",");
		return builder.append(parseJSONArray(tokenizer)).append("</array>").toString();

	}

	// Nested method calls
	private String parseJSONArray(StringTokenizer tokenizer) {
		StringBuilder builder = new StringBuilder();
		while (tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken().trim();
			if (token.startsWith("{") && token.endsWith("}") && token.contains("--")) {
				token = token.replace("--", ",");
				builder.append(parsonJSON("{"));
				token = StringUtils.removeEnd(RegExUtils.removeFirst(token, Pattern.compile(".?")), "}");
				StringTokenizer tokenizerNested = new StringTokenizer(token, ",");
				builder.append(parseJSONArray(tokenizerNested));
				builder.append(parsonJSON("}"));
			} else if (token.startsWith("{") && token.endsWith("}")) {
				builder.append(parsonJSON("{"));
				token = StringUtils.removeEnd(RegExUtils.removeFirst(token, Pattern.compile(".?")), "}");
				builder.append(parsonJSON(token));
				builder.append(parsonJSON("}"));
			} else {
				builder.append(parsonJSON(token));
			}
		}
		return builder.toString();

	}
}