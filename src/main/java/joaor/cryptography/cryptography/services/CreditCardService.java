package joaor.cryptography.cryptography.services;

import joaor.cryptography.cryptography.dto.CreditCardDto;
import joaor.cryptography.cryptography.entities.CreditCardEntity;
import joaor.cryptography.cryptography.repositories.CreditCardRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final EncryptionService encrypt;

    public CreditCardService(CreditCardRepository creditCardRepository, EncryptionService encrypt) {
        this.creditCardRepository = creditCardRepository;
        this.encrypt = encrypt;
    }

    public CreditCardEntity saveCard(CreditCardDto cardDto){
        var entity = new CreditCardEntity();
        var userDoc = encrypt.encryption(cardDto.userDocument());
        var cardToken = encrypt.encryption(cardDto.creditCardToken());
        entity.setUserDocument(userDoc);
        entity.setCreditCardToken(cardToken);
        entity.setValue(cardDto.value());
        creditCardRepository.save(entity);
        return entity;
    }

    public Optional<CreditCardEntity> getCard(ObjectId id){
        Optional<CreditCardEntity> creditCard = creditCardRepository.findById(id);
        String decryptUserDoc = encrypt.decryption(creditCard.get().getUserDocument());
        String decryptCardToken = encrypt.decryption(creditCard.get().getCreditCardToken());

        creditCard.get().setUserDocument(decryptUserDoc);
        creditCard.get().setCreditCardToken(decryptCardToken);
        creditCard.get().setValue(creditCard.get().getValue());
        return creditCard;
    }

    public CreditCardEntity updateCard(ObjectId id, CreditCardDto cardDto){
        Optional<CreditCardEntity> creditCard = creditCardRepository.findById(id);
        String encryptUserDoc = encrypt.encryption(cardDto.userDocument());
        String encryptCardToken = encrypt.encryption(cardDto.creditCardToken());

        creditCard.get().setValue(cardDto.value());
        creditCard.get().setCreditCardToken(encryptCardToken);
        creditCard.get().setUserDocument(encryptUserDoc);
        var entity = creditCard.get();
        BeanUtils.copyProperties(creditCard, entity);
        creditCardRepository.save(entity);
        return entity;
    }

    public void deleteCard(ObjectId id){
        creditCardRepository.deleteById(id);
    }

}
