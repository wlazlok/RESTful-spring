package karol.spring.webfluxrest.boostrap;

import karol.spring.webfluxrest.models.Category;
import karol.spring.webfluxrest.models.Vendor;
import karol.spring.webfluxrest.repositories.CategoryRepository;
import karol.spring.webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoad implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public DataLoad(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryRepository.count().block() == 0){
            //load data
            System.out.println("##### LOADING DATA #####");

            Category category = new Category();
            category.setDescription("Bread");
            categoryRepository.save(category).block();

            Category category1 = new Category();
            category1.setDescription("Milk");
            categoryRepository.save(category1).block();

            Category category2 = new Category();
            category2.setDescription("Pizza");
            categoryRepository.save(category2).block();

            System.out.println("Loaded categories " + categoryRepository.count().block());

            Vendor vendor = new Vendor();
            vendor.setFirstName("Karol");
            vendor.setLastName("Wlazlo");
            vendorRepository.save(vendor).block();

            Vendor vendor1 = new Vendor();
            vendor1.setFirstName("Patrycja");
            vendor1.setLastName("Wlazlo");
            vendorRepository.save(vendor1).block();

            System.out.println("Loaded vendors " + vendorRepository.count().block());
        }
            //vendorRepository.deleteAll().block();
            //categoryRepository.deleteAll().block();
    }
}
