package br.ce.waquino.servicos;

import br.ce.waquino.entidades.Filme;
import br.ce.waquino.entidades.NotaAluguel;
import br.ce.waquino.utils.DateUtils;

public class AluguelService {

	public NotaAluguel alugar(Filme filme, String tipo) {
		if (filme.getEstoque() == 0) {
			throw new RuntimeException("Filme sem estoque");
		}
		NotaAluguel nota = new NotaAluguel();

		filme.setEstoque(filme.getEstoque() - 1);			
		if (tipo != null && tipo.equals("extendido")) {
			nota.setPreco(filme.getAluguel() * 2);
			nota.setDataEntrega(DateUtils.obterDataDiferencaDias(3));	
			nota.setPontuacao(2);
		} else if (tipo != null && tipo.equals("semanal")) {
			nota.setPreco(filme.getAluguel() * 3);
			nota.setDataEntrega(DateUtils.obterDataDiferencaDias(7));	
			nota.setPontuacao(3);
		} else if (tipo != null && tipo.equals("comum")) {
			nota.setPreco(filme.getAluguel());
			nota.setDataEntrega(DateUtils.obterDataDiferencaDias(1));
			nota.setPontuacao(1);			
		}

		return nota;
	}
}
