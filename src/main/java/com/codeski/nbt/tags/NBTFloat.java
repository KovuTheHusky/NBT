package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTFloat extends NBT {
	private Float payload;

	public NBTFloat(String name, float payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Float getPayload() {
		return payload;
	}

	public void setPayload(float payload) {
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
			bb.put((byte) 0x5);
			bb.putShort(length);
			bb.put(name);
		}
		bb.putFloat(payload);
		return bb.array();
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Float] " + name + ": " + payload;
		else
			return "[Float] null: " + payload;
	}
}
