package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

/**
 * A UTF-8 string; it has a size, rather than being null terminated.
 */
public class NBTString extends NBT<String> {
	public NBTString(String name, String payload) {
		super(name, payload);
	}

	@Override
	public int getLength() {
		int length = new NBTShort(null, (short) 0).getLength() + this.getPayload().getBytes(NBT.CHARSET).length;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
		return length;
	}

	@Override
	public byte getType() {
		return NBT.STRING;
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
