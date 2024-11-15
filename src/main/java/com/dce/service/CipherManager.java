package com.dce.service;

import javax.crypto.Cipher;

public interface CipherManager {
    Cipher getEncryptCipher();

    Cipher getDecryptCipher();
}
