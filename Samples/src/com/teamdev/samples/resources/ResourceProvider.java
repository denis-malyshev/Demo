package com.teamdev.samples.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ResourceProvider {

    public static String getFilePath(String fileName) {
        makeTempDir();
        final String tempFilePath = ".temp/" + fileName;
        File file = new File(tempFilePath);
        final String jarName = "jxbrowserdemo.jar";
        final StringBuilder filePath = new StringBuilder();
        try {
            JarFile jarFile = new JarFile(jarName);
            JarEntry jarEntry = jarFile.getJarEntry("src/com/teamdev/samples/resources/" + fileName);
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            FileOutputStream outputStream = new FileOutputStream(file);
            while (inputStream.available() > 0) {
                outputStream.write(inputStream.read());
            }
            outputStream.close();
            inputStream.close();
            filePath.append(ResourceProvider.class.getProtectionDomain().getCodeSource().getLocation().toString().replace(jarName, tempFilePath));
        } catch (IOException e) {
            try {
                file = new File("Samples/src/com/teamdev/samples/resources/" + fileName);
                filePath.append(file.getCanonicalPath().toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return filePath.toString();
    }

    private static void makeTempDir() {
        final File tempDir = new File(".temp");
        tempDir.mkdir();
    }
}
