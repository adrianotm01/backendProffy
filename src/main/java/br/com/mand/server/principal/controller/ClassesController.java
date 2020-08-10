package br.com.mand.server.principal.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mand.server.principal.dto.ClassesDto;
import br.com.mand.server.principal.model.ClassSchedule;
import br.com.mand.server.principal.model.Classes;
import br.com.mand.server.principal.model.User;
import br.com.mand.server.principal.repository.ClassesRepository;
import br.com.mand.server.principal.repository.ClassesScheduleRepository;
import br.com.mand.server.principal.repository.UserRepository;
import br.com.mand.server.principal.util.ConversorUtil;

@RestController
@RequestMapping("/classes")
public class ClassesController {

	@Autowired
	private ClassesRepository classeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ClassesScheduleRepository scheduleRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> createClass(@RequestBody ClassesDto classToCadastr) {
		try {
			Classes cl = mapper.convertValue(classToCadastr, Classes.class);
			User users = mapper.convertValue(classToCadastr, User.class);
			Iterable<ClassSchedule> clas = classToCadastr.getSchedulesList().stream().map(t -> {
				ClassSchedule clases = new ClassSchedule();
				clases.setFroms(ConversorUtil.converterMinutes(t.getFroms()));
				clases.setTos(ConversorUtil.converterMinutes(t.getTos()));
				clases.setClasses(cl);
				clases.setWeekDay(t.getWeekDay());
				return clases;
			}).collect(Collectors.toList());
			userRepository.save(users);
			cl.setUser(users);
			classeRepository.save(cl);
			scheduleRepository.saveAll(clas);
			ServletUriComponentsBuilder.fromCurrentRequest();
			URI bu = UriComponentsBuilder.fromUriString("/class/{id}").build(cl.getId());
			return ResponseEntity.created(bu).build();
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> index(@RequestParam(name = "week_day") String weekDay,
			@RequestParam(name="subject") String subject, 
			@RequestParam(name="time") String time){
		
		if (weekDay.isEmpty() || subject.isEmpty() || time.isEmpty()) {
			Map<String,String> mapWithError = new HashMap<String, String>();
			mapWithError.put("error", "Missing filters to search classes");
			return new ResponseEntity<Map<String,String>>(mapWithError,HttpStatus.NOT_FOUND);
		}
		
		int timeInMinutes = ConversorUtil.converterMinutes(time);
		
		List<Classes> clas = classeRepository.findClas(subject, Integer.valueOf(weekDay), timeInMinutes);
		
		return ResponseEntity.ok(clas);
	}

}
