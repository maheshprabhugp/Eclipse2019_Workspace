package com.risksense.converters;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJSON2XmlApplicationTests {

	@Autowired
	private XMLJSONConverterI xmlJSONConverterITest;

	@Test
	public void contextLoads() {
		System.out.println("Testing");
	}

	@Test
	public void testNumberConversion() throws IOException {
		System.out.println("Nested Number Conversion");
		File numberJSONFile = ResourceUtils.getFile("classpath:json/Number.json");
		File numberXMLFile = ResourceUtils.getFile("classpath:workingcopy/Number.xml");
		xmlJSONConverterITest.convertJSONtoXML(numberJSONFile, numberXMLFile);

		File verifyNumberXMLFile = ResourceUtils.getFile("classpath:xml/Number.xml");
		assertTrue(readFile(verifyNumberXMLFile).equals(readFile(numberXMLFile)));
	}

	@Test
	public void testNestedNumberConversion() throws IOException {
		System.out.println("Nested Number Conversion");
		File nestedNumberJSONFile = ResourceUtils.getFile("classpath:json/NestedNumbers.json");
		File nestedNumberXMLFile = ResourceUtils.getFile("classpath:workingcopy/NestedNumbers.xml");
		xmlJSONConverterITest.convertJSONtoXML(nestedNumberJSONFile, nestedNumberXMLFile);

		File verifyNestedNumberXMLFile = ResourceUtils.getFile("classpath:xml/NestedNumbers.xml");
		assertTrue(readFile(verifyNestedNumberXMLFile).equals(readFile(nestedNumberXMLFile)));
	}

	@Test
	public void testBooleanConversion() throws IOException {
		System.out.println("Boolean Conversion");
		File booleanJSONFile = ResourceUtils.getFile("classpath:json/boolean.json");
		File booleanXMLFile = ResourceUtils.getFile("classpath:workingcopy/boolean.xml");
		xmlJSONConverterITest.convertJSONtoXML(booleanJSONFile, booleanXMLFile);

		File verifyBooleanXMLFile = ResourceUtils.getFile("classpath:xml/boolean.xml");
		assertTrue(readFile(verifyBooleanXMLFile).equals(readFile(booleanXMLFile)));
	}

	@Test
	public void testNestedBooleanConversion() throws IOException {
		System.out.println("Nested Boolean Conversion");
		File booleanJSONFile = ResourceUtils.getFile("classpath:json/NestedBoolean.json");
		File booleanXMLFile = ResourceUtils.getFile("classpath:workingcopy/NestedBoolean.xml");
		xmlJSONConverterITest.convertJSONtoXML(booleanJSONFile, booleanXMLFile);

		File verifyNestedBooleanXMLFile = ResourceUtils.getFile("classpath:xml/NestedBoolean.xml");
		assertTrue(readFile(verifyNestedBooleanXMLFile).equals(readFile(booleanXMLFile)));
	}

	@Test
	public void testNullConversion() throws IOException {
		System.out.println("Null Conversion");
		File nullJSONFile = ResourceUtils.getFile("classpath:json/Null.json");
		File nullXMLFile = ResourceUtils.getFile("classpath:workingcopy/Null.xml");
		xmlJSONConverterITest.convertJSONtoXML(nullJSONFile, nullXMLFile);

		File verifyNullXMLFile = ResourceUtils.getFile("classpath:xml/Null.xml");
		assertTrue(readFile(verifyNullXMLFile).equals(readFile(nullXMLFile)));
	}

	@Test
	public void testNestedNullConversion() throws IOException {
		System.out.println("Nested Null Conversion");
		File nestedNullJSONFile = ResourceUtils.getFile("classpath:json/NestedNull.json");
		File nestedNullXMLFile = ResourceUtils.getFile("classpath:workingcopy/NestedNull.xml");
		xmlJSONConverterITest.convertJSONtoXML(nestedNullJSONFile, nestedNullXMLFile);

		File verifyNestedNullXMLFile = ResourceUtils.getFile("classpath:xml/NestedNull.xml");
		assertTrue(readFile(verifyNestedNullXMLFile).equals(readFile(nestedNullXMLFile)));
	}

	@Test
	public void testStringConversion() throws IOException {
		System.out.println("String Conversion");
		File stringJSONFile = ResourceUtils.getFile("classpath:json/String.json");
		File stringXMLFile = ResourceUtils.getFile("classpath:workingcopy/String.xml");
		xmlJSONConverterITest.convertJSONtoXML(stringJSONFile, stringXMLFile);

		File verifyStringXMLFile = ResourceUtils.getFile("classpath:xml/String.xml");
		assertTrue(readFile(verifyStringXMLFile).equals(readFile(stringXMLFile)));
	}

	@Test
	public void testNestedStringConversion() throws IOException {
		System.out.println("Nested String Conversion");
		File nestedStringJSONFile = ResourceUtils.getFile("classpath:json/NestedString.json");
		File nestedStringXMLFile = ResourceUtils.getFile("classpath:workingcopy/NestedString.xml");
		xmlJSONConverterITest.convertJSONtoXML(nestedStringJSONFile, nestedStringXMLFile);

		File verifyStringXMLFile = ResourceUtils.getFile("classpath:xml/NestedString.xml");
		assertTrue(readFile(verifyStringXMLFile).equals(readFile(nestedStringXMLFile)));
	}

	@Test
	public void testArrayConversion() throws IOException {
		System.out.println("Array Conversion");
		File arrayJSONFile = ResourceUtils.getFile("classpath:json/Array.json");
		File arrayXMLFile = ResourceUtils.getFile("classpath:workingcopy/Array.xml");
		xmlJSONConverterITest.convertJSONtoXML(arrayJSONFile, arrayXMLFile);

		File verifyArrayXMLFile = ResourceUtils.getFile("classpath:xml/Array.xml");
		assertTrue(readFile(verifyArrayXMLFile).equals(readFile(arrayXMLFile)));
	}

	@Test
	public void testNestedArrayConversion() throws IOException {
		System.out.println("Nested Array Conversion");
		File nestedArrayJSONFile = ResourceUtils.getFile("classpath:json/nestedArray.json");
		File nestedArrayXMLFile = ResourceUtils.getFile("classpath:workingcopy/nestedArray.xml");
		xmlJSONConverterITest.convertJSONtoXML(nestedArrayJSONFile, nestedArrayXMLFile);

		File nestedVerifyArrayXMLFile = ResourceUtils.getFile("classpath:xml/nestedArray.xml");
		assertTrue(readFile(nestedVerifyArrayXMLFile).equals(readFile(nestedArrayXMLFile)));
	}

	@Test
	public void testExampleConversion() throws IOException {
		System.out.println("Example Conversion");
		File exampleJSONFile = ResourceUtils.getFile("classpath:json/example.json");
		File exampleXMLFile = ResourceUtils.getFile("classpath:workingcopy/example.xml");
		xmlJSONConverterITest.convertJSONtoXML(exampleJSONFile, exampleXMLFile);

		File exampleVerifyXMLFile = ResourceUtils.getFile("classpath:xml/example.xml");
		assertTrue(readFile(exampleVerifyXMLFile).equals(readFile(exampleXMLFile)));
	}

	@Test
	public void testObjectConversion() throws IOException {
		System.out.println("Object Conversion");
		File objectJSONFile = ResourceUtils.getFile("classpath:json/objects.json");
		File objectXMLFile = ResourceUtils.getFile("classpath:workingcopy/objects.xml");
		xmlJSONConverterITest.convertJSONtoXML(objectJSONFile, objectXMLFile);

		File objectVerifyXMLFile = ResourceUtils.getFile("classpath:xml/objects.xml");
		assertTrue(readFile(objectVerifyXMLFile).equals(readFile(objectXMLFile)));
	}

	private String readFile(File file) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}
		return stringBuilder.toString();
	}
}