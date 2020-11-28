package co.edu.javeriana.vendors.infraestructure.repository.vendorstypes;

import co.edu.javeriana.vendors.domain.VendorTypes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorTypesRowMapper implements RowMapper<VendorTypes> {

    @Override
    public VendorTypes mapRow(ResultSet rs, int i) throws SQLException {
        VendorTypes types = new VendorTypes();

        types.setIdType(rs.getString("TYPES_ID"));
        types.setDescription(rs.getString("DESCRIPTION"));

        return types;
    }
}
