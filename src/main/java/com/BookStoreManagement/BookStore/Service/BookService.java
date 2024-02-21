package com.BookStoreManagement.BookStore.Service;

import com.BookStoreManagement.BookStore.Dto.*;
import com.BookStoreManagement.BookStore.Entity.Author;
import com.BookStoreManagement.BookStore.Entity.Book;
import com.BookStoreManagement.BookStore.Repository.AuthorRepository;
import com.BookStoreManagement.BookStore.Repository.BookRepository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.BookStoreManagement.BookStore.enums.OrderBy;
import com.BookStoreManagement.BookStore.enums.SortBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final BookRepository _Book;
    private final AuthorRepository _Author;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        _Book = bookRepository;
        _Author = authorRepository;
    }

    @Override
    public ServicesResponse<PagingResponse<List<Book>>> GetAllBook(Integer pageNumber, Integer pageSize, SortBy sortBy, OrderBy orderBy) {
        // last final response
        ServicesResponse<PagingResponse<List<Book>>> response = new ServicesResponse<>();
        // pagingResponse
        PagingResponse<List<Book>> pagingResponse = new PagingResponse<>();

        int totalBooks = _Book.findAll().size();
        int totalPages = totalBooks % pageSize == 0 ? totalBooks / pageSize : totalBooks / pageSize + 1;

        // pagination
        Sort.Direction sortOrder = orderBy.name().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable p = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sortOrder, (sortBy.name())));
        Page<Book> pageBooks = _Book.findAll(p);

        pagingResponse.TotalPage = totalPages;
        pagingResponse.CurrentPage = pageNumber;
        pagingResponse.ElementsInCurrentPage = pageBooks.getContent().size();
        pagingResponse.Data = pageBooks.getContent();
        response.Data = pagingResponse;
        return response;
    }

    @Override
    public ServicesResponse<Book> AddBook(AddBookDto book) {
        ServicesResponse<Book> response = new ServicesResponse<>();
        try {
            Book obj = new Book();
            obj.setAuthorId(book.getAuthorid());
            obj.setPrice(book.getPrice());
            obj.setIsbn(book.getIsbn());
            obj.setTitle(book.getTitle());
            obj.setPublishedYear(book.getPublishedYear());
            Author author = _Author.findById(obj.getAuthorId()).orElseThrow(
                    () -> new EntityNotFoundException("Invalid Author Id: " +
                            String.valueOf(obj.getAuthorId())));
            Optional<Book> IsBnBook = Optional.ofNullable(_Book.findByIsbn(obj.getIsbn()));
            if (!IsBnBook.isPresent()) {
                try {
                    Book newbook = _Book.save(obj);
                } catch (Exception ex) {
                    throw new Exception("We apologize, but we encountered an error while attempting to add the book to our database. This could be due to technical issues or invalid data provided.");
                }
                response.Data = obj;
                response.Message = "We are pleased to inform you that the book data has been successfully added to our database.";
            } else {
                throw new Exception("We regret to inform you that the ISBN you provided already exists in our database. Each ISBN must be unique to ensure accurate cataloging and inventory management.");
            }
        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<Optional<Book>> Delete(Integer id) {
        ServicesResponse<Optional<Book>> response = new ServicesResponse<>();
        try {
            Optional<Book> book = _Book.findById(id);
            if (book.isPresent()) {
                _Book.deleteById(id);
                response.Data = book;

            } else {
                throw new Exception("We're sorry, but the book with the provided ID does not exist in our database or the ID is invalid. As a result, we are unable to process the deletion request.");
            }
        } catch (Exception ex) {

            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<Book> Update(Integer id, AddBookDto book) {
        ServicesResponse<Book> response = new ServicesResponse<>();
        try {
            Book book1 = _Book.findById(id).orElseThrow(
                    () -> new EntityNotFoundException(
                            "We're sorry, but the book with the provided ID does not exist in our database or the ID is invalid."));
            if (book.getAuthorid() != 0) {
                Author author = _Author.findById(book.getAuthorid()).orElseThrow(
                        () -> new EntityNotFoundException("We regret to inform you that the update process for the book could not be completed due to an invalid author ID. The author ID provided does not correspond to a valid author in our database."));
            }

            if (book.getPrice() != 0) book1.setPrice(book.getPrice());
            if (book.getAuthorid() != 0) {
                book1.setAuthorId(book.getAuthorid());
            }
            if (book.getTitle() != null) book1.setTitle(book.getTitle());
            if (book.getIsbn() != null) {
                book1.setIsbn(book.getIsbn());
            }
            if (book.getPublishedYear() != 0) book1.setPublishedYear(book.getPublishedYear());

            response.Data = _Book.save(book1);


        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<BookDto> GetById(Integer id) {
        ServicesResponse<BookDto> response = new ServicesResponse<>();
        try {
            Book book = _Book.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("We're sorry, but the book with the provided ID does not exist in our database or the ID is invalid."));

            Author author = _Author.findById(book.getAuthorId()).orElseThrow(
                    () -> new EntityNotFoundException("Invalid Author Id: " +
                            String.valueOf(id)));

            response.Data = new BookDto();
            response.Data.setAuthor(author);
            response.Data.setPrice(book.getPrice());
            response.Data.setIsbn(book.getIsbn());
            response.Data.setTitle(book.getTitle());
            response.Data.setPublishedYear(book.getPublishedYear());

        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    public ServicesResponse<Book> updateProductByFields(int id, Map<String, Object> fields) {
        ServicesResponse<Book> response = new ServicesResponse<>();
        try {

            Optional<Book> existingProduct = _Book.findById(id);

            if (existingProduct.isPresent()) {
                fields.forEach((key, value) -> {
                    Field field = ReflectionUtils.findField(Book.class, key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingProduct.get(), value);
                });
                response.Data = _Book.save(existingProduct.get());
            }
        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();

        }
        return response;
    }

    public ServicesResponse<PagingResponse<List<Book>>> SearchBook(String searchTerm, Integer pageNumber, Integer pageSize, SortBy sortBy, OrderBy orderBy, Integer minPrice, Integer maxPrice, Integer minYear, Integer maxYear) {
        ServicesResponse<PagingResponse<List<Book>>> response = new ServicesResponse<>();
        PagingResponse<List<Book>> pagingResponse = new PagingResponse<>();
        try {
            // pagination
            Sort.Direction sortOrder = orderBy.name().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortOrder, (sortBy.name())));
            Page<Book> pageBooks = _Book.findAll(new Specification<Book>() {
                @Override
                public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicates = new ArrayList<>();

                    // Search by title or word in title
                    if (searchTerm != null && !searchTerm.isEmpty()) {
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + searchTerm.toLowerCase() + "%"));
                    }

                    // Filtering based on price range
                    if(minPrice!= 0 && maxPrice!=0){
                        predicates.add(criteriaBuilder.between(root.get("price"),minPrice,maxPrice));
                    }

                    // Filtering based on published year range
                    if(minYear!=0 && maxYear!=0){
                        predicates.add(criteriaBuilder.between(root.get("publishedYear"),minYear,maxYear));
                    }

                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }
            }, pageable);

            int totalBooks = _Book.findAll().size();
            int totalPages = totalBooks % pageSize == 0 ? totalBooks / pageSize : totalBooks / pageSize + 1;

            pagingResponse.TotalPage = totalPages;
            pagingResponse.CurrentPage = pageNumber;
            pagingResponse.ElementsInCurrentPage = pageBooks.getContent().size();
            pagingResponse.Data = pageBooks.getContent();

            response.Data = pagingResponse;
        } catch (QueryCreationException e) {
            // Handle query creation exceptions
            response.Message = "Error occurred while creating the query.";
            response.Success = false;
        } catch (Exception e) {
            // Handle other exceptions
            response.Message = "An unexpected error occurred.";
            response.Success = false;
        }
        return response;
    }
}
