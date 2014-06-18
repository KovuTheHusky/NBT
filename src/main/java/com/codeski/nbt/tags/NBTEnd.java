package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

/**
 * Used to mark the end of compound tags. This tag does not have a name, so it is only ever a single byte 0.
 */
public class NBTEnd extends NBT {
	public NBTEnd() {
		super(null);
	}

	@Override
	public int getLength() {
		return 1;
	}

	@Override
	public Byte getPayload() {
		return 0;
	}

	@Override
	public byte getType() {
		return NBT.END;
	}

	@Override
	public void setPayload(Object payload) {
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		bytes.put(this.getPayload());
	}
}
