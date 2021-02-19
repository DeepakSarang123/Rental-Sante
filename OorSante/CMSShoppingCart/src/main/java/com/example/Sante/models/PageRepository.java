package com.example.Sante.models;

//import java.util.List;

import com.example.Sante.models.data.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PageRepository extends JpaRepository<Page,Integer>{

   Page findBySlug(String slug);

   @Query("SELECT p from Page p where p.id!=:id  and p.slug=:slug")
   Page findBySlug(int id, String slug);
}