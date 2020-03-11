package com.wt.iso8583.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteUtils {
	
	public static int bytesToInt(byte[] bytes) {
		return Integer.parseInt(Converts.BCD2ASC(bytes),10);
	}
	
	public static String getHexStr(byte[] b) {
		StringBuilder builder = new StringBuilder();
		ByteArrayInputStream input = new ByteArrayInputStream(b);
		int i = 0;
		while ((i = input.read()) != -1) {
			builder.append( (Integer.toHexString(i).length() == 2) ? (Integer
					.toHexString(i)) : (0 + Integer.toHexString(i)));
		}
		return builder.toString();
	}
	
	public static byte[] getByteByNoSpli(String ss) {
		byte[] arrB = ss.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String stdfrTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(stdfrTmp, 16);
		}
		return arrOut;
	}
	
	public static byte[] getByte(String ss) {
		String[] shil = ss.split(" ");
		StringBuilder er = new StringBuilder();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (String string : shil) {
			if ("".equals(string)) {
				continue;
			}
			int i = Integer.valueOf(string, 16);
			out.write(i);
		}
		return out.toByteArray();
	}
	
	public static byte[] subArray(byte[] array, int start, int len) {
		ByteArrayInputStream input = new ByteArrayInputStream(array);
		if (len == -1) {
			len = array.length - start;
		}
		byte[] result = new byte[len];
		input.read(result, 0, start);
		input.read(result, 0, len);
		return result;
	}
	
	public static String getHexStrNoSpli(byte[] b) {
		StringBuilder builder = new StringBuilder();
		ByteArrayInputStream input = new ByteArrayInputStream(b);
		int i = 0;
		while ((i = input.read()) != -1) {
			builder.append(Integer.toHexString(i).length() == 2 ? Integer
					.toHexString(i) : 0 + Integer.toHexString(i));
		}
		return builder.toString();
	}

}
