package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void firstProblem() throws IOException {
        /*
        Santa needs help figuring out which strings in his text file are naughty or nice.

        A nice string is one with all of the following properties:

        It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
        It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or
        dd).
        It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.

        How many strings are nice ?
         */

        Path puzzleInput = Paths.get("./aoc_2015/resources/day5/puzzle_input");


        List<String> lines = Files.readAllLines(puzzleInput);
        int niceCounter = 0;

        for(String line : lines) {
            if(isNice(line)) {
                niceCounter++;
            }
        }

        System.out.println("Number of nice strings : " + niceCounter);
    }

    public static void secondProblem() throws IOException {
        Path puzzleInput = Paths.get("./aoc_2015/resources/day5/puzzle_input");

        /*
        Now, a nice string is one with all of the following properties:

        It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy
        (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).

        It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe),
        or even aaa.
         */

        List<String> lines = Files.readAllLines(puzzleInput);
        int niceCounter = 0;

        for(String line : lines) {
            if(isVeryNice(line)) {
                niceCounter++;
            }
        }

        System.out.println("Number of nice strings : " + niceCounter);
    }

    public static boolean isNice(String input) {
        List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");
        List<String> forbiddenStrings = Arrays.asList("ab", "cd", "pq", "xy");

        // It contains at least three vowels
        int vowelCount = 0;
        String previousChar = "";
        boolean hasLetterTwiceInARow = false;
        boolean hasForbiddenStringAppeared = false;
        for(String c : input.split("")) {
            if(vowels.contains(c)) {
                vowelCount++;
            }
            if(previousChar.equals(c)) {
                hasLetterTwiceInARow = true;
            }
            if(forbiddenStrings.contains(previousChar + c)) {
                hasForbiddenStringAppeared = true;
            }
            previousChar = c;
        }

        return vowelCount >= 3 && hasLetterTwiceInARow && !hasForbiddenStringAppeared;
    }

    public static boolean isVeryNice(String input) {
        Map<String, Integer> twoLetterSequences = new HashMap<>();
        String previousChar = "";
        String lastSequenceAdded = "";

        int stringIndex = 0;
        boolean hasMirrorSequence = false;
        for(String c : input.split("")) {
            if(!lastSequenceAdded.equals(previousChar + c)) {
                lastSequenceAdded = previousChar + c;
                if(!twoLetterSequences.containsKey(lastSequenceAdded)) {
                    twoLetterSequences.put(lastSequenceAdded, 0);
                }
                twoLetterSequences.put(lastSequenceAdded, twoLetterSequences.get(previousChar + c) + 1);
            } else {
                lastSequenceAdded = "";
            }
            previousChar = c;

            // Check character behind and character ahead
            if(stringIndex - 1 >=0 && stringIndex + 1 < input.length()) {
                if(input.charAt(stringIndex-1) == input.charAt(stringIndex+1)) {
                    hasMirrorSequence = true;
                }
            }
            stringIndex++;
        }

        // Two letter sequence check
        boolean hasRepeatingTwoLetterSequence = false;
        for(Integer numberOfSequence : twoLetterSequences.values()) {
            if(numberOfSequence > 1) {
                hasRepeatingTwoLetterSequence = true;
                break;
            }
        }

        return hasMirrorSequence && hasRepeatingTwoLetterSequence;
    }

    public static void testIsNice() {
        if(isNice("ugknbfddgicrmopn")) System.out.println("First example OK");
        if(isNice("aaa")) System.out.println("Second example OK");
        if(!isNice("jchzalrnumimnmhp")) System.out.println("Third example OK");
        if(!isNice("haegwjzuvuyypxyu")) System.out.println("Fourth example OK");
        if(!isNice("dvszwmarrgswjxmb")) System.out.println("Fifth example OK");
    }

    public static void testIsVeryNice() {
        if(isVeryNice("qjhvhtzxzqqjkmpb")) System.out.println("Sixth example OK");
        if(isVeryNice("xxyxx")) System.out.println("Seventh example OK");
        if(!isVeryNice("uurcxstgmygtbstg")) System.out.println("Eighth example OK");
        if(!isVeryNice("ieodomkazucvgmuy")) System.out.println("Ninth example OK");

        // Testing overlap
        if(isVeryNice("ieodoaaaaucvgmuy")) System.out.println("Overlap test OK");
    }

    public static void main(String[] args) throws IOException {
        testIsNice();
        testIsVeryNice();
        firstProblem();
        secondProblem();
    }
}
