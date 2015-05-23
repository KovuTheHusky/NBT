package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

/**
 * A signed integral type that is 4 bytes in length.
 */
public class NBTInteger extends NBT {
	private int payload;

	public NBTInteger(String name, int payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = 4;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
		return length;
	}

	@Override
	public Integer getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return NBT.INTEGER;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (Integer) payload;
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		bytes.putInt(this.getPayload());
	}
}
