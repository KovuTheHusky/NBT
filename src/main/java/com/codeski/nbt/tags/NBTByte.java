package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

/**
 * A signed integral type that is 1 byte in length. Sometimes used for booleans.
 */
public class NBTByte extends NBT<Byte> {
	public NBTByte(String name, Byte payload) {
		super(name, payload);
	}

	@Override
	public int getLength() {
		int length = 1;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
		return length;
	}

	@Override
	public byte getType() {
		return NBT.BYTE;
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		bytes.put(this.getPayload());
	}
}
