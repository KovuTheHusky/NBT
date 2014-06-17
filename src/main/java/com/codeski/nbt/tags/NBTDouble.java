package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTDouble extends NBT {
	public static final byte LENGTH = 8;
	public static final byte TYPE = 6;
	private Double payload;

	public NBTDouble(String name, double payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = LENGTH;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		return length;
	}

	@Override
	public Double getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	public void setPayload(double payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Double] " + name + ": " + payload;
		else
			return "[Double] null: " + payload;
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.putDouble(this.getPayload());
	}
}
