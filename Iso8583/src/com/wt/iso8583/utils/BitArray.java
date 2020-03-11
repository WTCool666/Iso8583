package com.wt.iso8583.utils;

public class BitArray {
	
	//位图数据
	private byte[] repn;
	private int length;

	private static final int BITS_PER_UNIT = 8;

	/**
	 * 得到当前索引的 位图索引
	 * */
	private static int subscript(int idx) {
		return idx / BITS_PER_UNIT;
	}

	 // bits big-endian in each unit
	private static int position(int idx) {
		return 1 << (BITS_PER_UNIT - 1 - (idx % BITS_PER_UNIT));
	}
	
	/**
	 * Copy constructor (for cloning).
	 */
	private BitArray(BitArray ba) {
		length = ba.length;
		repn = ba.repn.clone();
	}

	
	/**
	 * Creates a BitArray of the specified size, initialized to zeros.
	 */
	public BitArray(int length) throws IllegalArgumentException {
		if (length < 0) {
			throw new IllegalArgumentException("Negative length for BitArray");
		}

		this.length = length;

		repn = new byte[(length + BITS_PER_UNIT - 1) / BITS_PER_UNIT];
	}
	
	/**
	 * Creates a BitArray of the specified size, initialized from the specified
	 * byte array. The most significant bit of a[0] gets index zero in the
	 * BitArray. The array a must be large enough to specify a value for every
	 * bit in the BitArray. In other words, 8*a.length <= length.
	 */
	public BitArray(int length, byte[] a) throws IllegalArgumentException {
		if (length < 0) {
			throw new IllegalArgumentException("Negative length for BitArray");
		}
		if (a.length * BITS_PER_UNIT < length) {
			throw new IllegalArgumentException("Byte array too short to represent " + "bit array of given length");
		}

		this.length = length;

		int repLength = ((length + BITS_PER_UNIT - 1) / BITS_PER_UNIT);
		int unusedBits = repLength * BITS_PER_UNIT - length;
		byte bitMask = (byte) (0xFF << unusedBits);

		/*
		 * normalize the representation: 1. discard extra bytes 2. zero out
		 * extra bits in the last byte
		 */
		repn = new byte[repLength];
		System.arraycopy(a, 0, repn, 0, repLength);
		if (repLength > 0) {
			repn[repLength - 1] &= bitMask;
		}
	}
	
	/**
	 * Returns the indexed bit in this BitArray.
	 */
	public boolean get(int index) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= length) {
			throw new ArrayIndexOutOfBoundsException(Integer.toString(index));
		}
		return (repn[subscript(index)] & position(index)) != 0;
	}

	/**
	 * Sets the indexed bit in this BitArray.
	 */
	public void set(int index, boolean value) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= length) {
			throw new ArrayIndexOutOfBoundsException(Integer.toString(index));
		}
		int idx = subscript(index);
		int bit = position(index);

		if (value) {
			repn[idx] |= bit;
		} else {
			repn[idx] &= ~bit;
		}
	}
	
	/**
	 * Returns a Byte array containing the contents of this BitArray. The bit
	 * stored at index zero in this BitArray will be copied into the most
	 * significant bit of the zeroth element of the returned byte array. The
	 * last byte of the returned byte array will be contain zeros in any bits
	 * that do not have corresponding bits in the BitArray. (This matters only
	 * if the BitArray's size is not a multiple of 8.)
	 */
	public byte[] toByteArray() {
		return repn.clone();
	}
	
	public Object clone() {
		return new BitArray(this);
	}

}
