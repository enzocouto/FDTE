package br.com.ecouto.fdte.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecouto.fdte.model.Mensagem;
import br.com.ecouto.fdte.model.Vigencia;
import br.com.ecouto.fdte.service.VigenciaService;
import br.com.ecouto.fdte.utils.TipoMensagem;

@RestController
public class VigenciaController {

	@Autowired
	VigenciaService service;

	@RequestMapping(value = "/vigencia", method = RequestMethod.POST)
	public Mensagem inserirVigencia(@Valid @RequestBody Vigencia vigencia,BindingResult bindingResults) {
        if(bindingResults.hasErrors()) {
        	Mensagem msg = new Mensagem();
        	msg.setTipo(TipoMensagem.ERRO);
        	msg.setMensagem(bindingResults.toString());
        	return msg;
        }
		return service.inserirVigencia(vigencia);
	}
	
	@RequestMapping(value = "/vigencia", method = RequestMethod.PUT)
	public Mensagem alterarVigencia(@Valid @RequestBody Vigencia vigencia) { 

		return service.alterarVigencia(vigencia);
	}	
	
	@RequestMapping(value = "/vigencia/{id}", method = RequestMethod.DELETE)
	public Mensagem excluirVigenciaByID(@PathVariable Long id) { 

	     return service.excluirVigenciaByID(id);
	}	
	
	@RequestMapping(value = "/vigencia/{id}", method = RequestMethod.GET)
	public Vigencia consultaVigenciaByID(@PathVariable Long id) { 

	     return service.consultaVigenciaByID(id);
	}	
	

	@RequestMapping(value = "/vigencia", method = RequestMethod.GET)
	public List<Vigencia> listarTodasVigencias() { 

		return service.listarTodasVigencias();
	}	
}
