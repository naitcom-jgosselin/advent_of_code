package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {

    public static void firstProblem() throws IOException {
        Path puzzleInput = Paths.get("./aoc_2015/resources/day1/puzzle_input");

        String inputAsString = new String(Files.readAllBytes(puzzleInput));

        /*
        Santa is trying to deliver presents in a large apartment building, but he can't find the right floor - the
        directions he got are a little confusing. He starts on the ground floor (floor 0) and then follows the
        instructions one character at a time.

        An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ), means he should go
        down one floor.

        The apartment building is very tall, and the basement is very deep; he will never find the top or bottom floors.
         */

        int current_floor = 0;

        for(String s : inputAsString.split("")) {
            if (s.equals("(")) {
                current_floor++;
            }
            else {
                current_floor--;
            }
        }

        System.out.println("The floor should be : " + current_floor);
    }

    public static void secondProblem() throws IOException {
        Path puzzleInput = Paths.get("./aoc_2015/resources/day1/puzzle_input");

        String inputAsString = new String(Files.readAllBytes(puzzleInput));

        /*
        Now, given the same instructions, find the position of the first character that causes him to enter the basement
         (floor -1). The first character in the instructions has position 1, the second character has position 2, and so
          on.
         */
        int current_floor = 0;
        int current_index = 0;

        for(String s : inputAsString.split("")) {
            current_index++;
            if (s.equals("(")) {
                current_floor++;
            }
            else {
                current_floor--;
            }

            if(current_floor == -1) {
                break;
            }
        }

        System.out.println("Basement found at index : " + current_index);
    }

    public static void main(String[] args) throws IOException {
        firstProblem();
        secondProblem();
    }
}
