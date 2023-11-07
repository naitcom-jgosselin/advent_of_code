package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void firstProblem() throws IOException {
        Path puzzleInput = Paths.get("./aoc_2015/resources/day3/puzzle_input");

        String route = new String(Files.readAllBytes(puzzleInput));

        /*
        Santa is delivering presents to an infinite two-dimensional grid of houses.

        He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls
        him via radio and tells him where to move next.

        Moves are always exactly one house to the north (^), south (v), east (>), or west (<).

        After each move, he delivers another present to the house at his new location.

        However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little
        off, and Santa ends up visiting some houses more than once. How many houses receive at least one present?
         */

        Map<String, Integer> houses = new HashMap<>();
        PresentDeliverer santa = new PresentDeliverer();

        for(String direction : route.split("")) {
            santa.move(direction);

            String currentCoordinates = santa.currentX + ";" + santa.currentY;

            if(!houses.containsKey(currentCoordinates)) {
                houses.put(currentCoordinates, 0);
            }
            houses.put(currentCoordinates, houses.get(currentCoordinates) + 1);
        }

        System.out.println("Number of houses that have received at least one present : " + houses.size());
    }

    public static void secondProblem() throws IOException {
        Path puzzleInput = Paths.get("./aoc_2015/resources/day3/puzzle_input");

        String route = new String(Files.readAllBytes(puzzleInput));

        /*
        The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver
        presents with him.

        Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take
        turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous
        year.
         */

        Map<String, Integer> houses = new HashMap<>();
        PresentDeliverer santa = new PresentDeliverer();
        PresentDeliverer roboSanta = new PresentDeliverer();

        boolean isSantaNext = true;
        for(String direction : route.split("")) {
            PresentDeliverer currentDeliverer;
            if(isSantaNext) {
                currentDeliverer = santa;
            } else {
                currentDeliverer = roboSanta;
            }

            currentDeliverer.move(direction);
            String currentCoordinates = currentDeliverer.currentX + ";" + currentDeliverer.currentY;

            if(!houses.containsKey(currentCoordinates)) {
                houses.put(currentCoordinates, 0);
            }
            houses.put(currentCoordinates, houses.get(currentCoordinates) + 1);

            isSantaNext = !isSantaNext;
        }

        System.out.println("Number of houses that have received at least one present : " + houses.size());
    }

    public static class PresentDeliverer {
        int currentX = 0;
        int currentY = 0;

        public void move(String direction) {
            switch (direction) {
                case "^":
                    currentY++;
                    break;
                case "v":
                    currentY--;
                    break;
                case ">":
                    currentX++;
                    break;
                case "<":
                    currentX--;
                    break;
                default:
                    break;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        firstProblem();
        secondProblem();
    }
}
