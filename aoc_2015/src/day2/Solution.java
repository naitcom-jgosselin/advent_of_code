package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Solution {
    public static void firstProblem() throws IOException {
        Path puzzleInput = Paths.get("./aoc_2015/resources/day2/puzzle_input");

        List<String> lines = Files.readAllLines(puzzleInput);

        /*
        The elves are running low on wrapping paper, and so they need to submit an order for more. They have a list of
        the dimensions (length l, width w, and height h) of each present, and only want to order exactly as much as they
        need.

        Fortunately, every present is a box (a perfect right rectangular prism), which makes calculating the required
        wrapping paper for each gift a little easier: find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l.
        The elves also need a little extra paper for each present: the area of the smallest side.
         */

        int totalWrapping = 0;
        for(String line : lines) {
            Gift gift = Gift.parseFromLine(line);
            totalWrapping += gift.calculateRequiredWrapping();
        }

        System.out.println("Total square feet of wrapping needed : " + totalWrapping);

    }

    public static void secondProblem() throws IOException {
        Path puzzleInput = Paths.get("./aoc_2015/resources/day2/puzzle_input");

        List<String> lines = Files.readAllLines(puzzleInput);

        /*
        The elves are also running low on ribbon. Ribbon is all the same width, so they only have to worry about the
        length they need to order, which they would again like to be exact.

        The ribbon required to wrap a present is the shortest distance around its sides, or the smallest perimeter of
        any one face. Each present also requires a bow made out of ribbon as well; the feet of ribbon required for the
        perfect bow is equal to the cubic feet of volume of the present. Don't ask how they tie the bow, though; they'll
        never tell.
         */

        int totalRibbon = 0;
        for(String line : lines) {
            Gift gift = Gift.parseFromLine(line);
            totalRibbon += gift.calculateRequiredRibbon();
        }

        System.out.println("Total feet of ribbon needed : " + totalRibbon);
    }

    private static class Gift {
        int length;
        int width;
        int height;

        private Gift(int length, int width, int height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }

        public static Gift parseFromLine(String line) {
            String[] dimensions = line.split("x");
            return new Gift(Integer.parseInt(dimensions[0]),
                    Integer.parseInt(dimensions[1]),
                    Integer.parseInt(dimensions[2])
            );
        }

        public int calculateRequiredWrapping() {
            int side1 = length*width;
            int side2 = width*height;
            int side3 = height*length;

            int smallest_side = side1;
            if(side2 < smallest_side) smallest_side = side2;
            if(side3 < smallest_side) smallest_side = side3;

            return 2*side1 + 2*side2 + 2*side3 + smallest_side;
        }

        public int calculateRequiredRibbon() {
            int side1 = length*width;
            int side2 = width*height;
            int side3 = height*length;

            int smallest_side = side1;
            if(side2 < smallest_side) smallest_side = side2;
            if(side3 < smallest_side) smallest_side = side3;

            int ribbonWrap = length * 2 + width * 2;
            if(smallest_side == side2) {
                ribbonWrap = width * 2 + height * 2;
            }
            if(smallest_side == side3) {
                ribbonWrap = height * 2 + length * 2;
            }

            return ribbonWrap + length * height * width;
        }
    }

    public static void testCalculateWrapping() {
        Gift gift = new Gift(2,3,4);
        if(gift.calculateRequiredWrapping() == 58) {
            System.out.println("calculateWrapping OK");
        }
    }

    public static void testCalculateRibbon() {
        Gift gift = new Gift(2,3,4);
        if(gift.calculateRequiredRibbon() == 34) {
            System.out.println("calculateRibbon OK");
        }
    }

    public static void main(String[] args) throws IOException {
        testCalculateWrapping();
        testCalculateRibbon();
        firstProblem();
        secondProblem();
    }
}
