package com.bci.cl.demo.util;

public class Util {
    public static String getToken(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }
}
