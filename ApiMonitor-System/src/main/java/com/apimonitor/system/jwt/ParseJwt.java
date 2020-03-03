package com.apimonitor.system.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseJwt {
    /**
     * psvm 快速生成main方法
     */
    public static void main(String[] args) {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoidGVzdCIsImlhdCI6MTU4MzIxNjEzMX0.m5CyBI2UhMSGIHAsS5D5A_AeepekSO8JzC3lHZ8YGZo";
        Claims claims=Jwts.parser().setSigningKey("YXBp").parseClaimsJws(token).getBody();

        //私有数据存放在claims
        System.out.println(claims.getId()+","+ claims.getSubject()+","+claims.getIssuedAt());

    }
}
