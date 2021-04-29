package com.github.chat.utils;

import com.github.chat.payload.Envelope;
import com.github.chat.payload.Token;
import com.github.chat.payload.Topic;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

public class JsonHelperTest {

    public static final String envelopStr = "{\"topic\":\"auth\",\"payload\":\"test\"}";

    public static final Envelope envelope = new Envelope (Topic.auth, "test");

    @Test
    public void toJson() {
        Optional<String> result = JsonHelper.toJson(envelope);
        String act = result.orElseThrow();
        Assert.assertEquals(envelopStr, act);
    }

    @Test
    public void toJson2() {
        Token token = new Token(1L, "Aaaa", "Bbbb", new Date().getTime(), new Date().getTime());
        String data = TokenProvider.encode(token);
        System.out.println("1----" + data);
        Envelope env = new Envelope(Topic.auth, data);
        System.out.println("2-----" + env);
        String result = JsonHelper.toJson(env).get();
        Optional<String> result2 = JsonHelper.toJson(token);
        System.out.println("3-------" + result);
        System.out.println("4-----" + result2);
        //String act = result.get();
        Envelope acte = JsonHelper.fromJson(result, Envelope.class).get();
        Assert.assertEquals(env, acte);
    }

    @Test
    public void fromJson() {
        Optional<Envelope> result = JsonHelper.fromJson(envelopStr, Envelope.class);
        Envelope act = result.orElseThrow();
        Assert.assertEquals(envelope, act);
    }

    @Test
    public void fromJson2() {
        Token token = new Token(1L, "Aaaa", "Bbbb", new Date().getTime(), new Date().getTime());
        String cipherToken = TokenProvider.encode(token);
        Envelope env = new Envelope(Topic.auth, cipherToken);
        String res = JsonHelper.toJson(env).orElseThrow();
        String payload = "{\"topic\":\"auth\",\"payload\":\"" + cipherToken + "\"}";
        Assert.assertEquals(payload, res);
    }
}