package io.github.lucasfreitasrocha.service;

import io.github.lucasfreitasrocha.util.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayTwoService implements ProcessLine{
    private final FileService fileService;
    private Integer numberOfSafeReports;
    private Boolean isErrorTolerant = false;
    public DayTwoService(FileService fileService) {
        this.fileService = fileService;
    }

    public Integer partOne(String path) throws IOException {
        numberOfSafeReports = 0;
        fileService.readFile(path, this::process);
        return numberOfSafeReports;
    }
    public Integer partTwo(String path) throws IOException {
        isErrorTolerant = true;
        numberOfSafeReports = 0;
        fileService.readFile(path, this::process);
        return numberOfSafeReports;
    }

    @Override
    public void process(String line) {
        ArrayList<String> originalList = new ArrayList<>(Arrays.asList(line.split(" ")));

        boolean repostIsSafe = false;
        if(isErrorTolerant){
            ArrayList<String> modifiedList;
            for (int i = -1; i < originalList.size(); i++) {
                modifiedList = new ArrayList<>(originalList);
                if(i != -1){
                    modifiedList.remove(i);
                }
                if(verifyIfReportIsSafe(modifiedList)){
                    repostIsSafe = true;
                    break;
                }

            }
        }else {
           repostIsSafe = verifyIfReportIsSafe(originalList);
        }
        if(repostIsSafe){
            numberOfSafeReports++;
        }
    }

    private boolean verifyIfReportIsSafe(List<String> report) {
        boolean isAsc = true, isDesc = true, isSafeReport = true;
        for(var i = 0 ; i < report.size() -1 ; i++){
            int number1 = Integer.parseInt(report.get(i));
            int number2 = Integer.parseInt(report.get(i+1));
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
