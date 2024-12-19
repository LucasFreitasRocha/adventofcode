package io.github.lucasfreitasrocha.service;

import io.github.lucasfreitasrocha.util.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayFourService implements ProcessLine {
    private final Integer lengthArray = 140;
    private final FileService fileService;
    private String[][] arrayWords = new String[lengthArray][lengthArray];
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

    public Integer partOne(String path) throws IOException {
        i = 0;
        j = 0;
        Integer  result = 0;
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

    private  boolean checkGrid(int x, int y, int xDirection, int yDirection, int position) {
        if (position == word.length) return true; // Palavra completa encontrada
        if (x < 0 || y < 0 || x >= arrayWords.length || y >= arrayWords[0].length) return false; // Fora dos limites
        if (!arrayWords[x][y].equalsIgnoreCase(String.valueOf(word[position]))) return false; // Caracter não corresponde
        return checkGrid(x + xDirection, y + yDirection, xDirection, yDirection, position + 1);
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
