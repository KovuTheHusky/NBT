package com.codeski.nbt;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.codeski.nbt.tags.NBT;
import com.codeski.nbt.tags.NBTByte;
import com.codeski.nbt.tags.NBTByteArray;
import com.codeski.nbt.tags.NBTCompound;
import com.codeski.nbt.tags.NBTDouble;
import com.codeski.nbt.tags.NBTEnd;
import com.codeski.nbt.tags.NBTFloat;
import com.codeski.nbt.tags.NBTInteger;
import com.codeski.nbt.tags.NBTIntegerArray;
import com.codeski.nbt.tags.NBTList;
import com.codeski.nbt.tags.NBTLong;
import com.codeski.nbt.tags.NBTShort;
import com.codeski.nbt.tags.NBTString;

/**
 * Class for reading NBT binary data from files.
 */
public class NBTReader {
	private final File file;
	private DataInputStream in;

	public NBTReader(File file) {
		this.file = file;
	}

	/**
	 * Reads the file and returns the root compound tag and its children.
	 *
	 * @throws IOException
	 */
	public NBTCompound read() throws IOException {
		try {
			if (this.isCompressed(file))
				in = new DataInputStream(new GZIPInputStream(new FileInputStream(file)));
			else
				in = new DataInputStream(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (NBTCompound) this.readTag();
	}

	private boolean isCompressed(File f) {
		int magic = 0;
		try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
			magic = raf.read() & 0xff | raf.read() << 8 & 0xff00;
		} catch (Throwable e) {
			e.printStackTrace(System.err);
		}
		return magic == GZIPInputStream.GZIP_MAGIC;
	}

	private NBT<?> readPayload(final byte type, final String name) throws IOException {
		switch (type) {
			case NBT.BYTE:
				return new NBTByte(name, in.readByte());
			case NBT.SHORT:
				return new NBTShort(name, in.readShort());
			case NBT.INTEGER:
				return new NBTInteger(name, in.readInt());
			case NBT.LONG:
				return new NBTLong(name, in.readLong());
			case NBT.FLOAT:
				return new NBTFloat(name, in.readFloat());
			case NBT.DOUBLE:
				return new NBTDouble(name, in.readDouble());
			case NBT.BYTE_ARRAY:
				int byteArrayLength = in.readInt();
				List<Byte> byteArrayBytes = new ArrayList<Byte>(byteArrayLength);
				for (int i = 0; i < byteArrayLength; i++)
					byteArrayBytes.add(in.readByte());
				return new NBTByteArray(null, byteArrayBytes);
			case NBT.STRING:
				short stringLength = in.readShort();
				byte[] stringBytes = new byte[stringLength];
				in.readFully(stringBytes);
				return new NBTString(name, new String(stringBytes, NBT.CHARSET));
			case NBT.LIST:
				byte listType = in.readByte();
				int listLength = in.readInt();
				List<NBT<?>> list = new ArrayList<NBT<?>>(listLength);
				for (int i = 0; i < listLength; ++i)
					list.add(this.readPayload(listType, null));
				return new NBTList(name, listType, list);
			case NBT.COMPOUND:
				NBT<?> tag;
				List<NBT<?>> tags = new ArrayList<NBT<?>>();
				while (!((tag = this.readTag()) instanceof NBTEnd))
					tags.add(tag);
				return new NBTCompound(name, tags);
			case NBT.INTEGER_ARRAY:
				int integerArrayLength = in.readInt();
				List<Integer> integerArrayIntegers = new ArrayList<Integer>(integerArrayLength);
				for (int i = 0; i < integerArrayLength; i++)
					integerArrayIntegers.add(in.readInt());
				return new NBTIntegerArray(name, integerArrayIntegers);
			default:
				System.err.println("Unsupported type: " + type);
				return null;
		}
	}

	private NBT<?> readTag() throws IOException {
		final byte type = in.readByte();
		if (type == NBT.END)
			return new NBTEnd();
		else {
			short nameLength = in.readShort();
			byte[] bytes = new byte[nameLength];
			in.readFully(bytes);
			String name = new String(bytes, NBT.CHARSET);
			return this.readPayload(type, name);
		}
	}
}
