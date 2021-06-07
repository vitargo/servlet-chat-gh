package com.github.chat.utils;

import com.github.chat.exceptions.CryptoException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class TokenProvider {

    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final long TERM = 604800000;

    private static final String SECRET_KEY = "PinkLink";

    public static String encode(long id, String nickname) {
        if(nickname == null || id < 0){
            throw new CryptoException ("Not valid user!");
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(String.valueOf(id))
                .setIssuedAt(now)
                .setIssuer(nickname)
                .signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + TERM;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return builder.compact();
    }

    public static Claims decode(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(jwt).getBody();
        } catch (MalformedJwtException | AssertionError e) {
            log.warn("Token is not a JWT format!");
            return null;
        }

        return claims;
    }
}
