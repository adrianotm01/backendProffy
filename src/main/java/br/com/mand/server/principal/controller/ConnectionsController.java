package br.com.mand.server.principal.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mand.server.principal.model.Connections;
import br.com.mand.server.principal.repository.ConnectionRepository;

@RestController
@RequestMapping("/connections")
public class ConnectionsController {

	@Autowired
	private ConnectionRepository repository;
	
	@PostMapping
	public ResponseEntity<?> created(@RequestBody Connections connection){
		if (connection.getCreatedAt() == null) {
			connection.setCreatedAt( new Timestamp(System.currentTimeMillis()));
		}
		connection = repository.save(connection);
		return ResponseEntity.created(UriComponentsBuilder.fromPath("/connections").build(connection.getId())).build();
	}
	
	@GetMapping
	public ResponseEntity<?> index(){
		long contagem = repository.count();
		Map<String, Long> mapTotalConnections = new HashMap<String, Long>();
		mapTotalConnections.put("total", contagem);
		return ResponseEntity.ok(mapTotalConnections);
	}
	
}
