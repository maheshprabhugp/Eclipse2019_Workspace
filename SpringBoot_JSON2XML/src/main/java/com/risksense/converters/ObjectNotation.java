package com.risksense.converters;

public class ObjectNotation {

	private String dataType;
	private String fieldName;
	private String fieldValue;
	private ObjectNotation objectNotation;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public ObjectNotation getObjectNotation() {
		return objectNotation;
	}

	public void setObjectNotation(ObjectNotation objectNotation) {
		this.objectNotation = objectNotation;
	}
}
