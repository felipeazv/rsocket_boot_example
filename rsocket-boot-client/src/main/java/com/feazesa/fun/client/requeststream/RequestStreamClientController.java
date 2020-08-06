package com.feazesa.fun.client.requeststream;

import com.feazesa.fun.client.ClientController;
import com.feazesa.fun.event.Request;
import com.feazesa.fun.event.Response;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RequestStreamClientController implements ClientController {
    private final RSocketRequester requester;

    @GetMapping(value = "/requestStream/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Publisher<Response> requestStream(@PathVariable String name) {
        return this.requester.route("requestStream")
                .data(new Request(name))
                .retrieveFlux(Response.class);
    }
}