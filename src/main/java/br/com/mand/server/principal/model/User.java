package br.com.mand.server.principal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1454028684572093816L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String avatar;
	
	@Column(nullable = false)
	private String whatsapp;
	
	@Column(nullable = false)
	private String bio;
}
