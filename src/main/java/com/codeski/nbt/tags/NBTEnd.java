package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

public class NBTEnd extends NBT {
	public static final byte LENGTH = 1;
	public static final byte TYPE = 0;
	private final byte payload = 0;

	public NBTEnd() {
		super(null);
	}

	@Override
	public int getLength() {
		return LENGTH;
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
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.put(this.getPayload());
	}
}
