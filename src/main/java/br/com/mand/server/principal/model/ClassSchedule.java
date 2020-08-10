package br.com.mand.server.principal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class ClassSchedule implements Serializable{

	private static final long serialVersionUID = 8193623031551960294L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false,name = "week_day")
	private Integer weekDay;
	
	@Column(nullable = false)
	private Integer froms;
	
	@Column(nullable = false)
	private Integer tos;
	
	@ManyToOne
	@JoinColumn(name = "class_id",nullable = false)
	private Classes classes;


}
