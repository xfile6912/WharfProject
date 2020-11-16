package com.example.ulsan.model;

import com.example.ulsan.model.network.Header;

public interface CrudInterface<T> {
    Header<T> create(T request);
    Header<T> read(Long id);
    Header<T> update(T request);
    Header delete(Long id);
}
