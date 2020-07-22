package karol.spring.webfluxrest.repositories;

import karol.spring.webfluxrest.models.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
