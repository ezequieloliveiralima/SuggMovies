/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlebooks.ws.entities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ezequieloliveira
 */
public class Book implements Serializable {
    private String id;
    private String title, publisher, description, thumbnail, buyLink, language, isbn;
    private List<String> authors, categories;
    private Integer pageCount;

    public Book() {
    }

    public Book(String id, String title, String publisher, String description, String thumbnail, String buyLink, String language, List<String> authors, List<String> categories, Integer pageCount) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.thumbnail = thumbnail;
        this.buyLink = buyLink;
        this.language = language;
        this.authors = authors;
        this.categories = categories;
        this.pageCount = pageCount;
    }
    
    public Book(String id, String title, String publisher, String description, String thumbnail, String buyLink, String language, List<String> authors, List<String> categories, Integer pageCount, String isbn) {
        this(id, title, publisher, description, thumbnail, buyLink, language, authors, categories, pageCount);
        this.isbn = isbn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", publisher=" + publisher + ", description=" + description + ", thumbnail=" + thumbnail + ", buyLink=" + buyLink + ", language=" + language + ", isbn=" + isbn + ", authors=" + authors + ", categories=" + categories + ", pageCount=" + pageCount + '}';
    }
}
