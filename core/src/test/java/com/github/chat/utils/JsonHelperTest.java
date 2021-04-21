package com.github.chat.utils;

import com.github.chat.payload.Envelope;
import com.github.chat.payload.Topic;
import org.junit.Assert;
import org.junit.Test;

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
    public void fromJson() {
        Optional<Envelope> result = JsonHelper.fromJson(envelopStr, Envelope.class);
        Envelope act = result.orElseThrow();
        Assert.assertEquals(envelope, act);
    }
}