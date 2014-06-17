package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTLong extends NBT {
	public static final byte LENGTH = 8;
	public static final byte TYPE = 4;
	private Long payload;

	public NBTLong(String name, long payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = LENGTH;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		// System.out.println(length + " of which " + LENGTH + " is payload.");
		return length;
	}

	@Override
	public Long getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	public void setPayload(long payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Long] " + name + ": " + payload;
		else
			return "[Long] null: " + payload;
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.putLong(this.getPayload());
	}
}
