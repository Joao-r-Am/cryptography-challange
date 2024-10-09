package joaor.cryptography.cryptography.services;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    private final StringEncryptor encryptor;

    public EncryptionService(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }
    public String encryption(String val){
        return encryptor.encrypt(val);
    }

    public String decryption(String val){
        return encryptor.decrypt(val);
    }
}
