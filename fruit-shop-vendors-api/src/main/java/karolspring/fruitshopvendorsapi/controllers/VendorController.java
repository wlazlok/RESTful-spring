package karolspring.fruitshopvendorsapi.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import karolspring.fruitshopvendorsapi.api.v1.models.VendorDTO;
import karolspring.fruitshopvendorsapi.api.v1.models.VendorDTOList;
import karolspring.fruitshopvendorsapi.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is my Vendor Controller")
@RestController
@RequestMapping("/api/vendors/")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "This will get a list of Vendors", notes = "some notes")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorDTOList getAllVendors(){
        System.out.println(vendorService.getAllVendors().size());
        return new VendorDTOList(vendorService.getAllVendors());
    }

    @ApiOperation(value = "This will crate a new Vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }

    @ApiOperation(value = "This will get Vendor by ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "This will delete Vendor by ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendorById(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }

    @ApiOperation(value = "This will update a Vendor")
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){

        return vendorService.updateVendorById(id, vendorDTO);
    }
}
