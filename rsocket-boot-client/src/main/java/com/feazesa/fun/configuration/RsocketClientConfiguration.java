package com.feazesa.fun.configuration;

import io.rsocket.frame.decoder.PayloadDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Log4j2
@Configuration
public class RsocketClientConfiguration {
    @Value("${rsocket.client.address}")
    private String address;
    @Value("${rsocket.client.port}")
    private int port;

//    @Bean
//    public RSocket rSocket() {
//        return RSocketFactory
//                .connect()
//                .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
//                .frameDecoder(PayloadDecoder.ZERO_COPY)
//                .transport(TcpClientTransport.create(address, port))
//                .start()
//                .doOnNext(socket -> log.info("🚀 Connected to RSocket"))
//                .block();
//    }
//
//
//    @Bean
//    public RSocketRequester rSocketRequester1(RSocketStrategies rSocketStrategies) {
//        return RSocketRequester.wrap(rSocket(), MimeTypeUtils.APPLICATION_JSON, MimeTypeUtils.APPLICATION_JSON, rSocketStrategies);
//    }

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder b) {
        return b
                .rsocketFactory(clientRSocketFactory -> clientRSocketFactory.frameDecoder(PayloadDecoder.ZERO_COPY))
                .connectTcp(address, port).block();
    }

//    @Bean
//    RSocketRequester rSocketRequester() {
//        return RSocketRequester.builder()
//                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
//                .rsocketFactory(clientRSocketFactory -> {
//                    clientRSocketFactory.frameDecoder(PayloadDecoder.ZERO_COPY);
//                })
//                .connect(TcpClientTransport.create(TcpClient.create().port(port).secure()))
//                .block();
//    }
}
