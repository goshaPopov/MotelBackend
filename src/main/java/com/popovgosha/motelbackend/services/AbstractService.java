package com.popovgosha.motelbackend.services;

import java.util.List;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
public interface AbstractService<TYPE, ID extends Number> {

    List<TYPE> findAll();

    TYPE findOne(ID id);

    TYPE save(TYPE type);

    void delete(ID id);

}
