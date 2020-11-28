package co.edu.javeriana.vendors.infraestructure.controller;

import co.edu.javeriana.vendors.application.VendorsCommandService;
import co.edu.javeriana.vendors.domain.Response;
import co.edu.javeriana.vendors.domain.Status;
import co.edu.javeriana.vendors.domain.Vendor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(value="Registro de vendors que tiene convenio con toures balon")
public class VendorAddCommandController {

    private final VendorsCommandService service;

    @ApiOperation(value = "Creacion de vendors en el sistema", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creacion exitosa del vendors"),
            @ApiResponse(code = 400, message = "Error en los datos de entrada no se envio informacion del vendors"),
            @ApiResponse(code = 500, message = "Error interno en el servidor, contacte y reporte con el administrador")
    })
    @PostMapping("/vendors")
    public ResponseEntity<CompletableFuture<Response>> handle(@RequestBody Vendor data) throws ExecutionException, InterruptedException {

        if (data == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        CompletableFuture<Response> rs = service.createVendor(data);

        if (rs.get().getStatus().equalsIgnoreCase(Status.CREATED.name()))
            return new ResponseEntity<>(rs, HttpStatus.CREATED);

        if (rs.get().getStatus().equalsIgnoreCase(Status.ERROR.name()))
            return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(rs, HttpStatus.CONFLICT);
    }

}
