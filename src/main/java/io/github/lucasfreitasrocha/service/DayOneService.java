package io.github.lucasfreitasrocha.service;

import io.github.lucasfreitasrocha.util.FileService;

import java.io.IOException;
import java.util.*;

public class DayOneService implements ProcessLine{
    private final FileService fileService;
    private List<Integer> listLeft;
    private List<Integer> listRight;

    public DayOneService(FileService fileService) {
        this.fileService = fileService;
    }

    public Integer partOne(String path) throws IOException {
        Lists lists = buildAndSort(path);
        Integer total = 0;
        for(var i = 0; i < lists.left().size() ; i ++){
           total += Math.abs(lists.left().get(i) - lists.right().get(i));
        }
        return total;
    }


    public Integer partTwo(String path) throws IOException {
        Lists lists = buildAndSort(path);
        Integer total = 0;
        for(var i = 0; i < lists.left().size() ; i ++){
            int valueLeft = lists.left.get(i);
            int howManyTimes = 0;
            for (int j = 0; j < lists.right.size(); j++) {
                if(lists.right.get(j) == valueLeft){
                    howManyTimes++;
                }
            }
            total += valueLeft * howManyTimes;
        }
        return total;
    }

    private Lists buildAndSort(String path) throws IOException {
        listLeft = new ArrayList<>();
        listRight = new ArrayList<>();
        fileService.readFile(path, this::process);
        if(listLeft.size() != listRight.size()){
            throw new RuntimeException("the list has different size");
        }
        Collections.sort(listLeft);
        Collections.sort(listRight);
        Lists result = new Lists(listLeft, listRight);
        return result;
    }

    @Override
    public void process(String line) {
        String[] lineSplit = line.split("   ");
        listLeft.add(Integer.valueOf(lineSplit[0]));
        listRight.add(Integer.valueOf(lineSplit[1]));
    }

    private record Lists(List<Integer> left, List<Integer> right) {
    }
}
