package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

/**
 * A signed integral type that is 2 bytes in length.
 */
public final class NBTShort extends NBT<Short> {
	public NBTShort(String name, Short payload) {
		super(name, payload);
	}

	@Override
	public int getLength() {
		int length = 2;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
		return length;
	}

	@Override
	public byte getType() {
		return NBT.SHORT;
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		bytes.putShort(this.getPayload());
	}
}
