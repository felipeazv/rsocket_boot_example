package com.feazesa.fun.server.fireandforget;

import com.feazesa.fun.event.Request;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Controller
@Log4j2
public class FireAndForgetServerController {
    @MessageMapping("fireAndForget")
    public Mono<Void> fireAndForget(Request request) {
        log.info("received: {} at {}", request, Instant.now());
        return Mono.empty();
    }
}