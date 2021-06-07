package com.github.chat.utils;

import com.github.chat.payload.Envelope;
import com.github.chat.payload.Topic;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class JsonHelperTest {

    public static final String envelopStr = "{\"topic\":\"auth\",\"payload\":\"test\"}";

    public static final Envelope envelope = new Envelope (Topic.auth, "nickName", 1, "test");

    @Test
    public void toJson() {
        Optional<String> result = JsonHelper.toJson(envelope);
        String act = result.orElseThrow();
        Assert.assertEquals(envelopStr, act);
    }

    @Test
    public void toJson2() {
        long jwtId = 123;
        String jwtIssuer = "First";
        String jwt = TokenProvider.encode(jwtId,jwtIssuer);
        Claims claims = TokenProvider.decode(jwt);
        Envelope env = new Envelope(Topic.auth, "nickName", 1, jwt);
        String result = JsonHelper.toJson(env).get();
        Optional<String> result2 = JsonHelper.toJson(jwt);
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
        long jwtId = 123;
        String jwtIssuer = "First";
        String jwt = TokenProvider.encode(jwtId,jwtIssuer);
        Claims claims = TokenProvider.decode(jwt);
        Envelope env = new Envelope(Topic.auth, "nickName", 1, jwt);
        String res = JsonHelper.toJson(env).orElseThrow();
        String payload = "{\"topic\":\"auth\",\"payload\":\"" + jwt + "\"}";
        Assert.assertEquals(payload, res);
    }
}