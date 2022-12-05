package com.example.semestfxx;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class highscoreLoader {

    public void highscoreGUI(){
        Scanner input = null;
        try {
            input = new Scanner(new File("score.txt"), StandardCharsets.UTF_8.name());
        } catch (Exception ex) {
            System.out.println("Can not open file.");
            System.exit(0);
        }
        while (input.hasNextInt()) {
            int number = input.nextInt();
            System.out.println(number);
            String content = input.useDelimiter("\\A").next();
        }
        input.close();


    }

}
