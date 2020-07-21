package karolspring.fruitshopvendorsapi.api.v1.mappers;

import karolspring.fruitshopvendorsapi.api.v1.models.VendorDTO;
import karolspring.fruitshopvendorsapi.models.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
