/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.br.lp3.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
@Entity
@Table(name = "CATEGORY_BOOK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoryBook.findAll", query = "SELECT c FROM CategoryBook c"),
    @NamedQuery(name = "CategoryBook.findByIdCategoryBook", query = "SELECT c FROM CategoryBook c WHERE c.idCategoryBook = :idCategoryBook"),
    @NamedQuery(name = "CategoryBook.findByCategoryBook", query = "SELECT c FROM CategoryBook c WHERE c.categoryBook = :categoryBook")})
public class CategoryBook implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATEGORY_BOOK")
    private Integer idCategoryBook;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CATEGORY_BOOK")
    private String categoryBook;
    @JoinTable(name = "CATEGORY_MOVIE", joinColumns = {
        @JoinColumn(name = "ID_CATEGORY_BOOK", referencedColumnName = "ID_CATEGORY_BOOK")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_CATEGORY_BOOK", referencedColumnName = "ID_CATEGORY_BOOK")})
    @ManyToMany
    private List<CategoryBook> categoryBookList;
    @ManyToMany(mappedBy = "categoryBookList")
    private List<CategoryBook> categoryBookList1;

    public CategoryBook() {
    }

    public CategoryBook(Integer idCategoryBook) {
        this.idCategoryBook = idCategoryBook;
    }

    public CategoryBook(Integer idCategoryBook, String categoryBook) {
        this.idCategoryBook = idCategoryBook;
        this.categoryBook = categoryBook;
    }

    public Integer getIdCategoryBook() {
        return idCategoryBook;
    }

    public void setIdCategoryBook(Integer idCategoryBook) {
        this.idCategoryBook = idCategoryBook;
    }

    public String getCategoryBook() {
        return categoryBook;
    }

    public void setCategoryBook(String categoryBook) {
        this.categoryBook = categoryBook;
    }

    @XmlTransient
    public List<CategoryBook> getCategoryBookList() {
        return categoryBookList;
    }

    public void setCategoryBookList(List<CategoryBook> categoryBookList) {
        this.categoryBookList = categoryBookList;
    }

    @XmlTransient
    public List<CategoryBook> getCategoryBookList1() {
        return categoryBookList1;
    }

    public void setCategoryBookList1(List<CategoryBook> categoryBookList1) {
        this.categoryBookList1 = categoryBookList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoryBook != null ? idCategoryBook.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoryBook)) {
            return false;
        }
        CategoryBook other = (CategoryBook) object;
        if ((this.idCategoryBook == null && other.idCategoryBook != null) || (this.idCategoryBook != null && !this.idCategoryBook.equals(other.idCategoryBook))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.model.entities.CategoryBook[ idCategoryBook=" + idCategoryBook + " ]";
    }
    
}
