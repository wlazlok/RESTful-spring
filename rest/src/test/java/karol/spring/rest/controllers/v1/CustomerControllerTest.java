package karol.spring.rest.controllers.v1;

import karol.spring.rest.api.v1.model.CustomerDTO;
import karol.spring.rest.models.Customer;
import karol.spring.rest.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static karol.spring.rest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getListOfCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(1L);
        customer1.setFirstName("Karol");
        customer1.setLastName("Wlazlo");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(1L);
        customer2.setFirstName("Patrycja");
        customer2.setLastName("Wlazlo");

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/api/v1/customers/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("Test");
        customerDTO.setLastName("User");

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Test")));
    }

    @Test
    void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("Karol");
        customerDTO.setLastName("Test");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setFirstName(customerDTO.getFirstName());

        when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/customers/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Karol")))
                .andExpect(jsonPath("$.lastName", equalTo("Test")));
    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Karol");
        customer.setLastName("Test");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setFirstName(customer.getFirstName());

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/customers/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Karol")))
                .andExpect(jsonPath("$.lastName", equalTo("Test")));
    }

    @Test
    void deleteCustomer() throws Exception {

        mockMvc.perform(delete("/api/v1/customers/1")
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());
    }
}