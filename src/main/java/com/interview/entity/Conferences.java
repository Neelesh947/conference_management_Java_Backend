package com.interview.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conferences {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long confId;
	private String name;
	private String location;
	private LocalDateTime localDateTime;
}
