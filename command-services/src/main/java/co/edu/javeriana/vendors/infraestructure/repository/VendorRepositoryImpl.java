package co.edu.javeriana.vendors.infraestructure.repository;

import co.edu.javeriana.vendors.domain.Status;
import co.edu.javeriana.vendors.domain.Vendor;
import co.edu.javeriana.vendors.domain.VendorTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor
public class VendorRepositoryImpl implements VendorRepository {

    private final JdbcTemplate template;

    @Override
    public Optional<Vendor> findById(String id) {
        try {
            String sql = "SELECT * " +
                         "FROM VENDORS WHERE ID_PROVIDER = ?";
            return template.queryForObject(sql,
                    new Object[]{id},
                    (rs, rowNum) ->
                            Optional.of(new Vendor(
                                                    rs.getString("ID_PROVIDER"),
                                                    rs.getString("NAME_PROVIDER"),
                                                    rs.getString("NIT"),
                                                    new VendorTypes(rs.getString("ID_TYPE"), ""),
                                                    rs.getString("ADDRESS"),
                                                    rs.getString("TELEPHONE"),
                                                    rs.getString("EMAIL"),
                                                    rs.getString("ID_COUNTRY"),
                                                    rs.getString("ID_PROVINCE"),
                                                    rs.getString("ID_CITY"),
                                                    ""
                                        ))
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public CompletableFuture<String> create(Vendor vendor) {
        try {
            if (findById(vendor.getIdProvider()).isPresent()) return CompletableFuture.completedFuture(Status.EXIST.name());

            String sql = "INSERT INTO VENDORS (ID_PROVIDER, " +
                                              "NAME_PROVIDER, " +
                                              "NIT, " +
                                              "ID_TYPE, " +
                                              "ADDRESS, " +
                                              "TELEPHONE, " +
                                              "EMAIL, " +
                                              "ID_COUNTRY, " +
                                              "ID_PROVINCE, " +
                                              "ID_CITY) " +
                         "VALUES (?,?,?,?,?,?,?,?,?,?)";

            template.update(sql,
                    vendor.getIdProvider(),
                    vendor.getNameProvider(),
                    vendor.getNit(),
                    vendor.getTypes().getIdType(),
                    vendor.getAddress(),
                    vendor.getTelephone(),
                    vendor.getEmail(),
                    vendor.getIdCountry(),
                    vendor.getIdProvince(),
                    vendor.getIdCity());

            return CompletableFuture.completedFuture(Status.CREATED.name());
        } catch(Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(Status.ERROR.name());
        }
    }
}
