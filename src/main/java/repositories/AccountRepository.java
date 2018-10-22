package repositories;

import entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account save(Account account);

    Optional<Account> findById(String id);
}
