package com.example.restapi.Controllers;

import java.util.List;

import com.example.restapi.models.data.Page;
import com.example.restapi.models.data.PageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pages", produces = "application/json")
@CrossOrigin(origins = "*")
public class PageController {

    @Autowired
    private PageRepository pageRepo;

    @GetMapping("/all")
    public Iterable<Page> pages() {

        List<Page> pages = pageRepo.findAllByOrderBySortingAsc();
        return pages;
    }
}