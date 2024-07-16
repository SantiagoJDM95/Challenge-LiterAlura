package com.literalura_Challenge.Challenge.LiterAlura.Repository;

import com.literalura_Challenge.Challenge.LiterAlura.Model.AuthorData;
import com.literalura_Challenge.Challenge.LiterAlura.Model.Book;
import com.literalura_Challenge.Challenge.LiterAlura.Model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<AuthorData> getAuthorsInfo ();

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE birthYear > :date")
    List<AuthorData> getAuthorLiveAfter (Integer date );

    List<Book> findByLanguages (Language language );
}
