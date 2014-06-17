package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTInteger extends NBT {
	public static final byte LENGTH = 4;
	public static final byte TYPE = 3;
	private Integer payload;

	public NBTInteger(String name, int payload) {
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
	public Integer getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	public void setPayload(int payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Integer] " + name + ": " + payload;
		else
			return "[Integer] null: " + payload;
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.putInt(this.getPayload());
	}
}
