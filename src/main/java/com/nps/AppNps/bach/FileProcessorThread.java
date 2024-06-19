package com.nps.AppNps.bach;

import com.nps.AppNps.service.ITrasformfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

@Component
public class FileProcessorThread extends Thread {
    private final String inputDirectory;
    private final String processedDirectory;
    private final ITrasformfiles trasformService;
    private final DataLoader dataLoader;

    @Autowired
    public FileProcessorThread(ITrasformfiles trasformService, DataLoader dataLoader) {
        this.inputDirectory = "C:/data";
        this.processedDirectory = "C:/data/bkdestino";
        this.trasformService = trasformService;
        this.dataLoader = dataLoader;
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

    private void processFile(Path filePath) {
        try {
            // Transform the file
            transformFile(filePath);

            // Upload file to database
            uploadFileToDatabase();

            // Move file to processed directory
            moveFileToProcessedDirectory(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transformFile(Path filePath) {
        // Call the transformation service
        try {
            trasformService.getFiles(filePath.getFileName().toString());
            System.out.println("Transformed file: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadFileToDatabase() {
        // Call the data loader service
        try {
            dataLoader.loadAllData();
            System.out.println("Uploaded data to database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void moveFileToProcessedDirectory(Path filePath) {
        // Move file to processed directory
        try {
            Path processedFilePath = Paths.get(processedDirectory, filePath.getFileName().toString());
            Files.move(filePath, processedFilePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved file to processed directory: " + processedFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
