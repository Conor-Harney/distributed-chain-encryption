package com.dce.service.impl;

import com.dce.config.KeyConfig;
import com.dce.service.CipherFactory;
import com.dce.service.KeyService;
import lombok.Getter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
public class KeyServiceImpl implements KeyService {

    private final KeyConfig keyConfig;

    private final CipherFactory cipherFactory;

    @Getter
    private final PublicKey publicKey;
    @Getter
    private final PrivateKey privateKey;

    public KeyServiceImpl(KeyConfig keyConfig, CipherFactory cipherFactory) {
        this.keyConfig = keyConfig;
        this.cipherFactory = cipherFactory;
        publicKey = loadPublicKey();
        privateKey = loadPrivateKey();
    }


    private PublicKey loadPublicKey() {
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance(cipherFactory.getAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] pem = loadPem(keyConfig.getPublicKeyPath());
        try {
            return keyFactory.generatePublic(new X509EncodedKeySpec(pem));
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private PrivateKey loadPrivateKey() {
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance(cipherFactory.getAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] pem = loadPem(keyConfig.getPrivateKeyPath());
        try {
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(pem));
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] loadPem(String keyPath) {
        PemObject pemObject;
        try (PemReader pemReader = new PemReader(new FileReader(keyPath))) {
            pemObject = pemReader.readPemObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pemObject.getContent();
    }
}
