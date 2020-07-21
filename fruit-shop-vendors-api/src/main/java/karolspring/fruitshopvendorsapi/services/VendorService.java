package karolspring.fruitshopvendorsapi.services;

import karolspring.fruitshopvendorsapi.api.v1.models.VendorDTO;
import karolspring.fruitshopvendorsapi.models.Vendor;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO getVendorById(Long id);

    void deleteVendorById(Long id);

    VendorDTO updateVendorById(Long id, VendorDTO vendorDTO);
}
