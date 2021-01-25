package Objetos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Sessao {
	private WebDriver driver;
	
	public Sessao() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		driver = new ChromeDriver(options);
	}
	
	public WebDriver getDriver (){
		return driver;
	}
}
