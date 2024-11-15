package com.dce.service.impl;

import com.dce.config.CipherConfig;
import com.dce.service.CipherFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.AsymmetricKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class CipherFactoryImpl implements CipherFactory {

    @RequiredArgsConstructor
    public enum KeyMode {
        ENCRYPT(1),
        DECRYPT(2);
        @Getter
        private final int value;
    }

    private final CipherConfig cipherConfig;
    private final String cipherTransform;

    public CipherFactoryImpl(CipherConfig cipherConfig) {
        this.cipherConfig = cipherConfig;
        cipherTransform = cipherConfig.getAlgorithm() + "/" + cipherConfig.getMode() + "/" + cipherConfig.getPadding();
    }

    public String getAlgorithm() {
        return cipherConfig.getAlgorithm();
    }

    public Cipher genCipher(AsymmetricKey key, KeyMode keyMode) {
        Cipher instance;
        try {
            instance = Cipher.getInstance(cipherTransform);
            instance.init(keyMode.value, key);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}
