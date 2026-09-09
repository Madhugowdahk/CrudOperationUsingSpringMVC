package com.unibrain.Entity;

public enum GenderEnum {

	MALE('M', "Male"), FEMALE('F', "Female"),Transgender('T',"other");

	private Character genderCharValue;

	private String genderStringValue;

	private GenderEnum(Character genderCharValue, String genderStringValue) {
		this.genderCharValue = genderCharValue;
		this.genderStringValue = genderStringValue;
	}

	public Character getGenderCharValue() {
		return genderCharValue;
	}

	public String getGenderStringValue() {
		return genderStringValue;
	}

}
