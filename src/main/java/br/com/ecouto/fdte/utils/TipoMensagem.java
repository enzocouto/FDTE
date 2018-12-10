package br.com.ecouto.fdte.utils;

public enum TipoMensagem {    
    SUCESSO("SUCESSO NA EXECUÇÃO DO SERVIÇO"), 
    ERRO("OCORREU ERRO DURANTE EXECUAÇÃO DO SERVIÇO"), 
    DATA_INICIO_MAIOR_FIM("DATA DE INICIO MAIOR QUE O FIM"),
    INTERVALOS_CONFLITANTES("A VIGENCIA TEM INTERNALOS CONFLITANTES COM AS VIGENCIAS JÁ CADASTRADAS")
    ;
     
    private final String valor;
    TipoMensagem(String tipo){
        valor = tipo;
    }
    public String getValor(){
        return valor;
    }
}