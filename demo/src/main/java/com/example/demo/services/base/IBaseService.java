package com.example.demo.services.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBaseService<ENTITY, PK> {

    List<ENTITY> findAll();
    Page<ENTITY> findAll(Pageable pageable);
    ENTITY findById(PK id);
    ENTITY save(ENTITY data);
    List<ENTITY> saveAll(List<ENTITY> list);
    void deleteById(PK id);
    void deleteInBatch(List<ENTITY> objects);

}
