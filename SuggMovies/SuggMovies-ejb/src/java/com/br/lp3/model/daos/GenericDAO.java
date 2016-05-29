
package com.br.lp3.model.daos;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
@Local
public interface GenericDAO<E> {
    
    public void insert(E e);
    public void update(E e);
    public void remove(E e);
    public E readById(long id);
    public List<E> readyAll();
}
