package com.dce.service;

public interface EncryptionService {
    byte[] encrypt(byte[] data);

    String decrypt(byte[] data);
}
