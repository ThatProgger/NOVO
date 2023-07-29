package com.novo.model.entry.service.impl;

import com.novo.model.employee.Employee;
import com.novo.model.entry.Entry;
import com.novo.model.entry.dao.EntryDao;
import com.novo.model.entry.dao.EntryPageableDAO;
import com.novo.model.entry.service.EntryService;
import com.novo.model.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
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
public class EntryServiceImpl implements EntryService {
    @Autowired
    private EntryDao entryDao;
    @Autowired
    private EntryPageableDAO entryPageableDAO;


    @Override
    public Entry save(Entry entry) {
        Entry savedEntry = null;
        try {
            savedEntry = entryDao.save(entry);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The entry has been saved by the id: {}", savedEntry.getId());
        return savedEntry;
    }

    @Override
    public Entry findById(int id) {
        Optional<Entry> optionalEmployee = null;
        try {
            optionalEmployee = entryDao.findById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        if (optionalEmployee.isPresent()) {
            log.debug("The entry by id: {} was found", optionalEmployee.get().getId());
            return optionalEmployee.get();
        }

        log.debug("The entry by id: {} was not found");
        Entry entry = Entry.builder().id(-1).build();
        return entry;
    }

    @Override
    public void delete(Entry entry) {
        try {
            entryDao.delete(entry);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The entry by the id: {} has been removed", entry.getId());
    }

    @Override
    public void deleteById(int id) {
        try {
            entryDao.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The entry by the id: {} has been removed", id);
    }

    @Override
    public void deleteAll() {
        try {
            entryDao.deleteAll();
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("All entries have been removed");
    }

    @Override
    public List<Entry> findAll() {
        Iterable<Entry> iterable = entryDao.findAll();
        List<Entry> entries = new ArrayList<>();
        iterable.forEach(entries::add);
        return entries;

    }


    @Override
    public List<Entry> findAllinInvertedOrder() {
        List<Entry> entries = entryDao.findAllinInvertedOrder();
        return entries;
    }


    @Override
    public List<Entry> findAllByPageAsList(int pageNumber) {
        int maxSize = 25;
        Pageable pageable = PageRequest.of(pageNumber, maxSize, Sort.Direction.DESC, "id");
        Page<Entry> page = entryPageableDAO.findAll(pageable);
        List<Entry> list = page.getContent();
        log.debug("The content size: {} on the page: {}", list.size(), pageNumber);
        return list;
    }


    @Override
    public Page<Entry> findAllSeparatedByPages(int pageNumber) {
        int maxSize = 25;
        Pageable pageable = PageRequest.of(pageNumber, maxSize, Sort.Direction.DESC, "id");
        Page<Entry> page = entryPageableDAO.findAll(pageable);
        return page;
    }

    @Override
    public Pageable createPageable(int pageNumber) {
        int maxSize = 25;
        Pageable pageable = PageRequest.of(pageNumber, maxSize, Sort.Direction.DESC, "id");
        return pageable;
    }

    @Override
    public Page<Entry> createPages(Pageable pageable) {
        Page<Entry> pages = entryPageableDAO.findAll(pageable);
        return pages;
    }
}
