package co.edu.javeriana.vendors.infraestructure.repository.vendors;

import co.edu.javeriana.vendors.domain.Vendor;
import co.edu.javeriana.vendors.domain.VendorTypes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorRowMapper implements RowMapper<Vendor> {

    @Override
    public Vendor mapRow(ResultSet rs, int i) throws SQLException {
        Vendor vendor = new Vendor();

        vendor.setIdProvider(rs.getString("ID_PROVIDER"));
        vendor.setNameProvider(rs.getString("NAME_PROVIDER"));
        vendor.setNit(rs.getString("NIT"));
        vendor.setTypes(new VendorTypes(rs.getString("TYPES_ID"), rs.getString("DESCRIPTION")));
        vendor.setAddress(rs.getString("ADDRESS"));
        vendor.setTelephone(rs.getString("TELEPHONE"));
        vendor.setEmail(rs.getString("EMAIL"));
        vendor.setIdCountry(rs.getString("ID_COUNTRY"));
        vendor.setIdProvince(rs.getString("ID_PROVINCE"));
        vendor.setIdCity(rs.getString("ID_CITY"));
        
        return vendor;
    }
}