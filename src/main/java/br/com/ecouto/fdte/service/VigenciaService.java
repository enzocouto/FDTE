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
	VigenciaRepository vigenciaRepository;
	
	@Autowired
	CompromissoRecorrenteService compromissoRecorrenteService;
	
	
	public Mensagem inserirVigencia(Vigencia vigencia){
		Mensagem msg = new Mensagem();
		Date dtInicioVigencia = vigencia.getDtInicioVigencia();
		Date dtFinalVigencia = vigencia.getDtFinalVigencia();
		if(dtInicioVigencia.after(dtFinalVigencia)) {
			msg.setTipo(TipoMensagem.DATA_INICIO_MAIOR_FIM);
			msg.setMensagem("Data de inicio de vigência não pode ser maior que fim de vigência");
		}else {
			Long count = vigenciaRepository.count();
			count++;
			vigencia.setId(count);	
			vigenciaRepository.insert(vigencia);
			msg.setTipo(TipoMensagem.SUCESSO);
			msg.setMensagem("Vigência inserida com sucesso");
			compromissoRecorrenteService.gerarCompromissoRecorrente(vigencia);
		}
		return msg;
	}
	
	
	public Mensagem alterarVigencia(Vigencia vigencia){
		Mensagem msg = new Mensagem();
		Vigencia vigenciaCadastrada =  vigenciaRepository.findById(vigencia.getId());
		if(vigenciaCadastrada == null) {
			msg.setTipo(TipoMensagem.ERRO);
			msg.setMensagem("Não existe a Vigencia que deseja alterar");
		}else {
			vigenciaRepository.save(vigencia);	
			//Excluir os compromissos diarios para consisti-los de acordo a nova vigencia 
			compromissoRecorrenteService.excluirTodosCompromissosDiarios();
			compromissoRecorrenteService.gerarCompromissoRecorrente(vigencia);
		}
		return msg;
		
	}
	
    public Mensagem excluirVigenciaByID(Long id){
    	Mensagem msg = new Mensagem();
    	Vigencia consultaByID = consultaVigenciaByID(id);
    	if(consultaByID == null) {
    		msg.setTipo(TipoMensagem.ERRO);
    		msg.setMensagem("Vigencia não existe!");
    	}else {
    		vigenciaRepository.delete(consultaByID);	
    		compromissoRecorrenteService.excluirTodosCompromissosDiarios();
			compromissoRecorrenteService.gerarCompromissoRecorrente(null);
    		msg.setTipo(TipoMensagem.SUCESSO);
    		msg.setMensagem("Registro de vigencia excluido com sucesso!");
    	}
		
	
		return msg;
	}
    
    public Vigencia consultaVigenciaByID(Long id){
	
		Vigencia findOne = vigenciaRepository.findById(id);
		return findOne;
	}   
	
    
	public List<Vigencia> listarTodasVigencias(){
		
		return vigenciaRepository.findAll();
		
	}
	
	
}
