package co.edu.javeriana.vendors.application;

import co.edu.javeriana.vendors.domain.Response;
import co.edu.javeriana.vendors.domain.Vendor;

import java.util.concurrent.CompletableFuture;

public interface VendorsCommandService {

    CompletableFuture<Response> createVendor(Vendor vendor);

}
