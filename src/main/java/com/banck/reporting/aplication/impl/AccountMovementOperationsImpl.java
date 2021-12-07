package com.banck.reporting.aplication.impl;

import com.banck.reporting.aplication.AccountMovementOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.banck.reporting.domain.DateIDateF;
import com.banck.reporting.domain.ProductMovementDto;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.tcp.TcpClient;

/**
 *
 * @author jonavcar
 */
@Service
@RequiredArgsConstructor
public class AccountMovementOperationsImpl implements AccountMovementOperations {

    org.slf4j.Logger logger = LoggerFactory.getLogger(AccountMovementOperationsImpl.class);

    @Override
    public Flux<ProductMovementDto> listByCustomerAndDates(String customer, DateIDateF didf) {

        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 6000)
                .doOnConnected(connection
                        -> connection.addHandlerLast(new ReadTimeoutHandler(3))
                        .addHandlerLast(new WriteTimeoutHandler(3)));

        WebClient webClient = WebClient.builder()
                .baseUrl("http://mov-account.southcentralus.azurecontainer.io:8083")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                //.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient))) // timeout
                .build();

        return webClient.post()
                .uri("/mov-account/product/movement/" + customer + "/list")
                .bodyValue(didf)
                .retrieve()
                .bodyToFlux(ProductMovementDto.class).flatMap(o -> {
            return Mono.just(o);
        });
    }

}
