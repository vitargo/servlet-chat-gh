package com.github.chat.utils;

import com.github.chat.exceptions.CryptoException;
import com.github.chat.payload.Token;
import org.junit.Assert;
import org.junit.Test;

public class TokenProviderTest {

    Token t = new Token(1L, "Bbbb", System.currentTimeMillis() + 1800000, System.currentTimeMillis());
    Token tAnother = new Token(2L, "dfshjdkhfs", System.currentTimeMillis() + 1800000, System.currentTimeMillis());
    Token tNull = null;

    @Test
    public void encode (){
        String cipher = TokenProvider.encode(t);
        Token token = TokenProvider.decode(cipher);
        Assert.assertEquals(t,token);
    }

    @Test
    public void encodeAnother (){
        String cipher = TokenProvider.encode(tAnother);
        Token token = TokenProvider.decode(cipher);
        Assert.assertEquals(tAnother,token);
    }

    @Test (expected = CryptoException.class)
    public void encodeNull (){
        String cipher = TokenProvider.encode(tNull);
        Token token = TokenProvider.decode(cipher);
    }
}