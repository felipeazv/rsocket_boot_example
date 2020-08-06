package com.feazesa.fun.server.requeststream;

import com.feazesa.fun.event.Request;
import com.feazesa.fun.event.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Controller
@Log4j2
public class RequestStreamServerController {

    @MessageMapping("requestStream")
    public Flux<Response> requestStream(Request request) {
        log.info("received: {} at {}", request, Instant.now());
        return Flux
                .fromStream(Stream.generate(() -> new Response("Welcome " + request.getName())))
                .delayElements(Duration.ofSeconds(1));
    }
}