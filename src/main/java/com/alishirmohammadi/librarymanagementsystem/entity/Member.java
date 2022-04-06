package com.alishirmohammadi.librarymanagementsystem.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fullname", length = 120, nullable = false)
	private String fullname;

	@Column(name = "nationalCode", length = 250, nullable = false,unique = true)
	private String nationalCode;

	@Column(name = "dateOfBirth")
	private Date dateOfBirth;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "members")
	private Set<Book> books = new HashSet<Book>();

	public Member(String fullname, String nationalCode,Date dateOfBirth ) {
		this.fullname = fullname;
		this.nationalCode = nationalCode;
		this.dateOfBirth = dateOfBirth;
	}
}
