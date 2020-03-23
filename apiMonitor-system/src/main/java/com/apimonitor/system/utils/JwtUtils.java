package com.apimonitor.system.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;


import java.util.Date;
import java.util.Map;

@ConfigurationProperties("jwt.config")
public class JwtUtils {
    // 签名私钥
    private String key;
    // 签名的失效时间
    private Long ttl;

    /**
     * 设置认证token
     */

    public String createJwt(String id, String username, Map<String,Object> map){
        //1、设置失效时间
        long now=System.currentTimeMillis();//当前时间
        long exp=now+ttl;
        //2、创建jwtBuilder
        JwtBuilder jwtBuilder= Jwts.builder().setId(id).setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,key);

        //3、根据map设置claims
        for(Map.Entry<String,Object> entry: map.entrySet()){
            jwtBuilder.claim(entry.getKey(),entry.getValue());
        }
        jwtBuilder.setExpiration(new Date(exp));
        //4、创建token
        String token=jwtBuilder.compact();
        return token;
    }

    /**
     * 解析token，获得clamis
     */
    public Claims parseJwt(String token){
        Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}
