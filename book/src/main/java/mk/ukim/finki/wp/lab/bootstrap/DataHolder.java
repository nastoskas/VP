package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init(){
        books.add(new Book("Title 1","Genre 1", 8.5));
        books.add(new Book("Title 2","Genre 2", 6.5));
        books.add(new Book("Title 3","Genre 3", 8.0));
        books.add(new Book("Title 4","Genre 4", 9.5));
        books.add(new Book("Title 5","Genre 5", 5.5));
        books.add(new Book("Title 6","Genre 6", 6.5));
        books.add(new Book("Title 7","Genre 7", 5.0));
        books.add(new Book("Title 8","Genre 8", 10.0));
        books.add(new Book("Title 9","Genre 9", 7.2));
        books.add(new Book("Title 10","Genre 10", 7.0));
    }

}
