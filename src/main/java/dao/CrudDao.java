package dao;

import org.hibernate.Session;

public interface CrudDao <ID, T> {
    void create(T o);
    T read(ID id);
    void update(T o);
    T delete(ID id);
}
