package com.example.employeeshub.services;

import com.example.employeeshub.models.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretkey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;
    public String generateToken(final UserModel userModel) {
        return buildToken(userModel, jwtExpiration);
    }

    public String generateRefreshToken(final UserModel userModel) {
        return buildToken(userModel, refreshExpiration);
    }

    private String buildToken(final UserModel userModel, final long expiration) {
        return Jwts.builder()
                .id(userModel.getId().toString())
                .claims(Map.of("name", userModel.getNombre())) //Informacion adiccional que queremos proporcionarle a nuestro token
                .subject(userModel.getEmail()) //Como sera identificado el usuario
                .issuedAt(new Date(System.currentTimeMillis()))//Cuando se creo la clave, basicamente son los milisegundos actuales de nuestro servidor
                .expiration(new Date(System.currentTimeMillis() + expiration)) // Cuando va a expirar el token
                .signWith(getSignInKey()) //La clave con la cual se registra
                .compact(); //Genera la clave, tanto para el registro / login como para refresh
    }

    //Metodo que genera una clave privada con la JWT SECRET KEY

    private SecretKey getSignInKey() {
        //Decodificamos la clave actual para asi transformarla a bytes.
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        //Nos genera la clave secreta utilizando el algoritmo hmacSHA
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
