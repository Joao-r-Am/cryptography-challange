package joaor.cryptography.cryptography.controllers;

import joaor.cryptography.cryptography.dto.CreditCardDto;
import joaor.cryptography.cryptography.entities.CreditCardEntity;
import joaor.cryptography.cryptography.services.CreditCardService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping()
    public ResponseEntity<CreditCardEntity> save(@RequestBody CreditCardDto cardDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(creditCardService.saveCard(cardDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CreditCardEntity>> getCard(@PathVariable("id") ObjectId id){
        return ResponseEntity.status(HttpStatus.OK).body(creditCardService.getCard(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardEntity> updateCard(@PathVariable("id") ObjectId id, @RequestBody CreditCardDto cardDto){
        return ResponseEntity.status(HttpStatus.OK).body(creditCardService.updateCard(id, cardDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCard(@PathVariable("id") ObjectId id){
        creditCardService.deleteCard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("CARTÃO DELETADO");
    }

}
