package com.nsu.aop.javassist;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassNamesFinder {
    public String[] getClassNames(String dirName) throws Exception {
        File filesDir = new File(dirName);
        File[] files = filesDir.listFiles((dir1, name) -> name.endsWith("class"));
        if (files == null) {
            System.out.println("No such directory");
            throw new FileNotFoundException();
        }
        if (files.length == 0) {
            System.out.println("No compiled classes");
            throw new FileNotFoundException();
        }
        System.out.println("Found files: " + Arrays.toString(files));
        List<String> filenames = new ArrayList<>();
        for (File file : files) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            System.out.println(getClassName(inputStream));
        }
        return new String[10];
    }

    private String getClassName(InputStream is) throws Exception {
        DataInputStream dis = new DataInputStream(is);
        dis.readLong(); // skip header and class version
        int cpcnt = (dis.readShort() & 0xffff) - 1;
        int[] classes = new int[cpcnt];
        String[] strings = new String[cpcnt];
        for (int i = 0; i < cpcnt; i++) {
            int t = dis.read();
            if (t == 7) classes[i] = dis.readShort() & 0xffff;
            else if (t == 1) strings[i] = dis.readUTF();
            else if (t == 5 || t == 6) {
                dis.readLong();
                i++;
            } else if (t == 8) dis.readShort();
            else dis.readInt();
        }
        dis.readShort(); // skip access flags
        return strings[classes[(dis.readShort() & 0xffff) - 1] - 1].replace('/', '.');
    }
}
