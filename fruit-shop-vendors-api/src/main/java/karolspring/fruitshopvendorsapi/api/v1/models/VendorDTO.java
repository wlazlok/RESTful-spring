package karolspring.fruitshopvendorsapi.api.v1.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {

    private String URL = "/api/vendors/";

    private Long id;

    @ApiModelProperty(value = "This is a name od Vendor")
    private String name;

    @ApiModelProperty(value = "This is the URL of Vendor")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = URL + this.id;
    }
}
