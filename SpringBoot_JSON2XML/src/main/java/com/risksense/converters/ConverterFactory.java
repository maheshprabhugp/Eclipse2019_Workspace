package com.risksense.converters;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * Factory class for creating instances of {@link XMLJSONConverterI}.
 */
@Component
public final class ConverterFactory {

	@Autowired
	private XMLJSONConverterI xmljsonConverterI;
	private static XMLJSONConverterI xmljsonConverterIStatic;

	@PostConstruct
	public void init() {
		xmljsonConverterIStatic = this.xmljsonConverterI;
	}

	/**
	 * You should implement this method having it return your version of
	 * {@link com.risksense.converters.XMLJSONConverterI}.
	 *
	 * @return {@link com.risksense.converters.XMLJSONConverterI} implementation you
	 *         created.
	 */
	public static final XMLJSONConverterI createXMLJSONConverter() {

		try {
			File xmlFile = ResourceUtils.getFile("classpath:workingcopy/example.xml");
			File jsonFile = ResourceUtils.getFile("classpath:json/example.json");
			xmljsonConverterIStatic.convertJSONtoXML(jsonFile, xmlFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmljsonConverterIStatic;
		// throw new UnsupportedOperationException("Please implement me!");
	}

}
