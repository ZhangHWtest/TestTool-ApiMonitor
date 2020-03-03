package com.apimonitor.system.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


public class CreateJwt {
    public static void main(String[] args){
        JwtBuilder jwtBuilder=Jwts.builder().setId("1").setSubject("test")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"YXBp");
        String token=jwtBuilder.compact();
        System.out.println("token:"+token);

    }
}
