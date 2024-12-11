package io.github.lucasfreitasrocha.service;

import io.github.lucasfreitasrocha.util.FileService;

import java.io.IOException;

public class DayTwoService implements ProcessLine{
    private final FileService fileService;
    private Integer reportSafe;
    public DayTwoService(FileService fileService) {
        this.fileService = fileService;
    }

    public Integer partOne(String path) throws IOException {
        reportSafe = 0;
        fileService.readFile(path, this::process);
        return reportSafe;
    }

    @Override
    public void process(String line) {
        String[] report = line.split(" ");
        if(isSafe(report)){
            reportSafe++;
        }
    }

    private boolean isSafe(String[] report) {
        boolean isAsc = true, isDesc = true, isSafeReport = true;

        for(var i = 0 ; i < report.length -1 ; i++){
            int number1 = Integer.parseInt(report[i]);
            int number2 = Integer.parseInt(report[i+1]);
            if(number1 >= number2){
                isAsc = false;
            }
            if( number1 <= number2){
                isDesc = false;
            }
            if(Math.abs(number1 - number2) > 3){
                isSafeReport = false;
                break;
            }
        }
        if(!isAsc && !isDesc){
            return false;
        }
        return isSafeReport;


    }
}
