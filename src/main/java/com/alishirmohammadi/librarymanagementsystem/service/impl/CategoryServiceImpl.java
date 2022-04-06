package com.alishirmohammadi.librarymanagementsystem.service.impl;

import java.util.List;

import com.alishirmohammadi.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alishirmohammadi.librarymanagementsystem.entity.Category;
import com.alishirmohammadi.librarymanagementsystem.exception.NotFoundException;
import com.alishirmohammadi.librarymanagementsystem.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
CategoryRepository categoryRepository;

//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));
	}

	@Override
	public void createCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Long id) {
		final Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));

		categoryRepository.deleteById(category.getId());
	}

}
