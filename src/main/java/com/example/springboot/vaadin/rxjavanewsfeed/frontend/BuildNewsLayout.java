package com.example.springboot.vaadin.rxjavanewsfeed.frontend;

import com.example.springboot.vaadin.rxjavanewsfeed.domain.NewsMessage;
import com.example.springboot.vaadin.rxjavanewsfeed.services.NewsService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.reactivex.functions.Consumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringComponent
@UIScope
public class BuildNewsLayout extends VerticalLayout implements IBuildNewsLayout{
    private final String TEXT_SEND_BUTTON = "Send";
    private final String TEXT_MESSAGE_FIELD = "Write the News you want to send";
    private final String GRID_COLUMN_ONE = "dateTime";
    private final String GRID_COLUMN_TWO = "message";
    private VerticalLayout newsLayout;
    private HorizontalLayout fieldButtonLayout;
    private Grid<NewsMessage> grid;
    private TextField messageField;
    private Button sendButton;
    private boolean publisherLayout = true;
    private NewsService newsService;
    private Optional<NewsMessage> newsMessageToSend;

    @Autowired
    public BuildNewsLayout(NewsService newsService){
        this.newsService = newsService;
    }

    @Override
    public void startNews(boolean publisherLayout) {
        this.publisherLayout = publisherLayout;

        buildLayout();
        addListeners();

        // Receive the News msgs
        setNewsMsgsReceiver();
    }

    public Optional<NewsMessage> receiveNewsMessageTest(){
        return newsMessageToSend;
    }

    public void sendNewsMessageTest(Optional<String> newsMessage){
        newsMessage.ifPresent(news -> {
            messageField.setValue(news);
            sendButton.click();
        });
    }

    private void setNewsMsgsReceiver(){
        newsService.subscribe(SetNewsMsgsToTheGrid());
    }

    private Consumer<? super NewsMessage> SetNewsMsgsToTheGrid(){
        return newsMessage ->
                getUI().ifPresent(ui ->
                        ui.access(() -> grid.setItems(setNewsInList(newsMessage)))
                );
    }

    private Consumer<? super NewsMessage> SetNewsMsgsToTheGrid2(){
        return newsMessage -> grid.setItems(setNewsInList(newsMessage));
    }

    // Send the News msgs
    private void sendNewsMsg(){
        createNewsMessage()
                .filter(newsMsg -> !newsMsg.getMessage().equals(""))
                .ifPresent(newsMsg ->  newsService.onNext(newsMsg));
        messageField.clear();
        messageField.focus();
    }

    private List<NewsMessage> getNewsInGrid() {
        ListDataProvider<NewsMessage> ldp = (ListDataProvider) grid.getDataProvider();
        return new ArrayList<>(ldp.getItems());
    }

    private List<NewsMessage> setNewsInList(NewsMessage msg){
        List<NewsMessage> newsList = getNewsInGrid();
        newsList.add(msg);
        return newsList;
    }

    private void buildLayout() {
        newsLayout = new VerticalLayout();
        fieldButtonLayout = new HorizontalLayout();
        grid = new Grid<>(NewsMessage.class);
        messageField = new TextField();
        sendButton = new Button(TEXT_SEND_BUTTON);

        messageField.setPlaceholder(TEXT_MESSAGE_FIELD);

        grid.setHeight("300px");
        grid.setColumns(GRID_COLUMN_ONE, GRID_COLUMN_TWO);
        grid.getColumnByKey(GRID_COLUMN_ONE).setWidth("200px").setFlexGrow(0);

        sendButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        fieldButtonLayout.add(messageField, sendButton);
        fieldButtonLayout.setWidth("100%");
        fieldButtonLayout.expand(messageField);

        messageField.focus();

        if(publisherLayout) {
            newsLayout.add(fieldButtonLayout, grid);
        }else{
            newsLayout.add(grid);
        }

        add(newsLayout);
    }

    private void addListeners(){
        messageField.addKeyPressListener(Key.ENTER,
                click -> sendNewsMsg());

        sendButton.addClickListener(click -> sendNewsMsg());
    }

    private Optional<NewsMessage> createNewsMessage(){
        newsMessageToSend = Optional.ofNullable(new NewsMessage(LocalDateTime.now(), messageField.getValue()));
        return newsMessageToSend;
    }
}







