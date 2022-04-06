package com.alishirmohammadi.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alishirmohammadi.librarymanagementsystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
