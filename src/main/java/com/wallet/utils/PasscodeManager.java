package com.wallet.utils;

import java.util.Random;

/**
 * PasscodeManager generates and stores a constant 6-digit passcode.
 * The same value is accessible globally across the test framework.
 */
public class PasscodeManager {

    private static String passcode;

    // Prevent instantiation
    private PasscodeManager() {}

    /**
     * Generates a random 6-digit passcode if not already set.
     */
    public static String generatePasscode() {
        if (passcode == null) {
            Random random = new Random();
            int code = 100000 + random.nextInt(900000); // ensures 6 digits
            passcode = String.valueOf(code);
        }
        return passcode;
    }

    /**
     * Returns the stored 6-digit passcode.
     * Ensure generatePasscode() is called at least once before using this.
     */
    public static String getPasscode() {
        if (passcode == null) {
            generatePasscode();
        }
        return passcode;
    }

    /**
     * Allows manually setting the passcode (optional).
     */
    public static void setPasscode(String customPasscode) {
        if (customPasscode.matches("\\d{6}")) {
            passcode = customPasscode;
        } else {
            throw new IllegalArgumentException("Passcode must be a 6-digit number.");
        }
    }
}
