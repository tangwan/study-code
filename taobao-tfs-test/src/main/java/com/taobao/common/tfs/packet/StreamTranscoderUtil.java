/*
 * (C) 2007-2010 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 */
package com.taobao.common.tfs.packet;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.ArrayList;

public class StreamTranscoderUtil {
    public static void writeString(ByteBuffer byteBuffer, String str) {
        if (str == null) {
            byteBuffer.putInt(0);
        } else {
            byte[] b = str.getBytes();

            byteBuffer.putInt(b.length + 1);
            byteBuffer.put(b);
            byteBuffer.put((byte) 0);
        }
    }

    public static String readString(ByteBuffer byteBuffer ) {
        int len = byteBuffer.getInt();

        if (len <= 1) {
            return "";
        } else {
            byte[] b = new byte[len];

            byteBuffer.get(b);
            return new String(b, 0, len - 1);
        }
    }

    public static void writeV(ByteBuffer byteBuffer, List<Integer> vint) {
        if (vint == null) return;
        // write vector's length
        byteBuffer.putInt(vint.size());
        // write content
        for (int i = 0; i < vint.size(); ++i) {
            byteBuffer.putInt(vint.get(i));
        }
    }

    public static List<Integer> readV(ByteBuffer byteBuffer) {
        List<Integer> ret = new ArrayList<Integer>();
        // get vector's length
        int size = byteBuffer.getInt();
        for (int i = 0; i < size; ++i) {
            ret.add(byteBuffer.getInt());
        }
        return ret;
    }

    public static void writeVL(ByteBuffer byteBuffer, List<Long> vlong) {
        if (vlong == null) return;
        // write vector's length
        byteBuffer.putInt(vlong.size());
        // write content
        for (int i = 0; i < vlong.size(); ++i) {
            byteBuffer.putLong(vlong.get(i));
        }
    }

    public static List<Long> readVL(ByteBuffer byteBuffer) {
        List<Long> ret = new ArrayList<Long>();
        // get vector's length
        int size = byteBuffer.getInt();
        for (int i = 0; i < size; ++i) {
            ret.add(byteBuffer.getLong());
        }
        return ret;
    }

    public static void writeByteArray(ByteBuffer byteBuffer, byte[] bytes) {
        byteBuffer.put(bytes);
    }

    public static byte[] readByteArray(ByteBuffer byteBuffer, int length) {
        byte[] ret = new byte[length];
        byteBuffer.get(ret, 0, length);
        return ret;
    }

    public static boolean writeByteArray(ByteBuffer byteBuffer, byte[] bytes, int offset, int length) {
        if (bytes.length < offset + length) return false;
        byteBuffer.put(bytes, offset, length);
        return true;
    }


}
