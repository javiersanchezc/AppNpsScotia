package com.nps.AppNps.loadProces;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSV {
    private final Path inputFilePath;
    private final Path outputFilePath;
    private final String firstLine;

    public CSV(String inputFilePath, String outputFilePath, String firstLine) {
        this.inputFilePath = Paths.get(inputFilePath);
        this.outputFilePath = Paths.get(outputFilePath);
        this.firstLine = firstLine;
    }

    public void transformCsv() {
        if (!Files.exists(inputFilePath)) {
            System.err.println("El archivo no existe: " + inputFilePath);
            return;
        }

        try {
            String content = new String(Files.readAllBytes(inputFilePath), StandardCharsets.UTF_8);
            int indexOfFirstLineBreak = content.indexOf("\n");
            if (indexOfFirstLineBreak != -1) {
                content = content.substring(indexOfFirstLineBreak + 1);
            }
            content = firstLine + "\n" + content;
            String modifiedContent = replaceCommasAndQuotes(content);
            Files.write(outputFilePath, modifiedContent.getBytes(StandardCharsets.UTF_8));
            System.out.println("Transformation completed. Result written to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error durante la transformación del archivo: " + e.getMessage());
        }
    }

    private String replaceCommasAndQuotes(String input) {
        StringBuilder result = new StringBuilder(input.length());
        boolean insideQuotes = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '"') {
                insideQuotes = !insideQuotes;
            } else if (insideQuotes && c == ',') {
                result.append('.');
            } else if (c != '"') {
                result.append(c);
            }
        }
        return result.toString();
    }
}