package com.github.chat.utils;

import io.jsonwebtoken.Claims;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TokenProviderTest {

    @Test
    public void decode (){
        long jwtId = 123L;
        String jwtIssuer = "First";
        String jwt = TokenProvider.encode(jwtId,jwtIssuer);
        Claims claims = TokenProvider.decode(jwt);
        assertEquals(String.valueOf(jwtId), claims.getId());
        assertEquals(jwtIssuer, claims.getIssuer());
    }

    @Test
    public void decodeAnother (){
        long jwtId = 1237428394729384L;
        String jwtIssuer = "Second time";
        String jwt = TokenProvider.encode(jwtId,jwtIssuer);
        Claims claims = TokenProvider.decode(jwt);
        assertEquals(String.valueOf(jwtId), claims.getId());
        assertEquals(jwtIssuer, claims.getIssuer());
    }

    @Test
    public void decodeShouldFail() {
        String notAJwt = "This is not a JWT";
        Claims claims = TokenProvider.decode(notAJwt);
        assertNull(claims);
    }
}