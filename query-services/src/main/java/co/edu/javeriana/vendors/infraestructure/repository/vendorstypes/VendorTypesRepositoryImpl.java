package co.edu.javeriana.vendors.infraestructure.repository.vendorstypes;

import co.edu.javeriana.vendors.domain.Vendor;
import co.edu.javeriana.vendors.domain.VendorTypes;
import co.edu.javeriana.vendors.infraestructure.repository.Repository;
import co.edu.javeriana.vendors.infraestructure.repository.vendors.VendorRowMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class VendorTypesRepositoryImpl implements Repository<VendorTypes> {

    private static final Logger LOG = LoggerFactory.getLogger(VendorTypesRepositoryImpl.class);

    private final JdbcTemplate template;

    @Override
    public Optional<VendorTypes> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<VendorTypes>> findByAll() {
        try{
            String sql = "SELECT * " +
                         "FROM VENDOR_TYPES";
            List<VendorTypes> types = this.template.query(sql, new VendorTypesRowMapper());
            return Optional.of(types);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public CompletableFuture<String> create(VendorTypes data) {
        return null;
    }

    @Override
    public Optional<List<VendorTypes>> findVendorsByIds(String ids) {
        return Optional.empty();
    }
}
