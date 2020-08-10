package br.com.mand.server.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mand.server.principal.model.Connections;

public interface ConnectionRepository extends JpaRepository<Connections, Integer>{

}
