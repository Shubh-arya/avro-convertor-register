package org.sarya;

import org.apache.avro.tool.IdlToSchemataTool;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AvroConvertor {
    public static void main(String[] args) throws Exception {
        IdlToSchemataTool tool = new IdlToSchemataTool();

        Path avdlFolder = Paths.get("./schemas/avdl/");
        Path avscFolder = Paths.get("./schemas/avsc/");

        try {
            // Get a list of files in the source directory
            List<Path> files = listFiles(avdlFolder);

            // Iterate through each file
            for (Path file : files) {
                var toolArgs = new ArrayList<String>();
                var folder = file.getFileName().toString().substring(0, file.getFileName().toString().indexOf("."));

                Path outputFolderPath = Paths.get(avscFolder.toString(), folder);
                if (!Files.exists(outputFolderPath)) {
                    Files.createDirectory(outputFolderPath);
                }

//                toolArgs.add(outputFolderPath.toFile().getAbsolutePath());
                toolArgs.add("C:\\Users\\4A\\IdeaProjects\\avro-convertor-register\\schemas\\avsc\\product");
                tool.run(System.in, System.out, System.err, toolArgs);
                var listFiles = listFiles(outputFolderPath);

//                var mainAvscFile = Arrays.stream(listFiles).sorted(Comparator.comparing(File::length).reversed()).findFirst();
//
//                if (mainAvscFile.isPresent()) {
//                    new CreateRandomFileTool().run(System.in, System.out, System.err, List.of(mainAvscFile.get().getAbsolutePath(), outputFolder.getAbsolutePath()));
//                }
//
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> listFiles(Path directory) throws IOException {
        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    files.add(entry);
                }
            }
        }
        return files;
    }
}