package co.edu.javeriana.vendors.infraestructure.repository.vendors;

import co.edu.javeriana.vendors.domain.StatusCodes;
import co.edu.javeriana.vendors.domain.Vendor;
import co.edu.javeriana.vendors.domain.VendorTypes;
import co.edu.javeriana.vendors.infraestructure.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class VendorRepositoryImpl implements Repository<Vendor> {

    private static final Logger LOG = LoggerFactory.getLogger(VendorRepositoryImpl.class);

    private final JdbcTemplate template;

    @Override
    public Optional<List<Vendor>> findByAll() {
        try{
            String sql = "SELECT * " +
                         "FROM VENDORS V INNER  JOIN VENDOR_TYPES VT on V.ID_TYPE = VT.TYPES_ID";
            List<Vendor> types = this.template.query(sql, new VendorRowMapper());
            return Optional.of(types);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vendor> findById(String id) {
        try {
            String sql = "SELECT * " +
                         "FROM VENDORS V INNER  JOIN VENDOR_TYPES VT on V.ID_TYPE = VT.TYPES_ID " +
                         "WHERE V.ID_PROVIDER = ?";
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
            if (findById(vendor.getIdProvider()).isPresent()) return CompletableFuture.completedFuture(StatusCodes.EXIST.name());

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

            return CompletableFuture.completedFuture(StatusCodes.CREATED.name());
        } catch(Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(StatusCodes.ERROR.name());
        }
    }

}
