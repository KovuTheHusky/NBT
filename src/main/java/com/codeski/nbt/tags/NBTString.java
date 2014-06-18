package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * A UTF-8 string; it has a size, rather than being null terminated.
 */
public class NBTString extends NBT {
	private String payload;

	public NBTString(String name, String payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = new NBTShort(null, (short) 0).getLength() + this.getPayload().getBytes(Charset.forName("UTF-8")).length; // Make static getLength() and possibly rename instanced to getSize().
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
		return NBT.STRING;
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
	protected void writePayload(ByteBuffer bytes) {
		byte[] name = this.getPayload().getBytes(Charset.forName("UTF-8"));
		bytes.putShort((short) name.length);
		bytes.put(name);
	}
}
