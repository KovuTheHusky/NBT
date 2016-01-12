package com.codeski.nbt;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.codeski.nbt.tags.NBTCompound;

/**
 * Class allows for GUI and CLI interaction for converting files from NBT to NBT, JSON, or XML.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Console console = System.console();
        if (console != null)
            if (args.length == 2) {
                // Read in a user specified file
                File in = new File(args[0]);
                System.out.println("Reading " + in.getCanonicalPath() + "...");
                NBTCompound root = NBTReader.read(in);
                // Write the file to NBT, JSON, or XML
                File out = new File(args[1]);
                String extension = args[1].substring(args[1].lastIndexOf('.') + 1);
                System.out.println("Writing " + out.getCanonicalPath() + "...");
                if (extension.equalsIgnoreCase("dat"))
                    NBTWriter.writeNBT(root, out);
                else if (extension.equalsIgnoreCase("json"))
                    NBTWriter.writeJSON(root, out);
                else if (extension.equalsIgnoreCase("xml"))
                    NBTWriter.writeXML(root, out);
                else {
                    System.out.println("File extension was not dat, json, or xml. Writing NBT by default.");
                    NBTWriter.writeNBT(root, out);
                }
            } else
                System.out.println("Usage: java -jar " + new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName() + " <file-to-read> <file-to-write>");
        else if (!GraphicsEnvironment.isHeadless()) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            JFileChooser fc = new JFileChooser();
            FileFilter nbt = new FileNameExtensionFilter("Named Binary Tag (.dat)", "dat");
            fc.addChoosableFileFilter(nbt);
            // Show an open file dialog
            int ret = fc.showOpenDialog(null);
            if (ret == JFileChooser.APPROVE_OPTION) {
                File in = fc.getSelectedFile();
                NBTCompound root = NBTReader.read(in);
                FileFilter json = new FileNameExtensionFilter("JavaScript Object Notation (.json)", "json");
                fc.addChoosableFileFilter(json);
                FileFilter xml = new FileNameExtensionFilter("Extensible Markup Language (.xml)", "xml");
                fc.addChoosableFileFilter(xml);
                fc.setFileFilter(fc.getAcceptAllFileFilter());
                // Show a save file dialog
                ret = fc.showSaveDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File out = fc.getSelectedFile();
                    if (fc.getFileFilter().equals(nbt))
                        NBTWriter.writeNBT(root, out);
                    else if (fc.getFileFilter().equals(json))
                        NBTWriter.writeJSON(root, out);
                    else if (fc.getFileFilter().equals(xml))
                        NBTWriter.writeXML(root, out);
                    else {
                        String ext = out.getName().substring(out.getName().lastIndexOf('.') + 1, out.getName().length());
                        if (ext.equalsIgnoreCase("dat"))
                            NBTWriter.writeNBT(root, out);
                        else if (ext.equalsIgnoreCase("json"))
                            NBTWriter.writeJSON(root, out);
                        else if (ext.equalsIgnoreCase("xml"))
                            NBTWriter.writeXML(root, out);
                        else
                            NBTWriter.writeNBT(root, out); // Default case is to write NBT data
                    }
                }
            }
        }
    }
}
