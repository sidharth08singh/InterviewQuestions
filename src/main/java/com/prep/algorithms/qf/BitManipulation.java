package com.prep.algorithms.qf;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * Created by sidharth1 on 8/2/17.
 */
public class BitManipulation {

    public static StringBuffer decimalToBinary(int number) {
        if (number > 0) {
            int remainder = number % 2;
            return decimalToBinary(number/2).append(remainder);
        }
        return new StringBuffer("");
    }

    public static int binaryToDecimal(String number) {
        int res = 0;
        int counter = number.length()-1;
        for (int i = 0; i<number.length(); i++) {
            res += Math.pow(2, counter) * Character.getNumericValue(number.charAt(i));
            counter--;
        }
        return res;
    }

    public static void main(String [] args) {
        System.out.println(decimalToBinary(9));
        System.out.println(binaryToDecimal("11010"));
    }
}