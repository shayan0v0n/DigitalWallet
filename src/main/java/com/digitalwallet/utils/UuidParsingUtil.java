package com.digitalwallet.utils;

import java.util.UUID;

public class UuidParsingUtil {
    public static UUID parseUUID(String uuidStr, String errorMessage) {
        try {
            return UUID.fromString(uuidStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(errorMessage, e);
        }
    }
}
