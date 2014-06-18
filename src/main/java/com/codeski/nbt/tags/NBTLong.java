package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * A signed integral type that is 8 bytes in length.
 */
public class NBTLong extends NBT {
	private long payload;

	public NBTLong(String name, long payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = 8;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		return length;
	}

	@Override
	public Long getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return NBT.LONG;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (Long) payload;
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		bytes.putLong(this.getPayload());
	}
}
