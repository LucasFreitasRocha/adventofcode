package io.github.lucasfreitasrocha;


import io.github.lucasfreitasrocha.service.DayTwoService;
import io.github.lucasfreitasrocha.util.FileService;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/";
//        DayOneService dayOneService = new DayOneService(new FileService());
//        System.out.println(dayOneService.partOne("src/main/resources/dayOne/test.txt"));
//        System.out.println(dayOneService.partTwo("src/main/resources/dayOne/test.txt"));

        DayTwoService dayTwoService = new DayTwoService(new FileService());
        System.out.println("part 1");
        System.out.println(dayTwoService.partOne("%sDayTwo/test.txt".formatted(path)));
        System.out.println(dayTwoService.partOne("%sDayTwo/input.txt".formatted(path)));
        System.out.println("________________________________________________________________________");
        System.out.println("part 2");
        System.out.println(dayTwoService.partTwo("%sDayTwo/test.txt".formatted(path)));
        System.out.println(dayTwoService.partTwo("%sDayTwo/input.txt".formatted(path)));
        System.out.println("________________________________________________________________________");
    }
}
