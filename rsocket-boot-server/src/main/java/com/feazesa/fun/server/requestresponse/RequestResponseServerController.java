package com.feazesa.fun.server.requestresponse;

import com.feazesa.fun.event.Request;
import com.feazesa.fun.event.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Controller
@Log4j2
public class RequestResponseServerController {
    @MessageMapping("requestResponse")
    public Mono<Response> requestResponse(Request request) {
        log.info("received: {} at {}", request, Instant.now());
        return Mono.just(new Response("Welcome " + request.getName()));
    }
}