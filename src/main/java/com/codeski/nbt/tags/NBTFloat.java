package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * A signed floating point type that is 4 bytes in length.
 */
public class NBTFloat extends NBT {
	private float payload;

	public NBTFloat(String name, float payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = 4;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		return length;
	}

	@Override
	public Float getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return NBT.FLOAT;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (Float) payload;
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		bytes.putFloat(this.getPayload());
	}
}
