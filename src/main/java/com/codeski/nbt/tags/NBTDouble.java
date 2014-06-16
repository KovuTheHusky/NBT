package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTDouble extends NBT {
	private Double payload;
	private final byte type = 6;

	public NBTDouble(String name, double payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Double getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return type;
	}

	public void setPayload(double payload) {
		this.payload = payload;
	}

	@Override
	public byte[] toNBT() {
		int bytesForName = 0;
		byte[] name = null;
		short length = 0;
		if (this.name != null) {
			name = this.name.getBytes(Charset.forName("UTF-8"));
			length = (short) name.length;
			bytesForName = 1 + 2 + length;
		}
		ByteBuffer bb = ByteBuffer.allocate(bytesForName + 8);
		if (this.name != null) {
			bb.put((byte) 0x6);
			bb.putShort(length);
			bb.put(name);
		}
		bb.putDouble(payload);
		return bb.array();
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Double] " + name + ": " + payload;
		else
			return "[Double] null: " + payload;
	}
}
