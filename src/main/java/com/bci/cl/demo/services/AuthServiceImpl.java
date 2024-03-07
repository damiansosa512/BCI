package com.bci.cl.demo.services;

import com.bci.cl.demo.entity.UserEntity;
import com.bci.cl.demo.exception.UserNotFoundError;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private final UserServiceImpl userService;

    public AuthServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    private static final String SECRET_KEY = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";
    private static final long EXPIRATION_TIME = 3600000;

    public Map<String, Object> generateToken(String username, String pass) {
        UserEntity userEntity = getUser(username, pass);
        String jwtToken = "";
        Date p = new Date(System.currentTimeMillis() + 3600000L);
        jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .claim("authorities", userEntity.getRol().getName())
                .setExpiration(p)
                .signWith(getSignInKey()
                        , SignatureAlgorithm.HS256).compact();
        Map<String, Object> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("access_token", jwtToken);
        jwtTokenGen.put("token_type", "Bearer");
        jwtTokenGen.put("expires_in", EXPIRATION_TIME / 100);
        return jwtTokenGen;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private UserEntity getUser(String username, String pass) {
        Optional<UserEntity> userEntity = userService.findByNameAndPassword(username, pass);
        if (userEntity.isPresent()) {
            return userEntity.get();
        }
        throw new UserNotFoundError(1, "El usuario no se encuentra registrado en el sistema");
    }

    public String checkToken(String token) {
        // Verificar el JWT
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token.substring("Bearer ".length()));
        Claims body = claims.getBody();
        return body.get("authorities", String.class);
    }

}
