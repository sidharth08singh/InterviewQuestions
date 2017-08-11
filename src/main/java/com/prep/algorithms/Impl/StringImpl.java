package com.prep.algorithms.Impl;

/**
 * Created by Sidharth1 on 7/16/17.
 */
public class StringImpl {
    /**
     * Check if a string is composed of unique characters
     * Assumption: string is composed of ASCII characters.
     * @param str
     * @return boolean
     */
    public static boolean doesThisASCIIStringHaveUniqueCharacters(String str) {
        boolean[] charset = new boolean[255]; //boolean array of size 255
        for (int i = 0; i < str.length(); i++) {
            if(charset[Character.getNumericValue(str.charAt(i))] == true) {
                return false;
            }
            charset[Character.getNumericValue(str.charAt(i))] = true;
        }
        return true;
    }

    public static StringBuffer reverseString(StringBuffer str) {
        return reverseString(str, 0, str.length()-1);
    }

    public static StringBuffer reverseString(StringBuffer str, int start, int end) {
        if (start < end) {
            char temp = str.charAt(start);
            str.setCharAt(start, str.charAt(end));
            str.setCharAt(end, temp);
            reverseString(str, start+1, end-1);
        }
        return str;
    }

    public static StringBuffer reverseStringIteratively(StringBuffer str) {
        int start = 0;
        int end = str.length() - 1;
        while (start < end) {
            char temp = str.charAt(start);
            str.setCharAt(start, str.charAt(end));
            str.setCharAt(end, temp);
            start++;
            end--;
        }
        return str;
    }

    /**
     * Case sensitivity and whitespaces are important.
     * "Chelsea" is not a permutation of "chelsea"
     * " Chelsea" is not a permutation of "Chelsea"
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isPermutation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        boolean[] charSet = new boolean[256];
        for(int i=0; i<str1.length(); i++) {
            charSet[(int)str1.charAt(i)] = true;
        }
        for(int i=0; i<str2.length(); i++) {
            charSet[(int)str2.charAt(i)] = false;
        }
        for(int i=0; i<charSet.length; i++) {
            if (charSet[i] == true) {
                return false;
            }
        }
        return true;
    }

    /**
     * Replace each whitespace in a string with XXX
     * It is okay to return a new string - so go ahead and use string buffer
     * T = O(n) - n is the length of input string.
     * S = O(n) - n is the length of input string.
     * @param str
     * @return
     */
    public static StringBuffer replaceSpaceWithXXX(StringBuffer str) {
        StringBuffer newstr = new StringBuffer();
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != ' ') {
                newstr.append(str.charAt(i));
            }
            else {
                newstr.append("XXX");
            }
        }
        return newstr;
    }

    //ama*zon**

    /**
     * Given a string with exact length and extra spaces.
     * ama*zon**
     * @param str
     * @return
     */
    public static String replaceSpaceWith123(String str, int length) {
        int spacecount = 0;
        char[] charArr = str.toCharArray();

        for (int i = 0; i < length; i++) {
            if (charArr[i] == ' ') {
                spacecount++;
            }
        }

        int newlength = length + spacecount*2;

        for(int i = length-1; i>=0; i--) {
            if(charArr[i] == ' ') {
                charArr[newlength-1] = '3';
                charArr[newlength-2] = '2';
                charArr[newlength-3] = '1';
                newlength = newlength -3;
            }
            else {
                charArr[newlength-1] = charArr[i];
                newlength--;
            }
        }
        return String.valueOf(charArr);
    }

    public static StringBuffer compress(String str) {
        if(str.length() == 0) {
            return null;
        }
        char[] charArr = str.toCharArray();
        char tracker = charArr[0];
        int count = 1;
        StringBuffer compressedStr = new StringBuffer();
        int index = 0;

        for (int i = 1; i < charArr.length; i++) {
            if (charArr[i] == tracker) {
                count++;
            }
            else {
                compressedStr.append(tracker);
                compressedStr.append(count);
                count = 1;
                tracker = charArr[i];
                index  = index + 2;
            }
        }
        compressedStr.append(tracker);
        compressedStr.append(count);
        return compressedStr;
    }

    public static int[][] rotate2DArray(int[][] matrix, int n) {
       int level = 0;
       int last = n-1;
       int totalLevels = n/2;
       int temp;
       while(level < totalLevels) {
           for (int i = 0; i < last; i++) {
                //swap matrix[level][i] with matrix[i][last]
                temp = matrix[level][i];
                matrix[level][i] = matrix[i][last];
                matrix[i][last] = temp;

               //swap matrix[level][i] with matrix[last][last - i + level]
               temp = matrix[level][i];
               matrix[level][i] = matrix[last][last - i + level];
               matrix[last][last - i + level] = temp;

               //swap matrix[level][i] with matrix[last - i + level][level]
               temp = matrix[level][i];
               matrix[level][i] = matrix[last - i + level][level];
               matrix[last - i + level][level] = temp;
           }
           level++;
           last--;
       }
       return matrix;
    }

    public static int[][] create2DMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = 1;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = count;
                count++;
            }
        }
        return matrix;
    }

    public static void print2DMatrix(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int MATRIX_SIZE = 4;
        String s = new String("abcdf");

        System.out.println(StringImpl.doesThisASCIIStringHaveUniqueCharacters(s));

        System.out.println(StringImpl.reverseString(new StringBuffer("chelsea handler")));
        System.out.println(StringImpl.reverseStringIteratively(new StringBuffer("")));

        System.out.println(StringImpl.isPermutation(new String("Chelsea"), new String("Chelsea")));
        System.out.println(StringImpl.isPermutation(new String("Chelsea"), new String("chelsea")));
        System.out.println(StringImpl.isPermutation(new String("Chelsea Handler"), new String("ChelseaHandler")));
        System.out.println(StringImpl.isPermutation(new String(""), new String(" ")));

        System.out.println(StringImpl.replaceSpaceWithXXX(new StringBuffer("Chelsea Handler")));

        System.out.println(StringImpl.replaceSpaceWith123(new String("ama zon  "), 7));

        System.out.println(StringImpl.compress(new String("aaabcdddfff")));


        int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        matrix = create2DMatrix(MATRIX_SIZE);
        StringImpl.print2DMatrix(matrix, MATRIX_SIZE);
        matrix = StringImpl.rotate2DArray(matrix, MATRIX_SIZE);
        StringImpl.print2DMatrix(matrix, MATRIX_SIZE);

//        StringImpl.print2DMatrix(create2DMatrix(4), 4);
//        matrix = StringImpl.rotate2DArray(matrix, 4);
//        StringImpl.print2DMatrix(matrix, 4);

    }
}
