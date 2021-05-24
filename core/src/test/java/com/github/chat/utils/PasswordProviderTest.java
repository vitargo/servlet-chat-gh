package com.github.chat.utils;

import com.github.chat.exceptions.PasswordException;
import org.junit.Assert;
import org.junit.Test;

public class PasswordProviderTest {

    @Test
    public void encodePassReg(){
        String password = "PinkLink123";
        String[] hashPass = PasswordProvider.encodePassReg(password);
        String result = hashPass[1];
        String salt = hashPass[0];
        boolean r = PasswordProvider.checkPass(password, salt, result);
        Assert.assertTrue(r);
    }
    @Test (expected = PasswordException.class)
    public void encodePassReg2(){
        String password = null;
        String[] hashPass = PasswordProvider.encodePassReg(password);
        String result = hashPass[1];
        String salt = hashPass[0];
        boolean r = PasswordProvider.checkPass(password, salt, result);
        Assert.assertTrue(r);
    }
}