package karol.spring.rest.boostrap;


import karol.spring.rest.models.Category;
import karol.spring.rest.models.Customer;
import karol.spring.rest.repositories.CategoryRepository;
import karol.spring.rest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BoostrapData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public BoostrapData(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();
        loadCategory();
    }

    public void loadCategory() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data loaded: " + categoryRepository.count());
    }

    public void loadCustomers(){
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Karol");
        customer1.setLastName("Wlazlo");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Patrycja");
        customer2.setLastName("Wlazlo");

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        System.out.println("Customer loaded: " + customerRepository.count());
    }
}
