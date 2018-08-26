package com.gupaoedu.user.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;
import java.util.UUID;

/**
 * @description: Jwt生成工具
 * @author: Wu Shuai
 * @create: 2018-08-26 11:43
 **/
public class JwtTokenUtils {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().
                toString().replace("-", ""));
    }

    public static String generatorToken(Map<String, Object> payLoad) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Jwts.builder().setPayload(objectMapper.writeValueAsString(payLoad))
                    .signWith(SignatureAlgorithm.HS256, generatorKey()).compact();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Key generatorKey() {
        SignatureAlgorithm saa = SignatureAlgorithm.HS256;
        byte[] bin = DatatypeConverter.parseBase64Binary
                ("f3973b64918e4324ad85acea1b6cbec5");
        Key key = new SecretKeySpec(bin, saa.getJcaName());
        return key;
    }

    public static Claims parseToken(String token) {
        Jws<Claims> claimsJwt = Jwts.parser().setSigningKey(generatorKey()).parseClaimsJws(token);

        return claimsJwt.getBody();
    }
}
