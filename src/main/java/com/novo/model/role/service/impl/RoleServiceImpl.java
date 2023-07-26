package com.novo.model.role.service.impl;

import com.novo.model.role.Role;
import com.novo.model.role.dao.RoleDao;
import com.novo.model.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class extends the {@link RoleService} interface and allows you to operate on models in the database.
 * @author  Mikhail Dedyukhin
 * @since 1.0
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role save(Role role) {
        Role savedRole = null;
        try {
            savedRole = roleDao.save(role);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e){
            log.error(e.getMessage());
        }

        log.debug("The role has been saved by the id: {}" , savedRole.getId());
        return role;
    }

    @Override
    public Role findById(int id) {
        Optional<Role> optionalRole  = null;
        try {
            optionalRole = roleDao.findById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e){
            log.error(e.getMessage());
        }

        if(optionalRole.isPresent()){
            log.debug("The role by id: {} was found", optionalRole.get().getId());
            return optionalRole.get();
        }

        log.debug("The role by id: {} was not found");
        Role role = Role.builder().id(-1).role("").build();
        return role;
    }

    @Override
    public void delete(Role role) {
        try {
            roleDao.delete(role);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e){
            log.error(e.getMessage());
        }

        log.debug("The role by the id: {} has been removed", role.getId());
    }

    @Override
    public void deleteById(int id) {
        try {
            roleDao.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e){
            log.error(e.getMessage());
        }

        log.debug("The role by the id: {} has been removed", id);
    }

    @Override
    public void deleteAll() {
        try {
            roleDao.deleteAll();
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e){
            log.error(e.getMessage());
        }

        log.debug("All roles have been removed");
    }

    @Override
    public List<Role> findAll() {
        Iterable<Role> iterable = roleDao.findAll();
        List<Role> roles = new ArrayList<>();
        iterable.forEach(roles::add);
        return roles;
    }

    @Override
    public Role findByRole(String rolename) {
        Role role = roleDao.findByRole(rolename);
        log.debug("The role found by the rolename: {}", rolename);
        return role;
    }


    @Override
    public boolean existsByRole(String roleName) {
        boolean exist = roleDao.existsByRole(roleName);
        log.debug("The role by role name exists: {}", exist);
        return exist;
    }
}
