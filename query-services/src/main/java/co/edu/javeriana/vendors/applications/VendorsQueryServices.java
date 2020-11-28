package co.edu.javeriana.vendors.applications;

import co.edu.javeriana.vendors.domain.Response;
import co.edu.javeriana.vendors.domain.ResponseVendor;
import co.edu.javeriana.vendors.domain.ResponseVendorTypes;

import java.util.concurrent.CompletableFuture;

public interface VendorsQueryServices {

    CompletableFuture<Response> getAllVendor();
    CompletableFuture<ResponseVendor> getVendorById(String id);
    CompletableFuture<ResponseVendorTypes> getAllVendorTypes();
    CompletableFuture<Response> getVendorByIds(String ids);

}
