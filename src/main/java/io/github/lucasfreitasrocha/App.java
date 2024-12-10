package io.github.lucasfreitasrocha;


import io.github.lucasfreitasrocha.service.DayOneService;
import io.github.lucasfreitasrocha.util.FileService;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        DayOneService dayOneService = new DayOneService(new FileService());
        System.out.println(dayOneService.partOne("src/main/resources/dayOne/test.txt"));
        System.out.println(dayOneService.partTwo("src/main/resources/dayOne/input.txt"));

    }
}
