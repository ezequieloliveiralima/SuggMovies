package com.br.lp3.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
@Entity
@Table(name = "INDICATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indication.findAll", query = "SELECT i FROM Indication i"),
    @NamedQuery(name = "Indication.findByIdIndication", query = "SELECT i FROM Indication i WHERE i.idIndication = :idIndication")})
public class Indication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INDICATION")
    private Integer idIndication;
    @JoinColumn(name = "ID_BOOK", referencedColumnName = "ID_BOOK")
    @ManyToOne(optional = false)
    private Book idBook;
    @JoinColumn(name = "ID_MOVIE", referencedColumnName = "ID_MOVIE")
    @ManyToOne(optional = false)
    private Movie idMovie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIndication")
    private List<Comment> commentList;

    public Indication() {
    }

    public Indication(Integer idIndication) {
        this.idIndication = idIndication;
    }

    public Integer getIdIndication() {
        return idIndication;
    }

    public void setIdIndication(Integer idIndication) {
        this.idIndication = idIndication;
    }

    public Book getIdBook() {
        return idBook;
    }

    public void setIdBook(Book idBook) {
        this.idBook = idBook;
    }

    public Movie getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Movie idMovie) {
        this.idMovie = idMovie;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIndication != null ? idIndication.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indication)) {
            return false;
        }
        Indication other = (Indication) object;
        if ((this.idIndication == null && other.idIndication != null) || (this.idIndication != null && !this.idIndication.equals(other.idIndication))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.model.entities.Indication[ idIndication=" + idIndication + " ]";
    }
    
}
