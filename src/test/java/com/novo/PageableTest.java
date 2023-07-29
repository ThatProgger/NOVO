package com.novo;

import com.novo.model.entry.Entry;
import com.novo.model.entry.dao.EntryPageableDAO;
import com.novo.model.entry.service.EntryService;
import com.novo.model.service.Service;
import org.junit.jupiter.api.Assertions;
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
    private EntryService entryService;


    @Test
    public void test (){
        Pageable pageable = entryService.createPageable(1);
        Page<Entry> pages = entryService.createPages(pageable);

        System.err.println(pageable.getOffset());

        if(pageable.hasPrevious()){
            Pageable _pageable = pageable.previousOrFirst();
            System.err.println(_pageable.getPageNumber());
        }
    }
}
