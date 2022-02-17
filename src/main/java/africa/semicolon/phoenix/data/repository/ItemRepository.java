package africa.semicolon.phoenix.data.repository;

import africa.semicolon.phoenix.data.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
