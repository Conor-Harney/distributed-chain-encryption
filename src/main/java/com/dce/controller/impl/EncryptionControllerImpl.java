package com.dce.controller.impl;

import com.dce.controller.EncryptionController;
import com.dce.data.DecryptionRequestBody;
import com.dce.data.EncryptionResponseMessage;
import com.dce.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EncryptionControllerImpl implements EncryptionController {

    private final EncryptionService service;

    @Override
    public EncryptionResponseMessage encrypt(@RequestBody String message) {
        log.debug("Post /encrypt");
        byte[] encrypt = service.encrypt(message.getBytes(StandardCharsets.UTF_8));
        return new EncryptionResponseMessage(encrypt);
    }

    @Override
    public String decrypt(@RequestBody DecryptionRequestBody decryptionRequestBody) {
        log.debug("Post /decrypt");
        return service.decrypt(decryptionRequestBody.getData());
    }
}
