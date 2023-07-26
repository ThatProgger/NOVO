package com.novo.model.responsible.service.impl;

import com.novo.model.entry.Entry;
import com.novo.model.entry.service.EntryService;
import com.novo.model.responsible.Responsible;
import com.novo.model.responsible.dao.ResponsibleDao;
import com.novo.model.responsible.service.ResponsibleService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class implements the {@link EntryService} interface and allows you to operate on models in the database.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Slf4j
@Service
public class ResponsibleServiceImpl implements ResponsibleService {
    @Autowired
    private ResponsibleDao responsibleDao;


    @Override
    public Responsible save(Responsible responsible) {
        Responsible savedResponsible = null;
        try {
            savedResponsible = responsibleDao.save(responsible);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The responsible has been saved by the id: {}", savedResponsible.getId());
        return savedResponsible;
    }

    @Override
    public Responsible findById(int id) {
        Optional<Responsible> optionalResponsible = null;
        try {
            optionalResponsible = responsibleDao.findById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        if (optionalResponsible.isPresent()) {
            log.debug("The responsible by id: {} was found", optionalResponsible.get().getId());
            return optionalResponsible.get();
        }

        log.debug("The responsible by id: {} was not found");
        Responsible responsible = Responsible.builder().id(-1).build();
        return responsible;
    }

    @Override
    public void delete(Responsible responsible) {
        try {
            responsibleDao.delete(responsible);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The responsible by the id: {} has been removed", responsible.getId());
    }

    @Override
    public void deleteById(int id) {
        try {
            responsibleDao.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The responsible by the id: {} has been removed", id);
    }

    @Override
    public void deleteAll() {
        try {
            responsibleDao.deleteAll();
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("All responsible have been removed");
    }

    @Override
    public List<Responsible> findAll() {
        Iterable<Responsible> iterable = responsibleDao.findAll();
        List<Responsible> responsibles = new ArrayList<>();
        iterable.forEach(responsibles::add);
        return responsibles;
    }
}
