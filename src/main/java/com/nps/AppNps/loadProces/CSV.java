package com.nps.AppNps.loadProces;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSV {
    private String inputFilePath;

    private String outputFilePath;

    private String firstLine;

    public CSV(String inputFilePath, String outputFilePath, String firstLine) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.firstLine = firstLine;
    }

    public void transformCsv() {
        try {
            String content = readFileContent(this.inputFilePath);
            int indexOfFirstLineBreak = content.indexOf("\n");
            if (indexOfFirstLineBreak != -1)
                content = content.substring(indexOfFirstLineBreak + 1);
            content = this.firstLine + "\n" + content;
            String modifiedContent = replaceCommasAndQuotes(content);
            writeToFile(this.outputFilePath, modifiedContent);
            System.out.println("Transformation completed. Result written to: " + this.outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("----------------"+e);
        }
    }

    private String readFileContent(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            String line;
            while ((line = br.readLine()) != null)
                content.append(line).append("\n");
            br.close();
        } catch (Throwable throwable) {
            try {
                br.close();
            } catch (Throwable throwable1) {
                throwable.addSuppressed(throwable1);
            }
            throw throwable;
        }
        return content.toString();
    }

    private void writeToFile(String filePath, String content) throws IOException {
        try {
            FileWriter writer = new FileWriter(filePath);
            try {
                writer.write(content);
                writer.close();
            } catch (Throwable throwable) {
                try {
                    writer.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private String replaceCommasAndQuotes(String input) {
        StringBuilder result = new StringBuilder();
        boolean insideQuotes = false;
        for (char c : input.toCharArray()) {
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

