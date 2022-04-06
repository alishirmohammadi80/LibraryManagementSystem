package com.alishirmohammadi.librarymanagementsystem.service.impl;

import java.util.List;

import com.alishirmohammadi.librarymanagementsystem.entity.Publisher;
import com.alishirmohammadi.librarymanagementsystem.exception.NotFoundException;
import com.alishirmohammadi.librarymanagementsystem.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alishirmohammadi.librarymanagementsystem.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {
@Autowired
PublisherRepository publisherRepository;

//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Publisher> findAllPublishers() {
		return publisherRepository.findAll();
	}

	@Override
	public Publisher findPublisherById(Long id) {
		return publisherRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));
	}

	@Override
	public void createPublisher(Publisher publisher) {
		publisherRepository.save(publisher);
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		publisherRepository.save(publisher);
	}

	@Override
	public void deletePublisher(Long id) {
		final Publisher publisher = publisherRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));

		publisherRepository.deleteById(publisher.getId());
	}

}
