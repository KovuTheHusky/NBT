package com.kovuthehusky.nbt;

import com.kovuthehusky.nbt.tags.NBT;
import com.kovuthehusky.nbt.regions.MCARegion;
import com.kovuthehusky.nbt.tags.NBTCompound;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class MCAWriter {
        /**
         * Writes the tag specified and its children as JSON text.
         *
         * @param region The root tag to write.
         * @param file The file to write to.
         *
         * @throws FileNotFoundException If the file being written to does not exist.
         */
        public static void writeJSON(MCARegion region, File file) throws FileNotFoundException {
            new com.kovuthehusky.nbt.MCAWriter(region, file).writeJSON();
        }

        /**
         * Writes the tag specified and its children as NBT binary data.
         *
         * @param region The root tag to write.
         * @param file The file to write to.
         *
         * @throws FileNotFoundException If the file being written to does not exist.
         */
        public static void writeMCA(MCARegion region, File file) throws FileNotFoundException {
            com.kovuthehusky.nbt.MCAWriter.writeMCA(region, file, true);
        }

        /**
         * Writes the tag specified and its children as NBT binary data.
         *
         * @param region       The root tag to write.
         * @param file       The file to write to.
         * @param compressed Whether or not the file should be compressed.
         *
         * @throws FileNotFoundException If the file being written to does not exist.
         */
        public static void writeMCA(MCARegion region, File file, boolean compressed) throws FileNotFoundException {
            new com.kovuthehusky.nbt.MCAWriter(region, file).writeMCA(compressed);
        }

        /**
         * Writes the tag specified and its children as XML text.
         *
         * @param region The root tag to write.
         * @param file The file to write to.
         *
         * @throws FileNotFoundException If the file being written to does not exist.
         */
        public static void writeXML(MCARegion region, File file) throws FileNotFoundException {
            new com.kovuthehusky.nbt.MCAWriter(region, file).writeXML();
        }

        private final File file;
        private final MCARegion region;

        private MCAWriter(MCARegion region, File file) throws FileNotFoundException {
            this.region = region;
            this.file = file;
        }

        private void writeJSON() {
            try (PrintWriter out = new PrintWriter(file)) {
                out.print("{");
                out.print("\"Offsets\":[");
                for (int i = 0; i < region.getOffsets().length; ++i) {
                    if (i != 0)
                        out.print(",");
                    out.print(region.getOffsets()[i]);
                }
                out.print("]");
                out.print(",");
                out.print("\"Lengths\":[");
                for (int i = 0; i < region.getLengths().length; ++i) {
                    if (i != 0)
                        out.print(",");
                    out.print(region.getLengths()[i]);
                }
                out.print("]");
                out.print(",");
                out.print("\"Timestamps\":[");
                for (int i = 0; i < region.getTimestamps().length; ++i) {
                    if (i != 0)
                        out.print(",");
                    out.print(region.getTimestamps()[i]);
                }
                out.print("]");
                out.print(",");
                out.print("\"Chunks\":[");
                for (int i = 0; i < region.getChunks().size(); ++i) {
                    if (i != 0)
                        out.print(",");
                    out.print("{" + region.getChunks().get(i).toJSON() + "}");
                }
                out.print("]");
                out.print("}");
                out.println();
            } catch (IOException e) {
                System.err.println("There was an error writing the data to JSON text.");
                e.printStackTrace(System.err);
            }
        }

        private void writeMCA(boolean compressed) {
            // not supported
        }

        private void writeXML() {
            // not supported
        }
}
