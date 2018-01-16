package com.ch.common.context;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Cxy on 2018/1/16.
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
