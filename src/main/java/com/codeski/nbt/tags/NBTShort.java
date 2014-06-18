package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * A signed integral type that is 2 bytes in length.
 */
public class NBTShort extends NBT {
	private short payload;

	public NBTShort(String name, short payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = 2;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		return length;
	}

	@Override
	public Short getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return NBT.SHORT;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (Short) payload;
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		bytes.putShort(this.getPayload());
	}
}
