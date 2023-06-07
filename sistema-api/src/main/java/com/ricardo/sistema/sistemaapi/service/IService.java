package com.ricardo.sistema.sistemaapi.service;

import java.util.List;

public interface IService<T> {

    public List<T> get();
    public T get(Long id);
    public T save(T objeto);
    public void delete(Long id);

}
