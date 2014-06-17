package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTFloat extends NBT {
	public static final byte LENGTH = 4;
	public static final byte TYPE = 5;
	private Float payload;

	public NBTFloat(String name, float payload) {
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
	public Float getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	public void setPayload(float payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Float] " + name + ": " + payload;
		else
			return "[Float] null: " + payload;
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.putFloat(this.getPayload());
	}
}
