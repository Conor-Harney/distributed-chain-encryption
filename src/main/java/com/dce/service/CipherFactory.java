package com.dce.service;

import com.dce.service.impl.CipherFactoryImpl;

import javax.crypto.Cipher;
import java.security.AsymmetricKey;

public interface CipherFactory {
    String getAlgorithm();

    Cipher genCipher(AsymmetricKey key, CipherFactoryImpl.KeyMode keyMode);
}
