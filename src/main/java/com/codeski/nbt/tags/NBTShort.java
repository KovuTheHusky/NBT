package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTShort extends NBT {
	public static final byte LENGTH = 2;
	public static final byte TYPE = 2;
	private Short payload;

	public NBTShort(String name, short payload) {
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
	public Short getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	public void setPayload(short payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Short] " + name + ": " + payload;
		else
			return "[Short] null: " + payload;
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.putShort(this.getPayload());
	}
}
