package com.example.springboot.vaadin.rxjavanewsfeed;

import com.example.springboot.vaadin.rxjavanewsfeed.domain.NewsMessage;
import io.reactivex.subjects.ReplaySubject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;


@Configuration
public class AppConfiguration {
    private final String NEWS_MSG_1 = "Science Channel --> First-Ever Image of Black Hole Revealed";
    private final String NEWS_MSG_2 = "Science Channel --> A 2014 meteor may have come from another solar system";
    private final String NEWS_MSG_3 = "Tech Channel --> PlayStation 5: Sony reveals first details of next-gen console";

    @Bean
    ReplaySubject<NewsMessage> observable() {
        ReplaySubject<NewsMessage> obs = ReplaySubject.create();

        // send some news as example
        obs.onNext(new NewsMessage(LocalDateTime.now(), NEWS_MSG_1));
        obs.onNext(new NewsMessage(LocalDateTime.now(), NEWS_MSG_2));
        obs.onNext(new NewsMessage(LocalDateTime.now(), NEWS_MSG_3));

        return obs;
    }
}
