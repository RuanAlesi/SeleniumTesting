package Objetos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sessao {
	private WebDriver driver;
	
	public Sessao() {
		driver = new ChromeDriver();
	}
	
	public WebDriver getDriver (){
		return driver;
	}
}
