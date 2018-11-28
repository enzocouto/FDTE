package br.com.ecouto.fdte.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.ecouto.fdte.model.CompromissoRecorrente;

public interface CompromissoRecorrenteRepository extends MongoRepository<CompromissoRecorrente, String> {

}
