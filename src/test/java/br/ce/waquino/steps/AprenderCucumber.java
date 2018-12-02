package br.ce.waquino.steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import br.ce.waquino.converters.DateConverter;
import cucumber.api.Transform;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class AprenderCucumber {
	
	private int contador = 0;
	
	@Dado("^que criei o arquivo corretamente$")
	public void queCrieiOArquivoCorretamente() throws Throwable {
	}
	
	@Quando("^executa-lo$")
	public void executaLo() throws Throwable {
	}
	
	@Então("^a especificação deve finalizar com tudo certo$")
	public void aEspecificaçãoDeveFinalizarComTudoCerto() throws Throwable {
	}
	
	@Dado("^que o valor do contador é (\\d+)$")
	public void queOValorDoContadorÉ(int arg1) throws Throwable {
	    contador = arg1;
	}

	@Quando("^eu incrementar em (\\d+)$")
	public void euIncrementarEm(int arg1) throws Throwable {
	    contador = contador + arg1;
	}

	@Então("^o valor do contador será (\\d+)$")
	public void oValorDoContadorSerá(int arg1) throws Throwable {
	    Assert.assertEquals(contador, arg1);
	}
	
	Date entrega = new Date();
	
	@Dado("^que prazo é dia (.*)$")
	public void quePrazoÉDia(@Transform(DateConverter.class) Date data) throws Throwable {
		entrega = data;		
	}

	@Quando("^a entrega atrasar em (\\d+) (dias|dia|meses|meses)$")
	public void aEntregaAtrasarEmDias(int qtd, String tipoAtraso) throws Throwable {
		Calendar cal = Calendar.getInstance();
		cal.setTime(entrega);
		if (tipoAtraso.equals("dias") || tipoAtraso.equals("dia")) {
			cal.add(Calendar.DAY_OF_MONTH, qtd);
		} else if (tipoAtraso.equals("meses") || tipoAtraso.equals("mes")) {
			cal.add(Calendar.MONTH, qtd);			
		}
		entrega =cal.getTime();		
	}
	
	@Então("^a entrega será efetuada em (\\d{2}\\/\\d{2}\\/\\d{4})$")
	public void aEntregaSeráEfetuadaEm(String data) throws Throwable {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = format.format(entrega);
		Assert.assertEquals(data, dataFormatada);
	}
	
	private String codigo = null;
	private Float valorPassagem = null;
	private String nomePassageiro = null;
	private String telefonePassageiro = null;
	private String codigoEspecial = null;
	
	// começa aqui
	@Dado("^que o ticket (especial)?\\s?é (AF345|AB167)$")
	public void queOTicketÉAF(String especial, String arg1) throws Throwable {
		if (especial == null) {
			codigo = arg1;
		} else {
			codigoEspecial = arg1;
		}
	}
	
	@Dado("^que o valor da passagem é R\\$ ([,0-9]*)$")
	public void queOValorDaPassagemÉR$(Float arg1) throws Throwable {
	    valorPassagem = arg1;
	}
	
	@Dado("^que o nome do passageiro é \"(Fulano da Silva|Cicrano de Oliveira)\"$")
	public void queONomeDoPassageiroÉ(String arg1) throws Throwable {
	    nomePassageiro = arg1;
	}
	
	@Dado("^que o nome do passageiro é \"(Beltrano Souza Matos de Alcântara Azevedo)\"$")
	public void queONomeDoPassageiroÉ2k(String arg1) throws Throwable {
	    nomePassageiro = arg1;
	}	
	
	@Dado("^que o telefone do passageiro é (9999-9999|9888-8888)$")
	public void queOTelefoneDoPassageiroÉ(String arg1) throws Throwable {
		telefonePassageiro = arg1;
	}
	
	@Quando("^criar os steps$")
	public void criarOsSteps() throws Throwable {
	}
	
	@Então("^o teste vai funcionar$")
	public void oTesteVaiFuncionar() throws Throwable {
	}	
	
	@Dado("^que o telefone do passageiro é (1234-5678|999-2223)$")
	public void queOTelefoneDoPassageiroÉ2(String arg1) throws Throwable {
		telefonePassageiro = arg1;
	}	
		
	@Dado("^que o ticket é (CD123|AG1234)$")
	public void queOTicketÉCDOUAG(String codigo) throws Throwable {
	}
	
	@Dado("^que o valor da passagem é R\\$ (\\d+)\\.(\\d+),(\\d+)$")
	public void queOValorDaPassagemÉR$(int arg1, int arg2, int arg3) throws Throwable {
	    valorPassagem = Float.valueOf(String.format("%d%d.%d", arg1, arg2, arg3));
	}	
	
}
