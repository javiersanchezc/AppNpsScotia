package com.nps.AppNps.bach;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileProcessorThread extends Thread {
    private final String inputDirectory;
    private final String processingDirectory;
    private final String processedDirectory;

    public FileProcessorThread(String inputDirectory, String processingDirectory, String processedDirectory) {
        this.inputDirectory = inputDirectory;
        this.processingDirectory = processingDirectory;
        this.processedDirectory = processedDirectory;
    }

    @Override
    public void run() {
        try {
            while (true) {
                try (Stream<Path> paths = Files.list(Paths.get(inputDirectory))) {
                    paths.filter(Files::isRegularFile).forEach(this::processFile);
                }
                // Sleep for a while before checking the folder again
                Thread.sleep(5000);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private void processFile(Path sourcePath) {
        try {
            Path processingPath = Paths.get(processingDirectory, sourcePath.getFileName().toString());
            Path processedPath = Paths.get(processedDirectory, sourcePath.getFileName().toString());

            // Move file to processing directory
            Files.move(sourcePath, processingPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved file to processing: " + processingPath);

            // Transform the file (dummy transformation here)
            transformFile(processingPath);

            // Upload file to database (dummy upload here)
            uploadFileToDatabase(processingPath);

            // Move file to processed directory
            Files.move(processingPath, processedPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved file to processed: " + processedPath);

            // Mark file as updated (this can be done by renaming or adding a log entry)
            markFileAsUpdated(processedPath);

            // Optionally, delete the original file from the input directory (although it was already moved)
            if (Files.exists(sourcePath)) {
                Files.delete(sourcePath);
                System.out.println("Deleted original file from input directory: " + sourcePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void transformFile(Path filePath) {
        // Implement your transformation logic here
        System.out.println("Transforming file: " + filePath);
    }

    private void uploadFileToDatabase(Path filePath) {
        // Implement your database upload logic here
        System.out.println("Uploading file to database: " + filePath);
    }

    private void markFileAsUpdated(Path filePath) {
        // Implement your logic to mark the file as updated
        System.out.println("File marked as updated: " + filePath);
    }

    public static void main(String[] args) {
        String inputDirectory = "C:/data";
        String processingDirectory = "C:/data/destino";
        String processedDirectory = "C:/data/bkdestino";

        FileProcessorThread fileProcessorThread = new FileProcessorThread(inputDirectory, processingDirectory, processedDirectory);
        fileProcessorThread.start();
    }
}

