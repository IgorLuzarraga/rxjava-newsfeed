package com.example.springboot.vaadin.rxjavanewsfeed.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class BuildStartLayout extends VerticalLayout{

    private HorizontalLayout twoBottonsLayout;
    private VerticalLayout startLayout;
    private Button subscriberButton;
    private Button publisherButton;
    private Label explanationLabel;
    final private String PUBLISHER_BUTTON_TEXT = "Publish News";
    final private String SUBSCRIBER_BUTTON_TEXT = "Receive News";
    final private String EXPLANATION_LABEL_TEXT = "Choose to send news or receive them. At least one publisher is needed!";

    private IBuildNewsLayout buildNewsLayout;

    @Autowired
    public BuildStartLayout(IBuildNewsLayout buildNewsLayout) {
        this.buildNewsLayout = buildNewsLayout;
        buildLayout();
        addListeners();
    }

    // Just used for testing
    public void eventClickPublisherButton(){
        publisherButton.click();
    }

    // Just used for testing
    public void eventClickSubscriberButton(){
        subscriberButton.click();
    }

    private void addStartLayout(){
        add(startLayout);
    }

    private void removeStartLayout(){
        remove(startLayout);
    }

    private void buildLayout(){
        twoBottonsLayout = new HorizontalLayout();
        startLayout = new VerticalLayout();
        publisherButton = new Button(PUBLISHER_BUTTON_TEXT);
        subscriberButton = new Button(SUBSCRIBER_BUTTON_TEXT);
        explanationLabel = new Label(EXPLANATION_LABEL_TEXT);

        twoBottonsLayout.add(publisherButton, subscriberButton);
        startLayout.add(explanationLabel, twoBottonsLayout);

        addStartLayout();
    }

    private void addListeners(){
        publisherButton.addClickListener(click -> eventForPublisher());
        subscriberButton.addClickListener(click -> eventForSubscriber());
    }

    private void eventForPublisher(){
        removeStartLayout();

        // Start the News Feed
        buildNewsLayout.startNews(true);
    }

    private void eventForSubscriber(){
        removeStartLayout();

        // Start the News Feed
        buildNewsLayout.startNews(false);
    }
}
