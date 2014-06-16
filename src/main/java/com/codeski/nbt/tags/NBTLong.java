package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTLong extends NBT {
	private Long payload;
	private final byte type = 4;

	public NBTLong(String name, long payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Long getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return type;
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
			bytesForName = 1 + 2 + length;
		}
		ByteBuffer bb = ByteBuffer.allocate(bytesForName + 8);
		if (this.name != null) {
			bb.put((byte) 0x4);
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
