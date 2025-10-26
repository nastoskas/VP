package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryBookRepository implements BookRepository{

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
//        List<Book> bookList = new ArrayList<>();
//        List<Book> books = findAll();
//        for (Book b:books) {
//            if (b.getTitle().contains(text) && b.getAverageRating() >= rating){
//                bookList.add(b);
//            }
//        }
//        return bookList;
        return DataHolder.books.stream().filter(b -> b.getTitle().contains(text)
                && b.getAverageRating()>=rating).toList();
    }
}
