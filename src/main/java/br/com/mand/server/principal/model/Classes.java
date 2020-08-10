package br.com.mand.server.principal.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class Classes implements Serializable{

	private static final long serialVersionUID = 55615905736990805L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String subject;
	
	@Column(nullable = false)
	private Double cost;
	
	@ManyToOne(cascade = {CascadeType.REMOVE})
	@JoinColumn(name = "user_id",nullable = false, foreignKey = @ForeignKey(name="user_fk"))
	private User user;

}
