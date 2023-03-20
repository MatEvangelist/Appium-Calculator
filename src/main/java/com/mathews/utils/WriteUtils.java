package com.mathews.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WriteUtils {

    private static void gravarInfo(String fileName, String... infos) {
        List<String> lista = new ArrayList<>(Arrays.asList(infos));
        File file = new File("target/arquivosCSV");

        try {
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath() + "/" + fileName + ".csv", true);
            fileWriter.write(System.lineSeparator());
            fileWriter.write("info" + "; " +
                    Instant.now().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")) + "; " +
                    lista.stream().map(Object::toString).collect(Collectors.joining("; ")));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
