package io.github.lucasfreitasrocha.service;

import io.github.lucasfreitasrocha.util.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThreeService implements ProcessLine {
    private final FileService fileService;
    private Integer result = 0;
    boolean enable = true;
    private final static Pattern pattern = Pattern.compile(
            "(do\\(\\))|(don't\\(\\))|(mul\\((\\d+),(\\d+)\\))"
    );
    private Boolean instructions;
    public DayThreeService(FileService fileService) {
        this.fileService = fileService;
    }

    public Integer partOne(String path) throws IOException {
        instructions =  false;
        this.fileService.readFile(path, this::process);
        return result;
    }

    public Integer partTwo(String path) throws IOException {
        instructions =  true;
        this.fileService.readFile(path, this::process);
        return result;
    }

    @Override
    public void process(String line) {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            if(matcher.group(3) != null && !instructions){
              result +=  Integer.valueOf(matcher.group(4)) * Integer.valueOf(matcher.group(5));
            }
            if(instructions){
                if (matcher.group(1) != null) {
                    enable = true;
                }
                if (matcher.group(2) != null) {
                    enable = false;
                }
                if (matcher.group(3) != null && enable) {
                    result +=  Integer.valueOf(matcher.group(4)) * Integer.valueOf(matcher.group(5));
                }
            }
        }
    }

}
