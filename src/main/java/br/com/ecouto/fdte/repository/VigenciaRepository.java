  package br.com.ecouto.fdte.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.ecouto.fdte.model.Vigencia;

public interface VigenciaRepository extends MongoRepository<Vigencia, String> {

	Vigencia findById(Long Id);
	
}
