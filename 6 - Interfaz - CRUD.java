package edu.pucp.mechatronics.interfaces;

import java.util.List;

public interface CRUD<E> {

    public List<E> findAll();

    public E find(int id);

    public boolean save(E p);

    public boolean update(E p);

    public boolean delete(int id);
}