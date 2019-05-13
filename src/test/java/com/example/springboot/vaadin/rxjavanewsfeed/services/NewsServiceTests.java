package com.example.springboot.vaadin.rxjavanewsfeed.services;

import com.example.springboot.vaadin.rxjavanewsfeed.domain.NewsMessage;
import io.reactivex.subjects.ReplaySubject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsServiceTests {
    private final String NEWS_MSG_SEND = "Science Channel --> First-Ever Image of Black Hole Revealed";

    private NewsService newsService;
    private ReplaySubject<NewsMessage> obs;
    private List<NewsMessage> newsMessageList;

    @Before
    public void setup(){
        obs = ReplaySubject.create();
        newsService = new NewsServiceImpl(obs);
        newsMessageList = new ArrayList<>();
    }

    @Test
    public void givenNewsMsg_whenNewsMsgSend_ThenTheReceiveNewsMsgIsTheSame(){

        // given a news message
        NewsMessage newsMessageSend = new NewsMessage(LocalDateTime.now(), NEWS_MSG_SEND);

        // when the news is send
        newsService.onNext(newsMessageSend);

        // then the receive news message is the same one
        newsService.subscribe(newsMessageList::add);
        NewsMessage newsMessageRecive = newsMessageList.get(0);

        assertEquals(newsMessageSend, newsMessageRecive);
    }
}
