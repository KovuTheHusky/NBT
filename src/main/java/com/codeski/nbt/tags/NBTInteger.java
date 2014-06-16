package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTInteger extends NBT {
	private Integer payload;

	public NBTInteger(String name, int payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Integer getPayload() {
		return payload;
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
			bytesForName = 2 + length;
		}
		ByteBuffer bb = ByteBuffer.allocate(1 + bytesForName + 4);
		bb.put((byte) 0x3);
		if (this.name != null) {
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
