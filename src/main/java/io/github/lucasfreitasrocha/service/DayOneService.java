package io.github.lucasfreitasrocha.service;

import io.github.lucasfreitasrocha.util.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class DayOneService {
    private final FileService fileService;

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
        InputStream inputStream =  fileService.getFile(path);
        List<Integer> listLeft = new ArrayList<>();
        List<Integer> listRight = new ArrayList<>();
        try(Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("   ");
                listLeft.add(Integer.valueOf(line[0]));
                listRight.add(Integer.valueOf(line[1]));
            }
        }
        if(listLeft.size() != listRight.size()){
            throw new RuntimeException("the list has different size");
        }
        Collections.sort(listLeft);
        Collections.sort(listRight);
        Lists result = new Lists(listLeft, listRight);
        return result;
    }

    private record Lists(List<Integer> left, List<Integer> right) {
    }
}
