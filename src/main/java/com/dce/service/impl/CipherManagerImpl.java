package com.dce.service.impl;

import com.dce.service.CipherFactory;
import com.dce.service.CipherManager;
import com.dce.service.KeyService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;

@Service
public class CipherManagerImpl implements CipherManager {
    @Getter
    private final Cipher encryptCipher;
    @Getter
    private final Cipher decryptCipher;

    public CipherManagerImpl(CipherFactory cipherFactory, KeyService keyService) {
        this.encryptCipher = cipherFactory.genCipher(keyService.getPublicKey(), CipherFactoryImpl.KeyMode.ENCRYPT);
        this.decryptCipher = cipherFactory.genCipher(keyService.getPrivateKey(), CipherFactoryImpl.KeyMode.DECRYPT);
    }
}
