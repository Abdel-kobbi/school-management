package DAO;

import java.util.List;

public interface GenericDAO<T, ID> {

    // pour crée un nouvelle entité
    boolean save(T entity);

    // pour trouver une entité identifier par id
    T findById(ID id);

    // pour obtenier toutes les entités
    List<T> findAll();

    // pour modifier une entité
    boolean update(T entity);

    // pour supprimer une entité pas son id
    boolean delete(ID id);

}
