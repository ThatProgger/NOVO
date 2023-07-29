package com.novo;

import com.novo.model.entry.Entry;
import com.novo.model.entry.dao.EntryPageableDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


@SpringBootTest
public class PageableTest {
    @Autowired
    private EntryPageableDAO entryPageableDAO;

    @Test
    public void test (){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Entry> page = entryPageableDAO.findAll(pageable);
        System.err.println(page.getTotalElements());
        System.err.println(page.getTotalPages());
        List<Entry> list = page.getContent();
        System.err.println(list.size());

    }
}
