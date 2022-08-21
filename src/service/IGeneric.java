package service;

import java.util.List;

public interface IGeneric<G> {

    List<G> findAll();

    void save(G g);

    G findById(int id);

    void remove(int id);


}

// CRUD

// Create  Read  Update  Delete
