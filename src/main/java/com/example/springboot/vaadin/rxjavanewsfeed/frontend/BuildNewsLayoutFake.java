package com.example.springboot.vaadin.rxjavanewsfeed.frontend;


public class BuildNewsLayoutFake implements IBuildNewsLayout {

    private boolean publisherLayout;

    @Override
    public void startNews(boolean publisherLayout) {
        this.publisherLayout = publisherLayout;
        System.out.println("\n\n\nHello World!\n\n\n");
    }
}
