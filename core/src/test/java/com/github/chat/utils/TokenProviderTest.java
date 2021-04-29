package com.github.chat.utils;

import com.github.chat.exceptions.CryptoException;
import com.github.chat.payload.Token;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TokenProviderTest {

    long dateStart = new GregorianCalendar(2019, Calendar.FEBRUARY, 11).getTime().getTime();
    long dateFinish = new GregorianCalendar(2023, Calendar.APRIL, 23).getTime().getTime();
    Token t = new Token(1L, "Aaaa","Bbbb", new Date().getTime(), new Date().getTime());
    Token tAnother = new Token(203L, "First","One", dateStart, dateFinish);
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