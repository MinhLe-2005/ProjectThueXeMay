package com.mycompany.smartride.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    /**
     * Hashes a plaintext password using BCrypt.
     * 
     * @param plainTextPassword the password to hash
     * @return the BCrypt hashed password
     */
    public static String hashPassword(String plainTextPassword) {
        if (plainTextPassword == null) {
            return null;
        }
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }

    /**
     * Checks a plaintext password against a hashed one.
     * Supports backward compatibility with plaintext passwords.
     * 
     * @param plainTextPassword the password to check
     * @param hashedPassword    the hashed (or old plaintext) password from the
     *                          database
     * @return true if the passwords match, false otherwise
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        if (plainTextPassword == null || hashedPassword == null) {
            return false;
        }

        // Check if the stored password looks like a BCrypt hash
        if (hashedPassword.startsWith("$2a$") || hashedPassword.startsWith("$2b$")
                || hashedPassword.startsWith("$2y$")) {
            try {
                return BCrypt.checkpw(plainTextPassword, hashedPassword);
            } catch (Exception e) {
                return false;
            }
        } else {
            // Backward compatibility for old, unhashed passwords
            return plainTextPassword.equals(hashedPassword);
        }
    }
}
