package co.edu.javeriana.vendors.events;

import co.edu.javeriana.vendors.domain.StatusCodes;
import co.edu.javeriana.vendors.domain.Vendor;
import co.edu.javeriana.vendors.infraestructure.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(EventHandler.class);

    private final Repository<Vendor> repository;

    @RabbitListener(queues = "${events.amqp.queue}")
    public void consumer(final Vendor data) {
        LOG.info("recibiendo: {}", data);

        Optional<Vendor> image = repository.findById(data.getIdProvider());

        if (data.getStatus().equalsIgnoreCase(StatusCodes.CREATED.name()) && image.isEmpty()) {
            this.repository.create(data);
        }

        LOG.info("Vendor with code [{}] has been saved", data.getIdProvider());

    }

}
