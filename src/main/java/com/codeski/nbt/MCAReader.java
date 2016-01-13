package com.codeski.nbt;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.codeski.nbt.regions.MCARegion;
import com.codeski.nbt.tags.NBTCompound;

public class MCAReader {
    private File file;
    private DataInputStream in;

    private MCAReader(File file) {
        this.file = file;
        // Detect whether or not this file is compressed
        int magic = 0;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            magic = raf.read() & 0xff | raf.read() << 8 & 0xff00;
        } catch (IOException e) {
            System.err.println("There was an error detecting if the MCA file is compressed.");
            e.printStackTrace(System.err);
        }
        // Set up the data input stream for reading MCA data
        try {
            if (magic == GZIPInputStream.GZIP_MAGIC)
                in = new DataInputStream(new GZIPInputStream(new FileInputStream(file)));
            else
                in = new DataInputStream(new FileInputStream(file));
        } catch (IOException e) {
            System.err.println("There was an error setting up the data input stream.");
            e.printStackTrace(System.err);
        }
    }

    public static MCARegion read(File file) throws IOException {
        return new MCAReader(file).readAll();
    }

    private MCARegion readAll() throws IOException {
        int[] offsets = new int[1024];
        byte[] lengths = new byte[1024];
        int[] timestamps = new int[1024];
        List<NBTCompound> chunks = new ArrayList<>();
        for (int i = 0; i < 1024; ++i) {
            byte[] bytes = new byte[3];
            in.read(bytes);
            ByteBuffer bb = ByteBuffer.allocate(4);
            bb.put((byte) 0).put(bytes).rewind();
            offsets[i] = bb.getInt();
            lengths[i] = in.readByte();
        }
        for (int i = 0; i < 1024; ++i)
            timestamps[i] = in.readInt();
        for (int i = 0; i < 1024; ++i) {
            if (offsets[i] == 0)
                continue;
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            raf.seek(offsets[i] << 12);
            int length = raf.readInt();
            byte compression = raf.readByte();
            byte[] data = new byte[length - 1];
            raf.read(data);
            chunks.add(NBTReader.read(data, compression));
        }
        return new MCARegion(offsets, lengths, timestamps, chunks);
    }
}
