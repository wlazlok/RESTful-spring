package karol.spring.rest.services;

import karol.spring.rest.api.v1.mapper.CustomerMapper;
import karol.spring.rest.api.v1.model.CustomerDTO;
import karol.spring.rest.models.Customer;
import karol.spring.rest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    void getAllCustomers() {

        when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer()));

        List<CustomerDTO> customersDTO = service.getAllCustomers();

        assertEquals(2, customersDTO.size());
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLastName("Test");
        customer.setFirstName("Karol");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer));

        //CustomerDTO customerDTO = service.getCustomerById(1L);

        //assertEquals("Karol", customerDTO.getFirstName());
    }

    @Test
    void createNewCustomer() {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Karol");
        customerDTO.setId(1L);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //CustomerDTO savedDTO = service.createNewCustomer(customerDTO);

        //System.out.printf(savedCustomer.getFirstName());

        //assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
    }

    @Test
    void saveCustomerByDTO() {
    }

    @Test
    void deleteCustomerById() {

        Long id = 1L;

        service.deleteCustomerById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}