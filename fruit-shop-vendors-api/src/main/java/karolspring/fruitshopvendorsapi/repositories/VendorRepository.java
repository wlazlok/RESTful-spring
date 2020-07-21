package karolspring.fruitshopvendorsapi.repositories;

import karolspring.fruitshopvendorsapi.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
