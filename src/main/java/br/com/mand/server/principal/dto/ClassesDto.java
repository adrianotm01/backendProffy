package br.com.mand.server.principal.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClassesDto {
	
	
	private String subject;
	
	private Double cost;
	
	private String name;
	
	private String avatar;
	
	private String whatsapp;
	
	private String bio;
	
	private List<ClassScheduleDto> schedulesList;
		
	
}
