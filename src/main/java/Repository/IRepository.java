package Repository;

import Domain.Entity;

import java.util.List;

public interface IRepository<T extends Entity> {
    T findById(int id);

    void insertOrUpdate(T entity);

    void remove(int id);

    List<T> getAll();
}
