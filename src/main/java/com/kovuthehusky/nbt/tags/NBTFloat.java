package com.kovuthehusky.nbt.tags;

import java.nio.ByteBuffer;

/**
 * A signed floating point type that is 4 bytes in length.
 */
public final class NBTFloat extends NBT<Float> {
    public NBTFloat(String name, Float payload) {
        super(name, payload);
    }

    @Override
    public int getLength() {
        int length = 4;
        if (this.getName() != null)
            length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
        return length;
    }

    @Override
    public byte getType() {
        return NBT.FLOAT;
    }

    @Override
    protected void writePayload(ByteBuffer bytes) {
        bytes.putFloat(this.getPayload());
    }
}
