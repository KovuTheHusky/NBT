package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTByte extends NBT {
	public static final int LENGTH = 1;
	public static final byte TYPE = 1;
	private byte payload;

	public NBTByte(String name, byte payload) {
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
	public Byte getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (Byte) payload;
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.put(this.getPayload());
	}
}
