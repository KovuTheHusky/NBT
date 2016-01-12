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

public class Main extends TestCase {
    private static final File out = new File("src/test/resources/test.tmp");

    public Main(String testName) throws IOException {
        super(testName);
        if (!out.exists())
            out.createNewFile();
        out.deleteOnExit();
    }

    @Test
    public void testReadWriteBigTest() throws IOException {
        Assert.assertTrue(this.testReadWrite("bigtest.nbt", true));
    }

    @Test
    public void testReadWriteLevel() throws IOException {
        Assert.assertTrue(this.testReadWrite("level.dat", true));
    }

    @Test
    public void testReadWriteMineshaft() throws IOException {
        Assert.assertTrue(this.testReadWrite("Mineshaft.dat", true));
    }

    @Test
    public void testReadWriteMonument() throws IOException {
        Assert.assertTrue(this.testReadWrite("Monument.dat", true));
    }

    @Test
    public void testReadWriteStronghold() throws IOException {
        Assert.assertTrue(this.testReadWrite("Stronghold.dat", true));
    }

    @Test
    public void testReadWriteTest() throws IOException {
        Assert.assertTrue(this.testReadWrite("test.nbt", false));
    }

    @Test
    public void testReadWriteVillage() throws IOException {
        Assert.assertTrue(this.testReadWrite("Village.dat", true));
    }

    @Test
    public void testReadWriteVillages() throws IOException {
        Assert.assertTrue(this.testReadWrite("villages.dat", true));
    }

    @Test
    public void testReadWriteVillagesEnd() throws IOException {
        Assert.assertTrue(this.testReadWrite("villages_end.dat", true));
    }

    @Test
    public void testReadWriteVillagesNether() throws IOException {
        Assert.assertTrue(this.testReadWrite("villages_nether.dat", true));
    }

    private boolean testReadWrite(String basename, boolean compressed) throws IOException {
        File in = new File("src/test/resources/" + basename);
        byte[] f1 = Files.readAllBytes(in.toPath());
        NBTWriter.writeNBT(NBTReader.read(in), out, compressed);
        byte[] f2 = Files.readAllBytes(out.toPath());
        return Arrays.equals(f1, f2);
    }
}
