package com.saucelabs.kv008.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T, Pk extends Serializable> {

    Pk create(T t);

    T readByKey(Pk pk);

    void update(T t);

    void delete(T t);

    List<T> getAll();

}
