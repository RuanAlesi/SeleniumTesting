import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PassosDeTestes {
	
	private WebDriver driver;

	public PassosDeTestes(WebDriver driver) {
		this.driver = driver;
	}
	
	public void DefinirCategoria(String categoria) {
		driver.findElement(By.id("open-categories-btn")).click();
		driver.findElement(By.id(categoria)).click();
	}
	
	public void IrParaCarrinho() {
		driver.findElement(By.id("cart-btn")).click();
	}
	
	public void FinalizarVenda() {
		driver.findElement(By.id("finish-checkout-button")).click();
	}
	
	public void Encerrar() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div/button")).click();
	}
	
	public void AdicionarQuantidade(String produto, Integer qtd) {
		for (int i = 0; i < qtd; i++) {
			driver.findElement(By.xpath("//*[@id=\"add-" + produto + "-qtd\"]/span")).click();			
		}
	}
	
	public void AdicionarProduto(String produto) {
		driver.findElement(By.id("add-" + produto + "-btn")).click();
	}
	
	public void AdicionarTodosProdutos () {
		List<WebElement> listaDeItens = driver.findElements(By.xpath("//*[@class=\"sc-gZMcBi gPZwOR\"]"));
		
		for (WebElement i: listaDeItens) {
			i.click();
		}
	}

	public void RetirarQuantidade(String produto, int qtd) {
		for (int i = 0; i < qtd; i++) {
			driver.findElement(By.xpath("//*[@id=\"remove-" + produto+ "-qtd\"]/span")).click();			
		}
	}

	public void ValidarValorTotal() {
		List<WebElement> listaDeProdutos = driver.findElements(By.xpath("//*[@class=\"sc-csuQGl wfUhd\"]"));
		
		float totalProdutos = 0;
		int cont = 1;
		
		for (WebElement i: listaDeProdutos) {
			int qtd = Integer.parseInt(i.findElement(By.xpath("/html/body/div/div[2]/div[2]/ul/li["+ cont +"]/div/div/p")).getText());
			float vlProduto = Float.parseFloat(i.findElement(By.xpath("/html/body/div/div[2]/div[2]/ul/li["+ cont +"]/p")).getText().replace(',', '.').replace("R$ ", ""));
			totalProdutos += (qtd * vlProduto);
			cont++;
		}
		
		String total = driver.findElement(By.id("subtotal-price")).getText();
		total = total.replace("R$ ", "");
		
		assertEquals(Float.parseFloat(total.replace(',', '.')), totalProdutos, 2);
	}

	public void ValidarMensagemFinalizacao(String mensagem){
		String msg = driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div/h2")).getText();
		assertEquals(mensagem, msg);
	}

	public void PrintarTela(String nomeTeste) {
		try {
			TakesScreenshot print = (TakesScreenshot) driver;
			File arquivo = print.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(arquivo, new File(nomeTeste + ".jpg"));
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
