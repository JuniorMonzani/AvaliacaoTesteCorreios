package tests;

import static org.junit.Assert.*;

import java.awt.List;
import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AvaliacaoTestsCorreios {
	public WebDriver driver;
	public String logradouro;
	public String bairro;
	public String uf;
	public String cep;
	public List allRows;
	
	@Before
	public void setUp() throws Exception {
		//->Abre o navegador Google Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void test1OpenSiteCorreios() throws InterruptedException {
		//->Abre no navegador o link do site dos Correios na tela de busca por CEP
		driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/BuscaCepEndereco.cfm");
		
		//->Verifica se a p�gina aberta realmente � a esperada
		assertTrue("T�tulo da p�gina � diferente do esperado", driver.getTitle().contentEquals("buscaCepEndere�o"));
	}
	
	@Test
	public void test2SearchCEP() throws InterruptedException {

		driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/BuscaCepEndereco.cfm");
		
		//->Busca por elemento que � o campo para inputar o CEP
		driver.findElement(By.name("relaxation")).sendKeys("04686-002");
		
		//->Espera de 2s para visualizar 
		Thread.sleep(1000);
		
		//->Busca por elemento afim de encontrar o bot�o 'Buscar'
		WebElement buttonBuscar =  driver.findElement(By.cssSelector("input[type='submit'"));		
		//->Clica no bot�o 'Buscar' para realizar a pesquisa por CEP
		buttonBuscar.click();
		
		//->Obtem as informa��es como endere�o, bairro, UF e CEP a partir do CEP pesquisado	guardando nas respectivas vari�veis
		logradouro = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[1]")).getText();
		bairro = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[2]")).getText();
		uf = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[3]")).getText();
		cep = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[4]")).getText();
		
		//->Faz o output das informa��es de endere�o encontradas
		System.out.printf("Endere�o: %s\n Bairro: %s\n UF: %s\n CEP: %s\n", logradouro, bairro, uf, cep);
		
		//->Compara se as informa��es retornadas correspondem ao esperado
		assertEquals("Avenida Nossa Senhora do Sabar� - de 1632 a 2740 - lado par ", logradouro);
		assertEquals("Jardim Campo Grande ", bairro);
		assertEquals("S�o Paulo/SP ", uf);
		assertEquals("04686-002", cep);
		
		//->Espera de 3s para verificar o resultado das a��es
		Thread.sleep(2000);
	}	

	@Test
	public void test3SearchAddress() throws InterruptedException {
		
		driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/BuscaCepEndereco.cfm");
		
		//->Busca por elemento que � o campo para inputar o Logradouro
		driver.findElement(By.name("relaxation")).sendKeys("Rua Juan Vicente");
		//->Busca por elemento afim de encontrar o bot�o 'Buscar'
		
		//->Espera de 3s para verificar o resultado das a��es
		Thread.sleep(1000);
		
		WebElement buttonBuscar =  driver.findElement(By.cssSelector("input[type='submit'"));		
		//->Clica no bot�o 'Buscar' para realizar a pesquisa por CEP
		buttonBuscar.click();
				
		//->Busca por elemento afim de encontrar o bot�o 'Buscar'
		String resultTable = driver.findElement(By.className("tmptabela")).getText();		
		System.out.print(resultTable);
		
		//->Espera de 3s para verificar o resultado das a��es
		Thread.sleep(2000);
	}
}