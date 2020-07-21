package karolspring.fruitshopvendorsapi.boostrap;

import karolspring.fruitshopvendorsapi.models.Vendor;
import karolspring.fruitshopvendorsapi.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {

    private final VendorRepository vendorRepository;

    public LoadData(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Vendor vendorKarol = new Vendor();
        vendorKarol.setName("Karol Fruit Shop");

        vendorRepository.save(vendorKarol);
    }
}
