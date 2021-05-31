package com.example.contacts.gravatar;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

final class Utils {
    private static final StringBuilder sBuilder;
    private static final String SPECIAL_CHARS = " %$&+,/:;=?@<>#%";

    private Utils() {
    }

    private static String hex(final byte[] array) {
        Utils.sBuilder.setLength(0);
        for (final byte b : array) {
            Utils.sBuilder.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
        }
        return Utils.sBuilder.toString();
    }

    static String convertEmailToHash(final String email) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            return hex(messageDigest.digest(email.getBytes("UTF-8")));
        }
        catch (NoSuchAlgorithmException e) {
            return email;
        }
        catch (UnsupportedEncodingException e2) {
            return email;
        }
    }

    static String encode(final String input) {
        Utils.sBuilder.setLength(0);
        for (final char ch : input.toCharArray()) {
            if (isUnsafe(ch)) {
                Utils.sBuilder.append('%');
                Utils.sBuilder.append(toHex(ch / '\u0010'));
                Utils.sBuilder.append(toHex(ch % '\u0010'));
            }
            else {
                Utils.sBuilder.append(ch);
            }
        }
        return Utils.sBuilder.toString();
    }

    private static char toHex(final int ch) {
        return (char)((ch < 10) ? (48 + ch) : (65 + ch - 10));
    }

    private static boolean isUnsafe(final char ch) {
        return ch > '\u0080' || ch < '\0' || " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
    }

    static {
        sBuilder = new StringBuilder();
    }
}
