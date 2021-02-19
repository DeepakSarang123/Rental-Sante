package com.example.restapi.models.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PageRepository extends JpaRepository<Page, Integer>{
    
    Page findBySlug(String slug);

    Page findBySlugandIdNot(String slu, int id);

    List<Page> findAllByOrderBySortingAsc();
}