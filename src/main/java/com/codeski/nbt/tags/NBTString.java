package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTString extends NBT {
	public static final byte TYPE = 8;
	private String payload;

	public NBTString(String name, String payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = NBTShort.LENGTH + this.getPayload().getBytes(Charset.forName("UTF-8")).length;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		return length;
	}

	@Override
	public String getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (String) payload;
	}

	@Override
	public String toString() {
		if (this.getName() != null)
			return this.getClass().getSimpleName() + " Name:\"" + this.getName() + "\" Payload:\"" + this.getPayload() + "\"";
		else
			return this.getClass().getSimpleName() + " Payload:\"" + this.getPayload() + "\"";
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		byte[] name = this.getPayload().getBytes(Charset.forName("UTF-8"));
		bytes.putShort((short) name.length);
		bytes.put(name);
	}
}
