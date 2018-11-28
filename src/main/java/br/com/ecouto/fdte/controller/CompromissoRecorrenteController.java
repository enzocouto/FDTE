package br.com.ecouto.fdte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecouto.fdte.service.CompromissoRecorrenteService;


@RestController
public class CompromissoRecorrenteController {

	
	@Autowired
	CompromissoRecorrenteService service;
	
	
	@RequestMapping(value = "/compromisso/recorrente", method = RequestMethod.POST)
	public String gerarCompromissoRecorrente() {

		return service.gerarCompromissoRecorrente();
	}
}
