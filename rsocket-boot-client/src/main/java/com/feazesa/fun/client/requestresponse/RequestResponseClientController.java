package com.feazesa.fun.client.requestresponse;

import com.feazesa.fun.client.ClientController;
import com.feazesa.fun.event.Request;
import com.feazesa.fun.event.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class RequestResponseClientController implements ClientController {
    private final RSocketRequester requester;

    @GetMapping("/requestResponse/{name}")
    Mono<Response> requestResponse(@PathVariable String name) {
        return this.requester.route("requestResponse")
                .data(new Request(name))
                .retrieveMono(Response.class);
    }
}