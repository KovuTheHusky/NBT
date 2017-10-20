package com.kovuthehusky.nbt.tags;

import java.nio.ByteBuffer;

/**
 * A signed integral type that is 8 bytes in length.
 */
public final class NBTLong extends NBT<Long> {
    public NBTLong(String name, Long payload) {
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
        return NBT.LONG;
    }

    @Override
    protected void writePayload(ByteBuffer bytes) {
        bytes.putLong(this.getPayload());
    }
}
