package com.github.chat.utils;

import com.github.chat.payload.Token;

import java.util.Optional;

public class TokenProvider {



    public static String encode(Token t) {
        Optional<String> str = JsonHelper.toJson(t);
        //   TODO encoding
        return str.orElse(null);
    }

    public static Token decoding(String str) {
        return JsonHelper.fromJson(str, Token.class).orElse(null);
    }
}
