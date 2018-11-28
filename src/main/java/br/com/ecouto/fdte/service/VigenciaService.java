package br.com.ecouto.fdte.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecouto.fdte.model.Mensagem;
import br.com.ecouto.fdte.model.Vigencia;
import br.com.ecouto.fdte.repository.VigenciaRepository;
import br.com.ecouto.fdte.utils.TipoMensagem;

@Service
public class VigenciaService {

    
	@Autowired
	VigenciaRepository repository;
	
	public Mensagem inserirVigencia(Vigencia vigencia){
		Mensagem msg = new Mensagem();
		Date dtInicioVigencia = vigencia.getDtInicioVigencia();
		Date dtFinalVigencia = vigencia.getDtFinalVigencia();
		if(dtInicioVigencia.after(dtFinalVigencia)) {
			msg.setTipo(TipoMensagem.DATA_INICIO_MAIOR_FIM);
			msg.setMensagem("data de inicio de vigencia não pode ser maior que fim de vigencia");
		}else {
			Long count = repository.count();
			count++;
			vigencia.setId(count);	
			repository.save(vigencia);
			msg.setTipo(TipoMensagem.SUCESSO);
			msg.setMensagem("Vigencia inserida com sucesso");
		}
		return msg;
	}
	
	
	public Vigencia alterarVigencia(Vigencia vigencia){
		
		return repository.save(vigencia);	
	}
	
    public Mensagem excluirVigenciaByID(Long id){
    	Mensagem msg = new Mensagem();
    	Vigencia consultaByID = consultaVigenciaByID(id);
		repository.delete(consultaByID);	
		msg.setTipo(TipoMensagem.SUCESSO);
		msg.setMensagem("Registro de vigencia excluido com sucesso!");
		return msg;
	}
    
    public Vigencia consultaVigenciaByID(Long id){
	
		Vigencia findOne = repository.findById(id);
		return findOne;
	}   
	
    
	public List<Vigencia> listarTodasVigencias(){
		
		return repository.findAll();
		
	}
	
	
}
