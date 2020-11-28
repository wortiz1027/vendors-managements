package co.edu.javeriana.vendors.applications;

import co.edu.javeriana.vendors.domain.*;
import co.edu.javeriana.vendors.infraestructure.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class VendorsQueryServicesImpl implements VendorsQueryServices {

    private static final Logger LOG = LoggerFactory.getLogger(VendorsQueryServicesImpl.class);

    private final Repository<Vendor> repository1;
    private final Repository<VendorTypes> repository2;

    @Override
    public CompletableFuture<Response> getAllVendor() {
        Response response = new Response();
        Status status = new Status();
        try {
            Optional<List<Vendor>> vendors = this.repository1.findByAll();

            if (!vendors.isPresent()) {
                status.setCode(StatusCodes.EMPTY.name());
                status.setDescription("There are not rows availables");
                response.setStatus(status);
                return CompletableFuture.completedFuture(response);
            }

            status.setCode(StatusCodes.SUCCESS.name());
            status.setDescription("There are rows availables");
            response.setStatus(status);
            response.setVendors(vendors.get());
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            status.setCode(StatusCodes.ERROR.name());
            status.setDescription(String.format("There is an error getting vendors type: %s", e.getMessage()));
            response.setStatus(status);
            return CompletableFuture.completedFuture(response);
        }
    }

    @Override
    public CompletableFuture<ResponseVendor> getVendorById(String id) {
        ResponseVendor response = new ResponseVendor();
        Status status = new Status();
        try {
            Optional<Vendor> vendor = this.repository1.findById(id);

            if (!vendor.isPresent()) {
                status.setCode(StatusCodes.EMPTY.name());
                status.setDescription(String.format("There is not image with id: %s", id));
                response.setStatus(status);
                return CompletableFuture.completedFuture(response);
            }

            status.setCode(StatusCodes.SUCCESS.name());
            status.setDescription("There are an row available");
            response.setStatus(status);
            response.setVendor(vendor.get());
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            status.setCode(StatusCodes.ERROR.name());
            status.setDescription(String.format("There is an error getting vendor detail: %s", e.getMessage()));
            response.setStatus(status);
            return CompletableFuture.completedFuture(response);
        }
    }

    @Override
    public CompletableFuture<ResponseVendorTypes> getAllVendorTypes() {
        ResponseVendorTypes response = new ResponseVendorTypes();
        Status status = new Status();
        try {
            Optional<List<VendorTypes>> types = this.repository2.findByAll();

            if (!types.isPresent()) {
                status.setCode(StatusCodes.EMPTY.name());
                status.setDescription("There are not rows availables");
                response.setStatus(status);
                return CompletableFuture.completedFuture(response);
            }

            status.setCode(StatusCodes.SUCCESS.name());
            status.setDescription("There are rows availables");
            response.setStatus(status);
            response.setTypes(types.get());
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            status.setCode(StatusCodes.ERROR.name());
            status.setDescription(String.format("There is an error getting vendors type: %s", e.getMessage()));
            response.setStatus(status);
            return CompletableFuture.completedFuture(response);
        }
    }

    @Override
    public CompletableFuture<Response> getVendorByIds(String ids) {
        Response response = new Response();
        Status status = new Status();
        try {
            Optional<List<Vendor>> vendors = this.repository1.findVendorsByIds(ids);

            if (!vendors.isPresent()) {
                status.setCode(StatusCodes.EMPTY.name());
                status.setDescription("There are not rows availables");
                response.setStatus(status);
                return CompletableFuture.completedFuture(response);
            }

            status.setCode(StatusCodes.SUCCESS.name());
            status.setDescription("There are rows availables");
            response.setStatus(status);
            response.setVendors(vendors.get());
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            status.setCode(StatusCodes.ERROR.name());
            status.setDescription(String.format("There is an error getting vendors type: %s", e.getMessage()));
            response.setStatus(status);
            return CompletableFuture.completedFuture(response);
        }
    }
}
