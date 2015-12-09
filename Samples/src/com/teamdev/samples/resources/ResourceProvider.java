package com.teamdev.samples.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ResourceProvider {

    public static String getFilePath(String fileName) {
        final File tempDir = new File(".temp");
        tempDir.mkdir();
        final String tempFileName = ".temp/" + fileName;
        final File file = new File(tempFileName);
        final String jarName = "jxbrowserdemo.jar";
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String filePath = ResourceProvider.class.getProtectionDomain().getCodeSource().getLocation().toString().replace(jarName, tempFileName);
        return filePath;
    }
}
