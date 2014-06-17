package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTFloat extends NBT {
	public static final byte LENGTH = 4;
	public static final byte TYPE = 5;
	private float payload;

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

	@Override
	public void setPayload(Object payload) {
		this.payload = (Float) payload;
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.putFloat(this.getPayload());
	}
}
