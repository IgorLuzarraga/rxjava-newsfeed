package com.example.springboot.vaadin.rxjavanewsfeed.services;

import com.example.springboot.vaadin.rxjavanewsfeed.domain.NewsMessage;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.ReplaySubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    private final ReplaySubject<NewsMessage> obs;

    @Autowired
    public NewsServiceImpl(ReplaySubject<NewsMessage> obs){
        this.obs = obs;
    }

    @Override
    public void onNext(NewsMessage newsMessage){
        obs.onNext(newsMessage);
    }

    @Override
    public void subscribe(Consumer<? super NewsMessage> onNext) {
        obs.subscribe(onNext);
    }
}
