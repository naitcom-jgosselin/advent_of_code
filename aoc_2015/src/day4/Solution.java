package day4;

import java.lang.Integer;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class Solution {

    /*
    Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically
    forward-thinking little girls and boys.

    To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5
    hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you
    must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.

    Your puzzle input is ckczppom.
     */

    private static final String PUZZLE_INPUT = "ckczppom";

    public static void firstProblem() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        int lowestPossibleNumber = -1;
        for(int i = 1; i<Integer.MAX_VALUE; i++) {
            String stringToHash = PUZZLE_INPUT + i;

            byte[] md5Digest = messageDigest.digest(stringToHash.getBytes());
            StringBuilder hexStringBuilder  = new StringBuilder();

            for(byte b : md5Digest) {
                hexStringBuilder.append(String.format("%02x", b));
            }

            String hexString = hexStringBuilder.toString();

            if(hexString.startsWith("00000")) {
                lowestPossibleNumber = i;
                break;
            }
        }
        System.out.println("Lowest possible number : " + lowestPossibleNumber);
    }

    public static void secondProblem() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        int lowestPossibleNumber = -1;
        for(int i = 1; i<Integer.MAX_VALUE; i++) {
            String stringToHash = PUZZLE_INPUT + i;

            byte[] md5Digest = messageDigest.digest(stringToHash.getBytes());
            StringBuilder hexStringBuilder  = new StringBuilder();

            for(byte b : md5Digest) {
                hexStringBuilder.append(String.format("%02x", b));
            }

            String hexString = hexStringBuilder.toString();

            if(hexString.startsWith("000000")) {
                lowestPossibleNumber = i;
                break;
            }
        }
        System.out.println("Lowest possible number : " + lowestPossibleNumber);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        firstProblem();
        secondProblem();
    }
}
