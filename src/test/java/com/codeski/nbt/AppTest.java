package com.codeski.nbt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	private static final File[] in = new File("src/test/resources").listFiles(new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return name.toLowerCase().endsWith(".dat") || name.toLowerCase().endsWith(".mca");
		}
	});
	private static final File out = new File("test.tmp");

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) throws IOException {
		super(testName);
		if (!out.exists())
			out.createNewFile();
		out.deleteOnExit();
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Assert.assertTrue(true);
	}

	public void testEquals() throws FileNotFoundException, IOException {
		for (File f : in) {
			new NBTWriter(out).writeNBT(new NBTReader(f).read());
			Assert.assertEquals(new NBTReader(f).read(), new NBTReader(out).read());
		}
	}

	public void testReadNBT() throws FileNotFoundException, IOException {
		for (File f : in)
			new NBTReader(f).read();
	}

	public void testWriteJSON() throws FileNotFoundException, IOException {
		for (File f : in)
			new NBTWriter(out).writeJSON(new NBTReader(f).read());
	}

	public void testWriteNBT() throws FileNotFoundException, IOException {
		for (File f : in)
			new NBTWriter(out).writeNBT(new NBTReader(f).read());
	}

	public void testWriteXML() throws FileNotFoundException, IOException {
		for (File f : in)
			new NBTWriter(out).writeXML(new NBTReader(f).read());
	}
}
