package com.chq.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author Ricardo
 * @since 2026/3/11 20:25
 */
public class JwtUtils {
    
    /**
     * JWT 签名密钥（Base64 编码，长度为 128 位，满足 HS256 算法要求）
     */
    private static String signKey = "SVRIRUlNQQ==SVRIRUlNQQ==SVRIRUlNQQ==SVRIRUlNQQ==";
    
    /**
     * JWT 令牌有效期（毫秒）
     * 默认值为 43200000 毫秒（12 小时）
     */
    private static Long expire = 43200000L;

    /**
     * 生成 JWT 令牌
     * 使用 HMAC-SHA256 算法进行签名，设置指定的声明和过期时间
     *
     * @param claims JWT 负载数据（包含用户自定义的键值对信息）
     *               例如：{"id": 1, "username": "admin", "role": "USER"}
     * @return 生成的 JWT 令牌字符串，格式为：header.payload.signature
     *         header 包含算法信息，payload 包含声明数据，signature 是签名部分
     */
    public static String generateJwt(Map<String, Object> claims) {
        // 从签名密钥生成安全的 Key 对象
        Key key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));

        // 构建并签名 JWT 令牌
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析 JWT 令牌
     * 验证签名并提取 JWT 中的声明信息
     *
     * @param jwt JWT 令牌字符串（由 generateJwt 方法生成的完整令牌）
     *            格式：header.payload.signature
     * @return Claims 对象，包含 JWT 中的所有声明数据
     *         可通过 getSubject()、get()、getExpiration() 等方法访问具体字段
     * @throws io.jsonwebtoken.JwtException 当 JWT 格式错误、签名验证失败或已过期时抛出
     */
    public static Claims parseJWT(String jwt) {
        // 从签名密钥生成安全的 Key 对象
        Key key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));

        // 使用签名密钥验证并解析 JWT
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}