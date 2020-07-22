package karol.spring.webfluxrest.repositories;

import karol.spring.webfluxrest.models.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
