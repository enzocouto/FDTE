package br.com.ecouto.fdte.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecouto.fdte.model.Vigencia;
import br.com.ecouto.fdte.repository.VigenciaRepository;

@Service
public class VigenciaService {


	@Autowired
	VigenciaRepository repository;
	
	public String inserirVigencia(Vigencia vigencia){
		
		String msg = "";
		Date dtInicioVigencia = vigencia.getDtInicioVigencia();
		Date dtFinalVigencia = vigencia.getDtFinalVigencia();
		if(dtInicioVigencia.after(dtFinalVigencia)) {
			msg = "data de inicio de vigencia não pode ser maior que fim de vigencia";
		}else {
			Long count = repository.count();
			count++;
			vigencia.setId(count);	
			repository.save(vigencia);
			msg = "Vigencia inserida com sucesso";
		}
		return msg;
	}
	
	
	public Vigencia alterarVigencia(Vigencia vigencia){
		
		return repository.save(vigencia);	
	}
	
    public String excluirVigenciaByID(Long id){
		
    	Vigencia consultaByID = consultaVigenciaByID(id);
		repository.delete(consultaByID);	
		return "Registro de vigencia excluido com sucesso!";
	}
    
    public Vigencia consultaVigenciaByID(Long id){
	
		Vigencia findOne = repository.findById(id);
		return findOne;
	}   
	
    
	public List<Vigencia> listarTodasVigencias(){
		
		return repository.findAll();
		
	}
	
	
}
