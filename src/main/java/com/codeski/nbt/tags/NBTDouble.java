package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

/**
 * A signed floating point type that is 8 bytes in length.
 */
public class NBTDouble extends NBT {
	private double payload;

	public NBTDouble(String name, double payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = 8;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
		return length;
	}

	@Override
	public Double getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return NBT.DOUBLE;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (Double) payload;
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		bytes.putDouble(this.getPayload());
	}
}
