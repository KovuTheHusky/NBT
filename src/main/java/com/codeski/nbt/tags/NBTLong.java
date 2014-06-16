package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTLong extends NBT {
	private Long payload;

	public NBTLong(String name, long payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Long getPayload() {
		return payload;
	}

	public void setPayload(long payload) {
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
		ByteBuffer bb = ByteBuffer.allocate(1 + bytesForName + 8);
		bb.put((byte) 0x4);
		if (this.name != null) {
			bb.putShort(length);
			bb.put(name);
		}
		bb.putLong(payload);
		return bb.array();
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Long] " + name + ": " + payload;
		else
			return "[Long] null: " + payload;
	}
}
