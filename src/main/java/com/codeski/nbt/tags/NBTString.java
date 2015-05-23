package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

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
		int length = new NBTShort(null, (short) 0).getLength() + this.getPayload().getBytes(NBT.CHARSET).length;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
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
		byte[] name = this.getPayload().getBytes(NBT.CHARSET);
		bytes.putShort((short) name.length);
		bytes.put(name);
	}
}
