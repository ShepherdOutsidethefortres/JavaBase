package com.jdjr.knowledge.test.frame.internal.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZipUtil {
    public ZipUtil() {
    }

    public static String compress(String original) throws IOException {
        if (original != null && original.length() != 0) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = null;

            try {
                gzip = new GZIPOutputStream(out);
                gzip.write(original.getBytes());
            } catch (IOException var12) {
                var12.printStackTrace();
            } finally {
                if (gzip != null) {
                    try {
                        gzip.close();
                    } catch (IOException var11) {
                        var11.printStackTrace();
                    }
                }

            }

            return (new BASE64Encoder()).encode(out.toByteArray());
        } else {
            return original;
        }
    }

    public static String uncompress(String compressedStr) {
        if (compressedStr == null) {
            return null;
        } else {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = null;
            GZIPInputStream ginzip = null;
            String decompressed = null;

            try {
                byte[] compressed = (new BASE64Decoder()).decodeBuffer(compressedStr);
                in = new ByteArrayInputStream(compressed);
                ginzip = new GZIPInputStream(in);
                byte[] buffer = new byte[1024];

                int offset;
                while((offset = ginzip.read(buffer)) != -1) {
                    out.write(buffer, 0, offset);
                }

                decompressed = out.toString();
            } catch (IOException var24) {
                var24.printStackTrace();
            } finally {
                if (ginzip != null) {
                    try {
                        ginzip.close();
                    } catch (IOException var23) {
                        var23.printStackTrace();
                    }
                }

                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException var22) {
                        var22.printStackTrace();
                    }
                }

                try {
                    out.close();
                } catch (IOException var21) {
                    var21.printStackTrace();
                }

            }

            return decompressed;
        }
    }
}
