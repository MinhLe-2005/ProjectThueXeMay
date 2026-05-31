package com.mycompany.smartride.constant;

import java.security.SecureRandom;

/**
 *
 * @author LeQuangMinh
 */
public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz"; 
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_+=<>?";
    private static final String ALL = UPPER + LOWER + DIGITS + SPECIAL;
    
    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        int length = 12; // Desired password length
        String password = generatePassword(length);
        System.out.println("Generated Password: " + password);
    }

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);

        // Ensure the password contains at least one character from each category
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        // Fill the remaining length with random characters from all categories
        for (int i = 4; i < length; i++) {
            password.append(ALL.charAt(random.nextInt(ALL.length())));
        }

        // Shuffle the characters to ensure randomness
        return shuffleString(password.toString());
    }
    //xáo chuỗi
    private static String shuffleString(String string) {
        char[] array = string.toCharArray();

        // Shuffle the array
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Swap the characters
            char temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }

        return new String(array);
    }
}
