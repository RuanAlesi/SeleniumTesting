import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import Objetos.Categorias;
import Objetos.Produtos;
import Objetos.Sessao;

public class Desafios {
	
	Sessao sessao = new Sessao();
	PassosDeTestes passos = new PassosDeTestes(sessao.getDriver());
	
	@Rule
	public TestName nomeDoTeste = new TestName();
	
	
	@Before
	public void PreparaNavegador() {
		sessao.getDriver().get("https://shopcart-challenge.4all.com/");
	}
	
	@After
	public void EncerraNavegador() {
		sessao.getDriver().quit();
	}
	
	@Test
	public void Desafio1() {
		passos.DefinirCategoria(Categorias.Doces());
		passos.AdicionarProduto(Produtos.Brigadeiro());
		passos.DefinirCategoria(Categorias.Todas());
		passos.IrParaCarrinho();
		passos.AdicionarQuantidade(Produtos.Brigadeiro(), 4);
		passos.FinalizarVenda();
		passos.ValidarMensagemFinalizacao("Pedido realizado com sucesso!");
		passos.PrintarTela(nomeDoTeste.getMethodName() + " - PrintMensagemFinal");
		passos.Encerrar();
	}
	
	@Test
	public void Desafio2() {
		passos.DefinirCategoria(Categorias.Bebidas());
		passos.AdicionarTodosProdutos();
		passos.DefinirCategoria(Categorias.Todas());
		passos.AdicionarProduto(Produtos.RissoleMedio());
		passos.IrParaCarrinho();
		passos.AdicionarQuantidade(Produtos.RissoleMedio(), 9);
		passos.RetirarQuantidade(Produtos.RissoleMedio(), 5);
		passos.ValidarValorTotal();
		passos.PrintarTela(nomeDoTeste.getMethodName() + " - PrintValor");
		passos.FinalizarVenda();
		passos.ValidarMensagemFinalizacao("Pedido realizado com sucesso!");
		passos.PrintarTela(nomeDoTeste.getMethodName() + " - PrintMensagemFinal");
		passos.Encerrar();
	}
}