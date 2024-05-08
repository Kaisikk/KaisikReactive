package com.kaisikk.java.kaisikreactive.controller;

import com.kaisikk.java.kaisikreactive.domain.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/controller")
public class MainController {

    /**
     * Самый дефолтный реактивный контроллер
     *
     * @param start
     * @param count
     * @return Flux
     */
    @GetMapping
    public Flux<Message> list(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "3") Long count) {
        return Flux
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

    }

}
