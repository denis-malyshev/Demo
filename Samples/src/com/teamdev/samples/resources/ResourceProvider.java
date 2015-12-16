package com.teamdev.samples.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ResourceProvider {

    public static String getFilePath(String fileName) {
        File file = new File("Samples/src/com/teamdev/samples/resources/" + fileName);
        if (file.exists())
            try {
                return file.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        String dirName = "temp";
        String tempFilePath = dirName + "/" + fileName;
        String jarName = "jxbrowserdemo.jar";
        makeTempDir(dirName);
        file = new File(tempFilePath);
        extractFileInTempDir(file);
        String filePath = ResourceProvider.class.getProtectionDomain().getCodeSource().getLocation().toString().replace(jarName, tempFilePath);
        return filePath;
    }

    private static void extractFileInTempDir(File file) {
        String tempFilePath = "temp/" + file.getName();
        file = new File(tempFilePath);
        String jarName = "jxbrowserdemo.jar";
        try {
            JarFile jarFile = new JarFile(jarName);
            JarEntry jarEntry = jarFile.getJarEntry("src/com/teamdev/samples/resources/" + file.getName());
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            FileOutputStream outputStream = new FileOutputStream(file);
            while (inputStream.available() > 0) {
                outputStream.write(inputStream.read());
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean makeTempDir(String dirName) {
        File tempDir = new File(dirName);
        return tempDir.mkdir();
    }
}
