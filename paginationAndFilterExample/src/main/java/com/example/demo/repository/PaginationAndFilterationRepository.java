package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PaginationAndFilterationEntity;

@Repository
public interface PaginationAndFilterationRepository extends JpaRepository<PaginationAndFilterationEntity, Long>{

	Page<PaginationAndFilterationEntity> findByNameContaining(String name, Pageable paging);

}
