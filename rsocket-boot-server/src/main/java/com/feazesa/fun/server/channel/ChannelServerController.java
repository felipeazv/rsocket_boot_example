package com.feazesa.fun.server.channel;

import com.feazesa.fun.event.Request;
import com.feazesa.fun.event.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

@Controller
@Log4j2
public class ChannelServerController {

    @MessageMapping("channel")
    public Flux<Response> channel(Flux<Request> request) {
        return request
                .delayElements(Duration.ofMillis(200))
                .doOnNext(re -> log.info("received:  {} at {}", re, Instant.now()))
                .map(response -> new Response("Hello " + response.getName() + " @ " + Instant.now()));
    }
}