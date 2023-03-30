package in.sanjeev.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sanjeev.entity.Book;

public interface BookRepository  extends JpaRepository<Book, Serializable> {

}
