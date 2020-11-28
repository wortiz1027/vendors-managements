package co.edu.javeriana.vendors.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Estructura para el manejo de la informacion del vendor")
public class ResponseVendor {

    @ApiModelProperty(notes = "Campo que indica el codigo del estado de la transaccion")
    private Status status;

    @ApiModelProperty(notes = "Detalle del vendor registradas en toures balon")
    private Vendor vendor;

}
