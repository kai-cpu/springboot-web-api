package org.example.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    @Autowired
    private JwtConfig jwtConfig;

    private Key key;

    @PostConstruct
    public void init() {
        // 生成密钥
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecretKey()));
    }


    // 创建Token
    public String createJwt(Map<String,Object> claims) {
        long currentTime = System.currentTimeMillis();
        Date currentDate = new Date(currentTime);
        long expirationTime = currentTime + jwtConfig.getExpirationTime();
        Date expirationDate = new Date(expirationTime);

        JwtBuilder builder = Jwts.builder()
                .setIssuer(jwtConfig.getIssuer())       // 声明发行的主体
                .setSubject(jwtConfig.getSubject())     // 声明主题
                .setAudience(jwtConfig.getAudience())   // 声明受众人群
                .setExpiration(expirationDate)          // 声明到期时间（超过不受理）
                .setNotBefore(currentDate)              // 声明不早于时间（早于不受理）
                .signWith(key);                         // 签名密钥

        claims.forEach(builder::claim);                 // 添加断言
        return builder.compact();
    }

    // 解析Token
    public Jwt parseJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parse(token);
    }
}
