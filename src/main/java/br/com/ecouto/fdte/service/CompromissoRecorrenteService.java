package br.com.ecouto.fdte.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecouto.fdte.model.CompromissoRecorrente;
import br.com.ecouto.fdte.model.Mensagem;
import br.com.ecouto.fdte.model.Vigencia;
import br.com.ecouto.fdte.repository.CompromissoRecorrenteRepository;
import br.com.ecouto.fdte.repository.VigenciaRepository;
import br.com.ecouto.fdte.utils.TipoMensagem;

@Service
public class CompromissoRecorrenteService {

	@Autowired
	VigenciaRepository vigenciaRepository;
	
	@Autowired
	CompromissoRecorrenteRepository compromissoRecorrenteRepository;
	
	public Mensagem gerarCompromissoRecorrente(Vigencia vigencia){
		Mensagem msg = new Mensagem();
		
		Calendar calendar = Calendar.getInstance();
		//Deve ter ao menos uma vigencia cadastrada
		Long qtdVigencia = vigenciaRepository.count();
		if(qtdVigencia == 0) {
			msg.setTipo(TipoMensagem.ERRO);
			msg.setMensagem("Não existe vigencia cadastrada. É necessário pelo menos um período de vigência.");
		}else {	
			//Se o processo de gerar compromisso diario foi executado isoladadamente
			//Retorna a ultima vigencia cadastrada
			if(vigencia == null) {
				vigencia = vigenciaRepository.findById(qtdVigencia);
			}
			//data de inicio d+1
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date dtInicio = calendar.getTime();
			
			//Gerar compromisso recorrente
			gerarCompromissosDiarios(dtInicio,vigencia);
		    msg.setTipo(TipoMensagem.SUCESSO);
			msg.setMensagem("Compromissos recorrentes gerados com sucesso");
		}
		
		return msg;
	}

    /*
     * Gerar Compromissos Diarios
     */
	private void gerarCompromissosDiarios(Date dtInicio, Vigencia vigencia) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dtInicioVigencia = vigencia.getDtInicioVigencia();
		Date dtFinalVigencia = vigencia.getDtFinalVigencia();
		
		Long count = compromissoRecorrenteRepository.count();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtInicio);
		//Gerar compromissos para os proximos 30 dias
		for(int i = 0; i < 30; i++){
			if(cal.getTime().after(dtInicioVigencia) && cal.getTime().before(dtFinalVigencia)) {
				count++;
				System.out.println("criando compromisso para:"+sdf.format(cal.getTime()));	
				CompromissoRecorrente compromissoRecorrente = new CompromissoRecorrente();
				compromissoRecorrente.setId(count);
				compromissoRecorrente.setDataCompromisso(cal.getTime());
				compromissoRecorrente.setHorarioInicio(vigencia.getHorarioInicio());
				compromissoRecorrente.setHorarioFinal(vigencia.getHorarioFinal());
				compromissoRecorrenteRepository.save(compromissoRecorrente);
				
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		} 
		
	}
	
	/*
     * Gerar Compromissos faltantes
     */
	public Mensagem gerarCompromissosFantantes() {
		
		Mensagem msg = new Mensagem();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(vigenciaRepository.count() == 0) {
			msg.setTipo(TipoMensagem.ERRO);
		    msg.setMensagem("Não existe vigencia cadastrada");
		    return msg;
		}
		Vigencia vigencia = vigenciaRepository.findById(vigenciaRepository.count());
		
		
		//calcular a data do ultimo compromisso gerado
		long ultimoGerado = compromissoRecorrenteRepository.count();
		CompromissoRecorrente ultimoCompromisso = compromissoRecorrenteRepository.findById(ultimoGerado);
		cal.setTime(ultimoCompromisso.getDataCompromisso());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		Date dtInicio = cal.getTime();
		
		//Calcular data para os dias faltantes
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, 30);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date dtFinal = cal.getTime();
		
		//retorna para o ultimo dia gerado, sera acrescido 1 dia até chegar na ultima data dos dias faltantes
		cal.setTime(dtInicio);
		while(cal.getTime().before(dtFinal)) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			System.out.println("criando compromisso para:"+sdf.format(cal.getTime()));	
			CompromissoRecorrente compromissoRecorrente = new CompromissoRecorrente();
			compromissoRecorrente.setId(++ultimoGerado);
			compromissoRecorrente.setDataCompromisso(cal.getTime());
			compromissoRecorrente.setHorarioInicio(vigencia.getHorarioInicio());
			compromissoRecorrente.setHorarioFinal(vigencia.getHorarioFinal());
			compromissoRecorrenteRepository.save(compromissoRecorrente);
		}
		msg.setTipo(TipoMensagem.SUCESSO);
		msg.setMensagem("Registros faltantes gerados com sucesso");
		return msg;
	}


	public void excluirTodosCompromissosDiarios() {
		
		compromissoRecorrenteRepository.deleteAll();
	}


	public List<CompromissoRecorrente> listarCompromissosDiario(Object object) {
		
		return compromissoRecorrenteRepository.findAll();
	}
}
