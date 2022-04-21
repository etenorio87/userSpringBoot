package com.example.demo.services.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Instantiates a new abstract simple dao impl.
 *
 * @param <ENTITY> the generic type
 * @param <PK> the generic type
 * @param <REPOSITORY> the generic type
 */
public abstract class BaseService<ENTITY, PK extends Serializable, REPOSITORY extends JpaRepository<ENTITY, PK>> implements IBaseService<ENTITY, PK> {

    @Autowired
    protected REPOSITORY repository;


    @Override
    @Transactional(readOnly = true)
    public List<ENTITY> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ENTITY> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ENTITY findById(PK id) {
        Optional<ENTITY> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    @Transactional
    public ENTITY save(ENTITY data) {
        return repository.save( data );
    }

    @Override
    @Transactional
    public List<ENTITY> saveAll(List<ENTITY> list) {
        return repository.saveAll( list );
    }

    @Override
    @Transactional
    public void deleteById(PK id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteInBatch(List<ENTITY> objects) {
        repository.deleteAllInBatch( objects );
    }
}
