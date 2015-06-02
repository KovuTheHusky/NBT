package com.codeski.nbt.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.codeski.nbt.NBTReader;
import com.codeski.nbt.NBTWriter;
import com.codeski.nbt.tags.NBTCompound;

public class Main extends TestCase {
	private static final File[] in = { new File("src/test/resources/test.nbt"), new File("src/test/resources/bigtest.nbt") };
	private static final boolean[] inGzip = { false, true };
	private static final File out = new File("src/test/resources/test.tmp");

	public Main(String testName) throws IOException {
		super(testName);
		if (!out.exists())
			out.createNewFile();
		out.deleteOnExit();
	}

	@Test
	public void testReadWriteFileEquals() throws IOException {
		for (int i = 0; i < in.length; ++i) {
			byte[] f1 = Files.readAllBytes(in[i].toPath());
			new NBTWriter(out).writeNBT(NBTReader.read(in[i]), inGzip[i]);
			byte[] f2 = Files.readAllBytes(out.toPath());
			Assert.assertTrue("Testing file equality before and after read and write.", Arrays.equals(f1, f2));
		}
	}

	@Test
	public void testReadWriteObjectEquals() throws IOException {
		for (File f : in) {
			NBTCompound root1 = NBTReader.read(f);
			new NBTWriter(out).writeNBT(root1);
			NBTCompound root2 = NBTReader.read(out);
			Assert.assertTrue("Testing object equality before and after read and write.", root1.equals(root2));
		}
	}
}
