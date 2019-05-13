package com.example.springboot.vaadin.rxjavanewsfeed.frontend;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;

import  com.vaadin.flow.component.orderedlayout.VerticalLayout;

@StyleSheet("frontend://styles/styles.css")
@Route
@Push
public class MainView extends VerticalLayout {
    private final static String HEADER_TEXT = "REACTIVE NEWS FEED WITH RXJAVA";
    private final static String HEADER_THEME = "dark";
    private final static String FOOTER_THEME = "dark";
    private final static String CLASS_NAME = "main-view";

    private BuildStartLayout buildStartLayout;
    private IBuildNewsLayout buildNewsLayout;

    public MainView(BuildStartLayout buildStartLayout,
                    IBuildNewsLayout buildNewsLayout) {
        this.buildStartLayout = buildStartLayout;
        this.buildNewsLayout = buildNewsLayout;

        setUp();
        buildHeader();
        buildLayout();
        buildFooter();
    }

    private void setUp(){
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addClassName(CLASS_NAME);
    }

    private void buildHeader(){
        H1 header = new H1(HEADER_TEXT);
        header.getElement().getThemeList().add(HEADER_THEME);
        add(header);
    }

    private void buildLayout(){
        add((VerticalLayout)buildNewsLayout, buildStartLayout);
    }

    private void buildFooter(){
        Footer footer = new Footer();
        footer.getElement().getThemeList().add(FOOTER_THEME);
        add(footer);
    }
}
