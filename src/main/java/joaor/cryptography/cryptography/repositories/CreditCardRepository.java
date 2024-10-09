package joaor.cryptography.cryptography.repositories;

import joaor.cryptography.cryptography.entities.CreditCardEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditCardRepository extends MongoRepository<CreditCardEntity, ObjectId> {
}
