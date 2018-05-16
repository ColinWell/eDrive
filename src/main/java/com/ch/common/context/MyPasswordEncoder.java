package com.ch.common.context;

import com.ch.common.utils.encrypt.MD5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Cxy on 2018/1/16.
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return encodePassword(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(encodePassword(charSequence.toString()));
    }

    public static String encodePassword(String password){
        return MD5Utils.getMD5String(MD5Utils.getMD5String(password));
    }
}
