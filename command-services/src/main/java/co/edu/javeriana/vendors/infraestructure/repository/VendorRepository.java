package co.edu.javeriana.vendors.infraestructure.repository;

import co.edu.javeriana.vendors.domain.Vendor;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface VendorRepository {

    Optional<Vendor> findById(String id);
    CompletableFuture<String> create(Vendor vendor);

}
