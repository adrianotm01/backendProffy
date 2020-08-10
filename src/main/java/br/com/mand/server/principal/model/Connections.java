package br.com.mand.server.principal.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table
@Entity
@Data
@AllArgsConstructor
public class Connections implements Serializable{

	private static final long serialVersionUID = 8164187718992536820L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false,columnDefinition = "TIMESTAMP DEFAULT now()")
	private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
	
	@ManyToOne
	private User user;
}
