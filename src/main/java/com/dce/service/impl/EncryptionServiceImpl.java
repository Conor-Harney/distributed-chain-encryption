package com.dce.service.impl;

import com.dce.service.CipherManager;
import com.dce.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EncryptionServiceImpl implements EncryptionService {

    private final CipherManager cipherManager;

    private final int plainChunkSize = 214;
    private final int encryptedChunkSize = 256;

    @Override
    public byte[] encrypt(byte[] data) {
        byte[] chunks = new byte[0];
        for (byte[] chunk : chunkData(data, plainChunkSize)) {
            try {
                byte[] encryptedChunk = cipherManager.getEncryptCipher().doFinal(chunk);
                chunks = join(chunks, encryptedChunk);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            }
        }
        return chunks;
    }

    @Override
    public String decrypt(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte[] chunk : chunkData(data, encryptedChunkSize)) {
            try {
                byte[] decryptedChunk = cipherManager.getDecryptCipher().doFinal(chunk);
                String decryptedStringData = new String(decryptedChunk, StandardCharsets.UTF_8);
                stringBuilder.append(decryptedStringData);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            }
        }
        return stringBuilder.toString();
    }

    public List<byte[]> chunkData(byte[] data, int chunkSize) {
        List<byte[]> chunks = new ArrayList<>();
        int numOfChunks = (int) ((Math.ceil((double) data.length / chunkSize)));
        for (int chunkIndex = 0; chunkIndex < numOfChunks; chunkIndex++) {
            int rangeStart = chunkIndex * chunkSize;
            int rangeEnd = Math.min(rangeStart + chunkSize, data.length);
            chunks.add(Arrays.copyOfRange(data, rangeStart, rangeEnd));
        }
        return chunks;
    }

    private byte[] join(byte[] left, byte[] right) {
        int oldEndIndex = left.length;
        int newEndIndex = oldEndIndex + right.length;
        // lengthen array
        byte[] retArray = Arrays.copyOf(left, newEndIndex);
        // Copy array right from index 0 to ret array.
        // Elements are inserted from index oldIndexEnd and copy the full length of the array right
        System.arraycopy(right, 0, retArray, oldEndIndex, right.length);
        return retArray;
    }
}
