package karolspring.fruitshopvendorsapi.api.v1.models;

import karolspring.fruitshopvendorsapi.models.Vendor;

import java.util.List;

public class VendorDTOList {

    List<VendorDTO> vendors;

    public VendorDTOList(List<VendorDTO> vendors) {
        this.vendors = vendors;
    }

    public List<VendorDTO> getVendors() {
        return vendors;
    }

    public void setVendors(List<VendorDTO> vendors) {
        this.vendors = vendors;
    }
}
