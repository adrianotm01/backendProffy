package br.com.mand.server.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mand.server.principal.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
