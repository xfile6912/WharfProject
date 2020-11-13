package com.example.ulsan.Model;

import com.example.ulsan.Model.Network.Header;

import java.util.List;

public interface CrudInterface<T> {
    Header<T> create(T request);
    Header<T> read(Long id);
    Header<T> update(T request);
    Header delete(Long id);
}
