package br.ce.waquino.steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;

import br.ce.waquino.entidades.Filme;
import br.ce.waquino.entidades.NotaAluguel;
import br.ce.waquino.servicos.AluguelService;
import br.ce.waquino.utils.DateUtils;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class AlugarFilmeSteps {
	
	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel nota;
	private String erro;
	private String tipoAluguel;
	
	@Dado("^um filme com estoque de (\\d+) unidades?$")
	public void umFilmeComEstoqueDeUnidades(int arg1) throws Throwable {
		filme = new Filme();
		filme.setEstoque(arg1);		
	}

	@Dado("^que o preço do aluguel seja (\\d+) reais?$")
	public void queOPreçoDoAluguelSejaReais(int arg1) throws Throwable {
		filme.setAluguel(arg1);
	}

	@Quando("^alugar$")
	public void alugar() throws Throwable {
		try {
			nota = aluguel.alugar(filme, tipoAluguel);
		} catch(RuntimeException e) {
			erro = e.getMessage();
		}
	}

	@SuppressWarnings("deprecation")
	@Então("^o preço do aluguel será R\\$ (\\d+)$")
	public void oPreçoDoAluguelSeráR$(int arg1) throws Throwable {
		Assert.assertEquals(arg1, nota.getPreco());
	}

	@Então("^a data de entrege será no dia seguinte$")
	public void aDataDeEntregeSeráNoDiaSeguinte() throws Throwable {
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 1);
	    
	    Date dataRetorno = nota.getDataEntrega();
	    Calendar calRetorno = Calendar.getInstance();
	    calRetorno.setTime(dataRetorno);
	    
	    Assert.assertEquals(cal.get(Calendar.DAY_OF_MONTH), calRetorno.get(Calendar.DAY_OF_MONTH));
	    Assert.assertEquals(cal.get(Calendar.MONTH), calRetorno.get(Calendar.MONTH));
	    Assert.assertEquals(cal.get(Calendar.YEAR), calRetorno.get(Calendar.YEAR));	    
	}
	
	@Então("^o estoque do filme será (\\d+) unidades?$")
	public void oEstoqueDoFilmeSeráUnidade(int arg1) throws Throwable {
	    Assert.assertEquals(arg1, filme.getEstoque());
	}
		
	
	@Então("^não será possível por falta de estoque$")
	public void nãoSeráPossívelPorFaltaDeEstoque() throws Throwable {
	    Assert.assertEquals("Filme sem estoque", erro);
	}
	
	@Dado("^que o preço de aluguel seja R\\$ (\\d+)$")
	public void queOPreçoDeAluguelSejaR$(int arg1) throws Throwable {
	    filme.setAluguel(arg1);
	}
		
	@Então("^a data de entrega será (\\d+) dias?$")
	public void aDataDeEntregaSeráDias(int arg1) throws Throwable {
		Date data = DateUtils.obterDataDiferencaDias(arg1);
		Date dataReal = nota.getDataEntrega();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Assert.assertEquals(format.format(data), format.format(dataReal));
	}
	
	@Então("^a pontuação recebida será de (\\d+) pontos?$")
	public void aOperaçãoRecebidaSeráDePontos(int arg1) throws Throwable {
	    Assert.assertEquals(arg1, nota.getPontuacao());
	}		
	
	@Dado("^que o tipo do aluguel seja (.+)$")
	public void queOTipoDoAluguelSejaComum(String tipo) throws Throwable {
	    tipoAluguel = tipo;
	}	

	@Dado("^um filme$")
	public void umFilme(DataTable table) throws Throwable {
		Map<String, String> map = table.asMap(String.class, String.class);
		filme = new Filme();
		filme.setAluguel(Integer.valueOf(map.get("preco")));
		filme.setEstoque(Integer.valueOf(map.get("estoque")));
		tipoAluguel = map.get("tipo");
	}

}
