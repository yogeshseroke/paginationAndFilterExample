package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PaginationAndFilterationEntity;
import com.example.demo.repository.PaginationAndFilterationRepository;

@RestController
public class PaginationAndFilterationController {

	@Autowired
	PaginationAndFilterationRepository paginationAndFilterationRepository;
	
	@GetMapping("/paginationAndFilteration")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
		try {
			List<PaginationAndFilterationEntity> paginationAndFilterationEntities = new ArrayList<PaginationAndFilterationEntity>();
			Pageable paging = PageRequest.of(page, size);
			Page<PaginationAndFilterationEntity> pageFilter;
			if(name == null) {
				pageFilter = paginationAndFilterationRepository.findAll(paging);
			}else {
				pageFilter = paginationAndFilterationRepository.findByNameContaining(name, paging);
			}
			
			paginationAndFilterationEntities = pageFilter.getContent();
			
			Map<String, Object> response = new HashMap<>();
			response.put("name", paginationAndFilterationEntities);
			response.put("currentPage", pageFilter.getNumber());
			response.put("totalItem", pageFilter.getTotalElements());
			response.put("totalPages", pageFilter.getTotalPages());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
