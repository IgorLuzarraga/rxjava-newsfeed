package com.example.springboot.vaadin.rxjavanewsfeed.frontend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class BuildStartLayoutTests {

    @Mock
    private BuildNewsLayout buildNewsLayout;

    @InjectMocks
    private BuildStartLayout buildStartLayout;

    @Test
    public void whenButtonSubscriberClickedThenCallStartNewsWithFalse(){
        //when
        buildStartLayout.eventClickSubscriberButton();

        // and then the startNews function from buildNewsLayout should be called
        // to start the News Feed (Subscriber side)
        then(buildNewsLayout).should().startNews(false);
    }

    @Test
    public void whenButtonPublisherClickedThenCallStartNewsWithTrue(){
        //when
        buildStartLayout.eventClickPublisherButton();

        // and then the startNews function from buildNewsLayout should be called
        // to start the News Feed (Publisher side)
        then(buildNewsLayout).should().startNews(true);
    }
}
