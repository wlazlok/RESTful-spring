package karol.spring.rest.controllers.v1;

import karol.spring.rest.api.v1.model.CustomerDTO;
import karol.spring.rest.api.v1.model.CustomerListDTO;
import karol.spring.rest.models.Customer;
import karol.spring.rest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){ //telling Spring MVC to look at the request of the body and parse it
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){ //telling Spring MVC to look at the request of the body and parse it
        return new ResponseEntity<CustomerDTO>(
                customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){ //telling Spring MVC to look at the request of the body and parse it
        return new ResponseEntity<CustomerDTO>(
                customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomerById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
