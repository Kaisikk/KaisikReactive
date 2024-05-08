package com.kaisikk.java.kaisikreactive.handlers;

import com.kaisikk.java.kaisikreactive.domain.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {

    /**
     * Получение объектов в json
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> hello(ServerRequest request) {

        // получение параметров из запроса
        Long start = request.queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);
        Long count = request.queryParam("count")
                .map(Long::valueOf)
                .orElse(3L);

        // получаем список строк и маппим в объект вызвав его конструктор
        Flux<Message> data = Flux
                .just(
                        "Hello. reactive!",
                        "More then one",
                        "Third Post",
                        "Fourth post",
                        "Fifth post"
                )
                // указываем с какого элемента отдаем записи
                .skip(start)
                // количество записей
                .take(count)
                .map(Message::new);

        // возвращаем ответ
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(data, Message.class);
    }

    /**
     * Получение страницы с индексом
     *
     * @param serverRequest
     * @return Mono
     */
    public Mono<ServerResponse> index(ServerRequest serverRequest) {
        // получаем из запроса параметр
        String userName = serverRequest.queryParam("user")
                .orElse("Nobody");
        return ServerResponse
                .ok()
                // возвращем шаблон положим туда параметр
                .render("index", Map.of("user", userName));
    }
}
