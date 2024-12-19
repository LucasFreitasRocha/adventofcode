package io.github.lucasfreitasrocha.service;

import io.github.lucasfreitasrocha.util.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayFourService implements ProcessLine {
    private  Integer lengthArray;
    private final FileService fileService;
    private String[][] arrayWords;
    private Integer i = 0;
    private Integer j = 0;

    private String[] word = new String[]{"X", "M", "A", "S"};
    private List<String> lines = new ArrayList<>();
    int[][] directions = {
            {0, 1}, {1, 0}, {1, 1}, {1, -1}, // Direita, Baixo, Diagonal Baixo Direita, Diagonal Baixo Esquerda
            {0, -1}, {-1, 0}, {-1, -1}, {-1, 1} // Esquerda, Cima, Diagonal Cima Esquerda, Diagonal Cima Direita
    };

    public DayFourService(FileService fileService) {
        this.fileService = fileService;
    }

    public Integer partOne(String path, boolean test) throws IOException {
        if(test){
            lengthArray = 10;
        }else {
            lengthArray =  140;
        }
        arrayWords = new String[lengthArray][lengthArray];
        i = 0;
        j = 0;
        Integer result = 0;
        this.fileService.readFile(path, this::process);
        for (int x = 0; x < lengthArray; x++) {
            for (int y = 0; y < lengthArray; y++) {
                for (int[] direction : directions) {
                    if (checkGrid(x, y, direction[0], direction[1], 0)) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public Integer partTwo(String path, boolean test) throws IOException {
        if(test){
            lengthArray = 10;
        }else {
            lengthArray =  140;
        }
        arrayWords = new String[lengthArray][lengthArray];
        i = 0;
        j = 0;
        Integer result = 0;
        this.fileService.readFile(path, this::process);
        for (int x = 0; x < lengthArray; x++) {
            for (int y = 0; y < lengthArray; y++) {
                if(checkGridPartTwo(x,y,2)) result++;
            }
        }
        return result;
    }

    private boolean checkGrid(int x, int y, int xDirection, int yDirection, int position) {
        if (position == word.length) return true; // Palavra completa encontrada
        if (x < 0 || y < 0 || x >= arrayWords.length || y >= arrayWords[0].length) return false; // Fora dos limites
        if (!arrayWords[x][y].equalsIgnoreCase(String.valueOf(word[position])))
            return false; // Caracter não corresponde
        return checkGrid(x + xDirection, y + yDirection, xDirection, yDirection, position + 1);
    }

    public boolean checkGridPartTwo(int x, int y,  int position) {
        if (x - 1 < 0) return false;
        if (y - 1 < 0) return false;
        if (arrayWords.length <= x + 1) return false;
        if (arrayWords[x].length <= y + 1) return false;
        if (!isTheLetter(x,y, position)) return false;

        /**
         * M M
         *  A
         * S S
         */
            if (
                    isTheLetter(x - 1, y - 1, 1)
                            && isTheLetter(x - 1, y + 1, 1)
                            && isTheLetter(x + 1, y - 1, 3)
                            && isTheLetter(x + 1, y + 1, 3)
            ) return true;

        /**
         * S S
         *  A
         * M M
         */
        if (
                isTheLetter(x - 1, y - 1, 3)
                        && isTheLetter(x - 1, y + 1, 3)
                        && isTheLetter(x + 1, y - 1, 1)
                        && isTheLetter(x + 1, y + 1, 1)
        ) return true;

        /**
         * M S
         *  A
         * M S
         */
        if (
                isTheLetter(x - 1, y - 1, 1)
                        && isTheLetter(x - 1, y + 1, 3)
                        && isTheLetter(x + 1, y - 1, 1)
                        && isTheLetter(x + 1, y + 1, 3)
        ) return true;

        /**
         * S M
         *  A
         * S M
         */
        if (
                isTheLetter(x - 1, y - 1, 3)
                        && isTheLetter(x - 1, y + 1, 1)
                        && isTheLetter(x + 1, y - 1, 3)
                        && isTheLetter(x + 1, y + 1, 1)
        ) return true;

        return false;
    }


    private boolean isTheLetter(int x, int y, int position) {
        return arrayWords[x][y].equalsIgnoreCase(word[position]);
    }

    @Override
    public void process(String line) {
        lines.add(line);
        String[] lineSplit = line.split("");
        for (j = 0; j < lineSplit.length; j++) {
            arrayWords[i][j] = lineSplit[j];
        }
        i++;
    }


}
