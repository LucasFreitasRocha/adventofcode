package io.github.lucasfreitasrocha.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileService {

    public InputStream getFile(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        if(inputStream == null){
            throw new IOException("File not Found: %s".formatted(path));
        }
        return inputStream;
    }


}
