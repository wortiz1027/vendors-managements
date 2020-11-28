package co.edu.javeriana.vendors.infraestructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface Repository<T> {

    Optional<T> findById(String id);
    Optional<List<T>> findByAll();
    CompletableFuture<String> create(T data);
    Optional<List<T>> findVendorsByIds(String ids);

}