REACTIVE NEWS FEED WITH RXJAVA2
==============

Spring Boot application that implements a reactive News Feed 
using RxJava2.
The GUI is made using the framework Vaadin.

Modules:
========
- Spring Boot
- Vaadin - Java web framework - https://vaadin.com
- RxJava2

Build the jar:
-------------------------
./gradlew build

Run the jar:
-------------------------
java -jar build/libs/rxjava-newsfeed-0.0.1-SNAPSHOT.jar

How to use the application:
-------------------------
Connect to the server via http://localhost:8080, choose between 
Publisher (send the news) and Subscriber (receive the news) 
and start sending news.
One publisher and one or more subscribers are required.

You can see that the application is reactive, any time the publisher
sends a message the subscribers are are automatically updated.
