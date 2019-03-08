package net.ofnir.quarkus.config;

import net.ofnir.quarkus.CounterService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/greeting")
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);

    @Inject
    @ConfigProperty(name = "greeting.message")
    private String message;

    @Inject
    @ConfigProperty(name = "greeting.suffix", defaultValue = "!")
    private String suffix;

    @Inject
    @ConfigProperty(name = "greeting.name")
    private Optional<String> name;

    @Inject
    private CounterService counterService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message + ", " + name.orElse("World") + " #" + counterService.inc().toString() + " " + suffix;
    }

    void onMessage(@Observes Event msg) {
        LOGGER.info("XXX Got an message: " + msg.toString());
    }
}