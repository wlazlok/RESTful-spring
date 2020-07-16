package karol.spring.rest.api.v1.mapper;

import karol.spring.rest.api.v1.model.CustomerDTO;
import karol.spring.rest.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer CustomerDTOToCustomer(CustomerDTO customerDTO);
}
