package com.dce.data;

import lombok.Data;

import java.util.Arrays;

@Data
public class EncryptionResponseMessage {
    public EncryptionResponseMessage(byte[] byteData) {
        this.data = Arrays.toString(byteData);
    }
    private final String data;
}
