package co.edu.javeriana.vendors.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Estructura para el manejo de la informacion del vendor")
public class Response implements java.io.Serializable {

    @ApiModelProperty(notes = "Campo con el codigo de informacion del estado de la transaccion")
    private String status;

    @ApiModelProperty(notes = "Campo con la descripcion de la transaccion")
    private String description;

}