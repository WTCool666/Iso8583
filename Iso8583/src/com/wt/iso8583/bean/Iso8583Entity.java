package com.wt.iso8583.bean;


public class Iso8583Entity {
	private Integer length;//�򳤶�
	private LengthType lengthType;//�򳤶�����
	private EncodeType encodeType;
	public LengthType getLengthType() {
		return lengthType;
	}
	public EncodeType getEncodeType() {
		return encodeType;
	}
	public Integer getLength() {
		return length;
	}
	public Iso8583Entity(Integer length,EncodeType encodeType, LengthType lengthType) {
		super();
		this.length = length;
		this.lengthType = lengthType;
		this.encodeType = encodeType;
	}
}

