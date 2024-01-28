package com.musalasoft.drones.util;
import java.security.SecureRandom;

public class SerialNumberGenerator {
    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SERIAL_NUMBER_MAX_LENGTH = 100;

    public static String generateSerialNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder serialNumber = new StringBuilder();

        for (int i = 0; i < SERIAL_NUMBER_MAX_LENGTH; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            serialNumber.append(randomChar);
        }

        return serialNumber.toString();
    }
}
