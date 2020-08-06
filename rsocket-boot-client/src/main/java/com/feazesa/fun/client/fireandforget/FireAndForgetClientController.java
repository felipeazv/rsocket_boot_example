package com.feazesa.fun.client.fireandforget;

import com.feazesa.fun.client.ClientController;
import com.feazesa.fun.event.Request;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FireAndForgetClientController implements ClientController {
    private final RSocketRequester requester;

    @GetMapping("/fireAndForget/{name}")
    Publisher<Void> fireAndForget(@PathVariable String name) {
        return this.requester.route("fireAndForget")
                .data(new Request(name))
                .send();
    }
}