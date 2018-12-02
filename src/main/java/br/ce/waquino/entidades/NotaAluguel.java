package br.ce.waquino.entidades;

import java.util.Calendar;
import java.util.Date;

public class NotaAluguel {

	private int preco;
	private int pontuacao;
	private Date dataEntrega;

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}
	
	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;		
	}

	public int getPontuacao() {
		return this.pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
}
