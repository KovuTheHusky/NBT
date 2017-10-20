package com.kovuthehusky.nbt.regions;

import java.util.List;

import com.kovuthehusky.nbt.tags.NBTCompound;

public class MCARegion {
    private int[] offsets;
    private byte[] lengths;
    private int[] timestamps;
    private List<NBTCompound> chunks;

    public MCARegion(int[] offsets, byte[] lengths, int[] timestamps, List<NBTCompound> chunks) {
        this.offsets = offsets;
        this.lengths = lengths;
        this.timestamps = timestamps;
        this.chunks = chunks;
    }

    public int[] getOffsets() {
        return offsets;
    }

    public void setOffsets(int[] offsets) {
        this.offsets = offsets;
    }

    public byte[] getLengths() {
        return lengths;
    }

    public void setLengths(byte[] lengths) {
        this.lengths = lengths;
    }

    public int[] getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(int[] timestamps) {
        this.timestamps = timestamps;
    }

    public List<NBTCompound> getChunks() {
        return chunks;
    }

    public void setChunks(List<NBTCompound> chunks) {
        this.chunks = chunks;
    }
}
