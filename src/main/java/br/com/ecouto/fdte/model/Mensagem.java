package br.com.ecouto.fdte.model;

import br.com.ecouto.fdte.utils.TipoMensagem;

public class Mensagem {

	private TipoMensagem tipo;
	private String mensagem;
	
	public TipoMensagem getTipo() {
		return tipo;
	}
	public void setTipo(TipoMensagem tipo) {
		this.tipo = tipo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
