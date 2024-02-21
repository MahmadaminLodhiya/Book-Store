package com.BookStoreManagement.BookStore;

import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import com.BookStoreManagement.BookStore.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializar implements CommandLineRunner {
    @Autowired
    private AuthorRepository _Author;
    @Autowired
    private BookRepository _Book;
    @Override
    public void run(String... args) throws Exception {

        List<Author> authors = new ArrayList<>();

        authors.add(new Author("Emily Dickinson", "emily.dickinson@example.com"));
        authors.add(new Author("Mark Twain", "mark.twain@example.com"));
        authors.add(new Author("Jane Austen", "jane.austen@example.com"));
        authors.add(new Author("Ernest Hemingway", "ernest.hemingway@example.com"));
        authors.add(new Author("Virginia Woolf", "virginia.woolf@example.com"));
        authors.add(new Author("F. Scott Fitzgerald", "f.scott.fitzgerald@example.com"));
        authors.add(new Author("Charles Dickens", "charles.dickens@example.com"));
        authors.add(new Author("Harper Lee", "harper.lee@example.com"));
        authors.add(new Author("J.K. Rowling", "jk.rowling@example.com"));
        authors.add(new Author("George Orwell", "george.orwell@example.com"));

        for(Author author : authors)
            _Author.save(author);

        String[] titles = {
                "Rich dad Poor Dad","The Great Escape", "Into the Wild", "The Art of War", "Pride and Prejudice",
                "Richest Man in Babylon", "4 Hour Work Week", "How to Win Friends and Influence People", "The Catcher in the Rye",
                "Energize your Mind", "Moby Dick", "The 48 Laws of Power", "Rework", "The Secret",
                "Crime and Punishment", "The Laws of Happiness", "Atomic Habit", "Habit of Highly Effective Peoples",
                "Atomic Habit 2", "The Power of Subconscious Mind", "A Tale of Two Cities",
                "The Millionaire Fastlane ", "Think and Grow Rich", "The Psychology of Money",
                "Great Expectations", "The Scarlet Letter", "The Secret Garden", "The 5Am Club",
                "Dracula", "Deep Work", "Rich dad Poor Dad Latest",
                "Alices Adventures in Wonderland", "Little Women", "The Count of Monte Cristo",
                "Don Quixote", "The Alchemist", "A Midsummer Night's Dream", "Romeo and Juliet",
                "The Merchant of Venice", "Othello", "Rich dad Poor Dad",
                "The Iliad", "The Aeneid", "Paradise Lost", "Faust", "The Waste Land",
                "Leaves of Grass", "The Sun Also Rises", "Ikigai", "Mastery"
        };
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int publishedYear = 1970 + random.nextInt(50); // generate year between 1970 to 2020
            String title = titles[i];
            int authorId = 1+random.nextInt(10);
            String isbn = "978-" + (random.nextInt(900) + 100) + "-" + (random.nextInt(900) + 100) + "-" + (random.nextInt(9000) + 1000);
            int price = 100+ random.nextInt(901);

            _Book.save(new Book(publishedYear,title,authorId,isbn,price));
        }
    }
}
