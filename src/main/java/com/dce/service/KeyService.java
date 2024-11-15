package com.dce.service;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface KeyService {
    PublicKey getPublicKey();

    PrivateKey getPrivateKey();
}
