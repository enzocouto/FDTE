package br.com.ecouto.fdte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecouto.fdte.model.CompromissoRecorrente;
import br.com.ecouto.fdte.model.Mensagem;
import br.com.ecouto.fdte.service.CompromissoRecorrenteService;


@RestController
public class CompromissoRecorrenteController {

	
	@Autowired
	CompromissoRecorrenteService service;
	
	@RequestMapping(value = "/compromisso/recorrente", method = RequestMethod.GET)
	public List<CompromissoRecorrente> listarCompromissosDiario() {
		return service.listarCompromissosDiario(null);
	}
	
	
	@RequestMapping(value = "/compromisso/recorrente", method = RequestMethod.POST)
	public Mensagem gerarCompromissoRecorrente() {
		return service.gerarCompromissoRecorrente(null);
	}
	
	@RequestMapping(value = "/compromisso/recorrente", method = RequestMethod.PUT)
	public Mensagem gerarCompromissosFantantes() {
		return service.gerarCompromissosFantantes();
	}
	
}
