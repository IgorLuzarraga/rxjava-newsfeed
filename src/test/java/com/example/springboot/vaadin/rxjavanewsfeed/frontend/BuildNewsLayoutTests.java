package com.example.springboot.vaadin.rxjavanewsfeed.frontend;

import com.example.springboot.vaadin.rxjavanewsfeed.domain.NewsMessage;
import com.example.springboot.vaadin.rxjavanewsfeed.services.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class BuildNewsLayoutTests {

    private final String NEWS_MSG_SEND = "Science Channel --> First-Ever Image of Black Hole Revealed";

    @Mock
    NewsService newsService;

    private BuildNewsLayout buildNewsLayout;

    @Before
    public void setup() {
        buildNewsLayout = new BuildNewsLayout(newsService);
        // build the layout as publisher
        buildNewsLayout.startNews(true);
    }

    @Test
    public void givenNewsMsg_whenButtonSendClicked_ThenCallServiceOnNextToSendTheNews() {

        // given a news message
        Optional<String> sendNewsMsg = Optional.of(NEWS_MSG_SEND);

        //when send the news
        buildNewsLayout.sendNewsMessageTest(sendNewsMsg);

        // then call service.onNext to send the News
        Optional<NewsMessage> newsMessageSend = buildNewsLayout.receiveNewsMessageTest();
        then(newsService).should().onNext(newsMessageSend.get());
    }

    @Test
    public void givenBuildNewsStarted_ThenCallServiceSubscribeToReceiveTheNews() {

        // then call service.subscribe to  receive the News
        then(newsService).should().subscribe(Mockito.any());
    }
}
