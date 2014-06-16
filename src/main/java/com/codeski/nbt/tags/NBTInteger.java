package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTInteger extends NBT {
	private Integer payload;
	private final byte type = 3;

	public NBTInteger(String name, int payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Integer getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return type;
	}

	public void setPayload(int payload) {
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
		ByteBuffer bb = ByteBuffer.allocate(bytesForName + 4);
		if (this.name != null) {
			bb.put((byte) 0x3);
			bb.putShort(length);
			bb.put(name);
		}
		bb.putInt(payload);
		return bb.array();
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Integer] " + name + ": " + payload;
		else
			return "[Integer] null: " + payload;
	}
}
