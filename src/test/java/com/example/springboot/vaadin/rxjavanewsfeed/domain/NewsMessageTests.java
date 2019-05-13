package com.example.springboot.vaadin.rxjavanewsfeed.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
public class NewsMessageTests {

    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static String NEWS_MESSAGE = "News channel: Hello World!";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private LocalDateTime localDateTime;
    private String formatDateTime;
    private String expectedNews;
    private NewsMessage newsMessage;

    @Before
    public void setup(){
        localDateTime = LocalDateTime.now();
        formatDateTime = localDateTime.format(formatter);
        expectedNews = formatDateTime + " --> " + NEWS_MESSAGE;

        newsMessage = new NewsMessage(localDateTime, NEWS_MESSAGE);
    }

    @Test
    public void CheckTheNewsMsgIsCorrect(){
        assertEquals(expectedNews, newsMessage.getNewsMessage());
    }

    @Test
    public void CheckTheMsgIsCorrect(){
        assertEquals(NEWS_MESSAGE, newsMessage.getMessage());
    }

    @Test
    public void CheckTheDateTimeIsCorrect(){
        assertEquals(formatDateTime, newsMessage.getDateTime());
    }
}
