package com.nsu.aop;

public class PackageNameStorage {
    private static  String packageName;

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        PackageNameStorage.packageName = packageName;
    }
}
