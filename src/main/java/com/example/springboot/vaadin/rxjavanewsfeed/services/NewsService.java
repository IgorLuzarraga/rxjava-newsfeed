package com.example.springboot.vaadin.rxjavanewsfeed.services;

import com.example.springboot.vaadin.rxjavanewsfeed.domain.NewsMessage;
import io.reactivex.functions.Consumer;

public interface NewsService {
    public void onNext(NewsMessage newsMessage);
    public void subscribe(Consumer<? super NewsMessage> onNext);
}