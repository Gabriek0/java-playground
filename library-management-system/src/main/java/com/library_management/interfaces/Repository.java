package main.java.com.library_management.interfaces;

import java.util.ArrayList;
import java.util.Optional;

public interface Repository<T, ID> {
    void create(T item);
    void update(ID id, T item);
    void delete(ID id);
    Optional<T> findById(ID id);
    ArrayList<T> list ();
}
