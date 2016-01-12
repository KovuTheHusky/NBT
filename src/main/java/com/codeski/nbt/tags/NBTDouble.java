package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

/**
 * A signed floating point type that is 8 bytes in length.
 */
public final class NBTDouble extends NBT<Double> {
    public NBTDouble(String name, Double payload) {
        super(name, payload);
    }

    @Override
    public int getLength() {
        int length = 8;
        if (this.getName() != null)
            length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
        return length;
    }

    @Override
    public byte getType() {
        return NBT.DOUBLE;
    }

    @Override
    protected void writePayload(ByteBuffer bytes) {
        bytes.putDouble(this.getPayload());
    }
}
