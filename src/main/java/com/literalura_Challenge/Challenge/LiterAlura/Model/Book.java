package com.literalura_Challenge.Challenge.LiterAlura.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AuthorData> authors;
    @Enumerated(EnumType.STRING)
    private Language language;
    private Double downloads;

    public Book () {
    }

    public Book ( List<BookData> results ) {
    }

    public Book ( String title, List<String> languages, Double downloads, List<Authors> authors ) {
        this.title = title;
        this.language = Language.fromString(languages.get(0));
        this.downloads = downloads;
        this.authors = new ArrayList<>();
        for ( Authors authorInfo : authors ) {
            AuthorData author = new AuthorData(authorInfo.name(), authorInfo.birthYear(), authorInfo.deathYear(), this);
            this.authors.add(author);
        }
    }


    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Double getDownloads () {
        return downloads;
    }

    public void setDownloads ( Double downloads ) {
        this.downloads = downloads;
    }

    public Language getLanguages () {
        return language;
    }

    public void setLanguages ( Language languages ) {
        this.language = languages;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    public List<AuthorData> getAuthors () {
        return authors;
    }

    public void setAuthors ( List<AuthorData> authors ) {
        authors.forEach(e -> e.setBook(this));
        this.authors = authors;
    }

    @Override
    public String toString () {
        return
                "Title='" + title + '\'' + "\n" +
                        "Authors=" + authors + "\n" +
                        "Languages=" + language + "\n" +
                        "Downloads=" + downloads + "\n";
    }


}
