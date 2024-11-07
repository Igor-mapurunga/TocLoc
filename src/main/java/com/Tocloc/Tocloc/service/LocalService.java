package com.Tocloc.Tocloc.service;

import com.Tocloc.Tocloc.entities.Local;

import java.util.List;

public interface LocalService {
    List<Local> findAll();
    Local findById(Long id);
    Local save(Local local);
    Local update(Long id, Local localDetails);
    void deleteById(Long id);
}
