package br.com.mand.server.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mand.server.principal.model.Classes;

public interface ClassesRepository extends JpaRepository<Classes, Integer>{

	@Query(value = "SELECT c FROM Classes c "
			+ "JOIN FETCH c.user u "
			+ "WHERE c.subject = ?1 "
			+ "AND EXISTS"
				+ "(SELECT cs FROM ClassSchedule cs JOIN  cs.classes cls "
				+ "WHERE cls.id = c.id AND cs.weekDay = ?2  AND cs.froms <= ?3 AND cs.tos > ?3)")
	public List<Classes> findClas(String subject,  Integer weekDay,Integer time);
	
}
