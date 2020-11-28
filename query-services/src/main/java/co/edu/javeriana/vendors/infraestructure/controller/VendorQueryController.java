package co.edu.javeriana.vendors.infraestructure.controller;

import co.edu.javeriana.vendors.applications.VendorsQueryServices;
import co.edu.javeriana.vendors.domain.Response;
import co.edu.javeriana.vendors.domain.ResponseVendor;
import co.edu.javeriana.vendors.domain.ResponseVendorTypes;
import co.edu.javeriana.vendors.domain.StatusCodes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(value="Consulta de vendors que tiene convenio con toures balon")
public class VendorQueryController {

    private final VendorsQueryServices services;

    @ApiOperation(value = "Consulta de vendors en el sistema", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitos de vendors"),
            @ApiResponse(code = 400, message = "Error en los datos de entrada no se envio informacion del vendors"),
            @ApiResponse(code = 500, message = "Error interno en el servidor, contacte y reporte con el administrador")
    })
    @GetMapping("/vendors")
    public ResponseEntity<CompletableFuture<Response>> all() throws ExecutionException, InterruptedException, UnknownHostException {
        CompletableFuture<Response> rs = services.getAllVendor();

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.SUCCESS.name()))
            return new ResponseEntity<>(rs, HttpStatus.OK);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.EMPTY.name()))
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.ERROR.name()))
            return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Consulta de vendors en el sistema", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitos de vendors"),
            @ApiResponse(code = 400, message = "Error en los datos de entrada no se envio informacion del vendors"),
            @ApiResponse(code = 500, message = "Error interno en el servidor, contacte y reporte con el administrador")
    })
    @GetMapping("/vendors/{id}/detail")
    public ResponseEntity<CompletableFuture<ResponseVendor>> detail(@PathVariable(required = true) String id) throws ExecutionException, InterruptedException, UnknownHostException {
        CompletableFuture<ResponseVendor> rs = services.getVendorById(id);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.SUCCESS.name()))
            return new ResponseEntity<>(rs, HttpStatus.OK);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.EMPTY.name()))
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.ERROR.name()))
            return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Consulta de vendors en el sistema", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitos de vendors"),
            @ApiResponse(code = 400, message = "Error en los datos de entrada no se envio informacion del vendors"),
            @ApiResponse(code = 500, message = "Error interno en el servidor, contacte y reporte con el administrador")
    })
    @GetMapping("/vendors/types")
    public ResponseEntity<CompletableFuture<ResponseVendorTypes>> detail() throws ExecutionException, InterruptedException, UnknownHostException {
        CompletableFuture<ResponseVendorTypes> rs = services.getAllVendorTypes();

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.SUCCESS.name()))
            return new ResponseEntity<>(rs, HttpStatus.OK);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.EMPTY.name()))
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.ERROR.name()))
            return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Consulta de vendors en el sistema", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitos de vendors"),
            @ApiResponse(code = 400, message = "Error en los datos de entrada no se envio informacion del vendors"),
            @ApiResponse(code = 500, message = "Error interno en el servidor, contacte y reporte con el administrador")
    })
    @PostMapping("/vendors/informations")
    public ResponseEntity<CompletableFuture<Response>> informations(@RequestParam(name = "ids") String ids) throws ExecutionException, InterruptedException, UnknownHostException {
        CompletableFuture<Response> rs = services.getVendorByIds(ids);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.SUCCESS.name()))
            return new ResponseEntity<>(rs, HttpStatus.OK);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.EMPTY.name()))
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);

        if (rs.get().getStatus().getCode().equalsIgnoreCase(StatusCodes.ERROR.name()))
            return new ResponseEntity<>(rs, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
    }

}
