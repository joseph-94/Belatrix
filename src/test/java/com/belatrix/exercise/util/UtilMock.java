package com.belatrix.exercise.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class UtilMock {

    public static String getDefaultFolder(String folder) throws URISyntaxException {
        File defaultFolder = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath().concat(folder));
        defaultFolder.mkdir();
        return defaultFolder.toString();
    }

    public static String getContentFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);

        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
        }
        return sb.toString();
    }
}
