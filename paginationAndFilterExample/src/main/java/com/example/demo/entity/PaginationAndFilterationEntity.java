package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PaginationAndFilterationEntity {

	@Id
	private Long id;
	
	private String name;
}
