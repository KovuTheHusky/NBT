package com.codeski.nbt;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import com.codeski.nbt.tags.NBT;

/**
 * Class for writing NBT structures as NBT, JSON, or XML.
 */
public class NBTWriter {
    /**
     * Writes the tag specified and its children as JSON text.
     *
     * @param root
     *            The root tag to write.
     * @param file
     *            The file to write to.
     * @throws FileNotFoundException
     *             If the file being written to does not exist.
     */
    public static void writeJSON(NBT<?> root, File file) throws FileNotFoundException {
        new NBTWriter(root, file).writeJSON();
    }

    /**
     * Writes the tag specified and its children as NBT binary data.
     *
     * @param root
     *            The root tag to write.
     * @param file
     *            The file to write to.
     * @throws FileNotFoundException
     *             If the file being written to does not exist.
     */
    public static void writeNBT(NBT<?> root, File file) throws FileNotFoundException {
        NBTWriter.writeNBT(root, file, true);
    }

    /**
     * Writes the tag specified and its children as NBT binary data.
     *
     * @param root
     *            The root tag to write.
     * @param file
     *            The file to write to.
     * @param compressed
     *            Whether or not the file should be compressed.
     * @throws FileNotFoundException
     *             If the file being written to does not exist.
     */
    public static void writeNBT(NBT<?> root, File file, boolean compressed) throws FileNotFoundException {
        new NBTWriter(root, file).writeNBT(compressed);
    }

    /**
     * Writes the tag specified and its children as XML text.
     *
     * @param root
     *            The root tag to write.
     * @param file
     *            The file to write to.
     * @throws FileNotFoundException
     *             If the file being written to does not exist.
     */
    public static void writeXML(NBT<?> root, File file) throws FileNotFoundException {
        new NBTWriter(root, file).writeXML();
    }

    private final File file;
    private final NBT<?> root;

    private NBTWriter(NBT<?> root, File file) throws FileNotFoundException {
        this.root = root;
        this.file = file;
    }

    private void writeJSON() {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println("{ " + root.toJSON() + " }");
        } catch (IOException e) {
            System.err.println("There was an error writing the data to JSON text.");
            e.printStackTrace(System.err);
        }
    }

    private void writeNBT(boolean compressed) {
        if (compressed)
            try (DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)))) {
                out.write(root.toNBT());
            } catch (IOException e) {
                System.err.println("There was an error writing the data to NBT binary data.");
                e.printStackTrace(System.err);
            }
        else
            try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
                out.write(root.toNBT());
            } catch (IOException e) {
                System.err.println("There was an error writing the data to NBT binary data.");
                e.printStackTrace(System.err);
            }
    }

    private void writeXML() {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + root.toXML());
        } catch (IOException e) {
            System.err.println("There was an error writing the data to XML text.");
            e.printStackTrace(System.err);
        }
    }
}
