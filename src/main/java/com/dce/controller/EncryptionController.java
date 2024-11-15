package com.dce.controller;

import com.dce.data.DecryptionRequestBody;
import com.dce.data.EncryptionResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EncryptionController {
    @PostMapping("/encrypt")
    EncryptionResponseMessage encrypt(@RequestBody String message);

    @PostMapping("/decrypt")
    String decrypt(@RequestBody DecryptionRequestBody decryptionRequestBody);
}
