package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

/**
 * Used to mark the end of compound tags. This tag does not have a name, so it is only ever a single byte 0.
 */
public final class NBTEnd extends NBT<Byte> {
    public NBTEnd() {
        super(null, (byte) 0);
    }

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public byte getType() {
        return NBT.END;
    }

    @Override
    public void setPayload(Byte payload) {
    }

    @Override
    protected void writePayload(ByteBuffer bytes) {
        bytes.put(this.getPayload());
    }
}
