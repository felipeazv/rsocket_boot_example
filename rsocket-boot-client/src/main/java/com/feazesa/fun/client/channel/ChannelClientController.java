package com.feazesa.fun.client.channel;

import com.feazesa.fun.client.ClientController;
import com.feazesa.fun.event.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@Log4j2
public class ChannelClientController implements ClientController {
    private final RSocketRequester requester;

    @GetMapping(value = "/channel/{name}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Publisher<Response> channel(@PathVariable String name) {

        final var flux = Flux
                .fromStream(Stream.of(name.split("")).map(Response::new));

        return this.requester
                .route("channel")
                .data(flux)
                .retrieveFlux(Response.class)
                .doOnNext(re -> log.info("received:  {} at {}", re, Instant.now()));
    }
}