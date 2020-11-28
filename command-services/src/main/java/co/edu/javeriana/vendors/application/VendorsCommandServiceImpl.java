package co.edu.javeriana.vendors.application;

import co.edu.javeriana.vendors.domain.Response;
import co.edu.javeriana.vendors.domain.Status;
import co.edu.javeriana.vendors.domain.Vendor;
import co.edu.javeriana.vendors.infraestructure.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class VendorsCommandServiceImpl implements VendorsCommandService {

    private static final Logger LOG = LoggerFactory.getLogger(VendorsCommandServiceImpl.class);

    @Value("${events.amqp.exchange}")
    String vendorExchange;

    @Value("${events.amqp.routing-key}")
    String vendorRoutingKey;

    private final VendorRepository repository;
    private final AmqpTemplate template;

    @Override
    public CompletableFuture<Response> createVendor(Vendor vendor) {
        Response response = new Response();
        try {
            String status = this.repository.create(vendor).get();
            response.setStatus(status);

            if (!status.equalsIgnoreCase(Status.CREATED.name())) {
                response.setDescription(String.format("The vendor with id: {%s} has an error", vendor.getIdProvider()));
                return CompletableFuture.completedFuture(response);
            }

            vendor.setStatus(Status.CREATED.name());
            this.template.convertAndSend(vendorExchange, vendorRoutingKey, vendor);
            response.setDescription(String.format("The vendor with id: {%s} has been created", vendor.getIdProvider()));
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            response.setStatus(Status.ERROR.name());
            response.setDescription(String.format("Exception creating row has been release: {%s}", e.getMessage()));
            return CompletableFuture.completedFuture(response);
        }
    }
}
