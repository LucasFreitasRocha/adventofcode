package io.github.lucasfreitasrocha.util;

import io.github.lucasfreitasrocha.service.ProcessLine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FileService {

    public void readFile(String path, ProcessLine processLine) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        if(inputStream == null){
            throw new IOException("File not Found: %s".formatted(path));
        }

        try(Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                processLine.process( scanner.nextLine());

            }
        }

    }


}
