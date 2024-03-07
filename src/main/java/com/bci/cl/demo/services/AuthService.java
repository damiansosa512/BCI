package com.bci.cl.demo.services;

import java.util.Map;

public interface AuthService {

    Map<String, Object> generateToken(String username, String pass);

    public String checkToken(String token);
}
