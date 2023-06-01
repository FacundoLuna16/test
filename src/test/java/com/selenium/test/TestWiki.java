package com.selenium.test;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.selenium.MetodosUtiles.*;

public class TestWiki {

	WebDriver driver;

	@BeforeMethod()
	public void preCondiciones() throws Exception {
		// Seteamos el sistema
		System.setProperty("webdriver.chrome.driver", "C:/chrome/chromedriver.exe");
		//TODO "utilizar dataprovide "
		driver = new ChromeDriver();
		Utiles.reportes("Inicializamos el Browser");

		// Ubicacion pagina
		driver.get("http://wikipedia.org");
		Utiles.reportes("Ingresamos en la pagina http://wikipedia.org");
		Thread.sleep(2000);
	}

	@AfterMethod()
	public void postCondiciones() throws Exception {
		driver.close();
	}

	@DataProvider(name = "datos")
	public Object[][] createData() {
		return new Object[][] { { "Selenium", "Selenium" }, { "TDD", "Desarrollo guiado por pruebas" },
				{ "DATA DRIVEN TESTING", "Data-driven testing" } };
	}

	@Test(dataProvider = "datos", description = "Validar que las busquedas en Wikipedia funcionan")
	public void ValidarBusquedaWikilistas(String varBuscar, String resultado) throws Exception {
		// elemento busqueda
		WebElement searchInput = driver.findElement(By.id("searchInput"));
		Assert.assertTrue(searchInput.isDisplayed(), "La caja de texto NO esta Visible");
		Assert.assertTrue(searchInput.getText().toString().isEmpty(), "La caja de texto NO esta vacia");
		Utiles.reportes("Indentificamos la caja de busqueda");
		Utiles.reportes("Identificar el combo de idiomas");

		Select selectbuscar = new Select(driver.findElement(By.id("searchLanguage")));
		for (WebElement e : selectbuscar.getOptions()) {
			System.out.println(e.getText());
			if (e.getText().contains("Español")) {
				e.click();
				break;
			}
		}

		// Agregamos y enviamos para buscar Selenium
		searchInput.sendKeys(varBuscar);// agregamos texto
		Thread.sleep(2000);
		Utiles.reportes("Ingresamos para buscar " + varBuscar);
		searchInput.submit();// enviamos la peticion
		Utiles.reportes("Enviamos la peticion");
		// Validando titulo de la busqueda
		WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
		Assert.assertTrue(tituloResultado.getText().contains(resultado), "El titulo No es el correcto");
		Utiles.reportes("Texto encontrado " + tituloResultado.getText());
	}

	@Test(dataProvider = "datos", description = "Validar que las busquedasen Wikipedia funcionan")
	public void ValidarBusquedaWikipedia(String varBuscar, String resultado) throws Exception {

		// elemento busqueda
		WebElement searchInput = driver.findElement(By.id("searchInput"));
		Assert.assertTrue(searchInput.isDisplayed(), "La caja de texto NO esta Visible");

		// System.out.println(searchInput.getText().getClass());Es del tipo String
		Assert.assertTrue(searchInput.getText().toString().isEmpty(), "La caja de texto NO esta vacia");
		Utiles.reportes("Indentificamos la caja de busqueda");

		// Agregamos y enviamos para buscar Selenium
		searchInput.sendKeys(varBuscar);// agregamos texto
		Thread.sleep(2000);
		Utiles.reportes("Ingresamos para buscar " + varBuscar);
		searchInput.submit();// enviamos la peticion
		Utiles.reportes("Enviamos la peticion");

		// Validando titulo de la busqueda
		WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
		Assert.assertTrue(tituloResultado.getText().contains(resultado), "El titulo No es el correcto");
		Utiles.reportes("Texto encontrado " + tituloResultado.getText());

	}
	/*
	 * 
	 * @Test(description =
	 * "Verificamos que los elementos se identifiquen en la pantalla") public void
	 * validarElementosWiki() throws Exception {
	 * 
	 * //1--------------------------------- elemento
	 * TituloBody--------------------------------- WebElement lvltituloPagina =
	 * driver.findElement(By.
	 * xpath("//*[@class='central-textlogo__image sprite svg-Wikipedia_wordmark']"))
	 * ; Assert.assertTrue(lvltituloPagina.getText().contains("Wikipedia")
	 * ,"El titulo No es el correcto");
	 * Utiles.reportes("Identificamos el titulo de la pagina principal");
	 * 
	 * //2--------------------------------- elemento
	 * IdiomalblEspanion--------------------------------- WebElement linkEspaniol =
	 * driver.findElement(By.id("js-link-box-es"));
	 * Assert.assertTrue(linkEspaniol.isDisplayed());
	 * Utiles.reportes("El link para español esta disponible");
	 * 
	 * //3--------------------------------- elemento
	 * IdiomaPyccknn--------------------------------- WebElement linkPyccknn =
	 * driver.findElement(By.id("js-link-box-ru"));
	 * Assert.assertTrue(linkPyccknn.isDisplayed());
	 * Utiles.reportes("El link del idioma Pyccknn esta disponible");
	 * 
	 * //4--------------------------------- elemento
	 * IdiomaDeutsch--------------------------------- WebElement linkDeutsch =
	 * driver.findElement(By.id("js-link-box-de"));
	 * Assert.assertTrue(linkDeutsch.isDisplayed());
	 * Utiles.reportes("El link del idioma Deutsch esta disponible");
	 * 
	 * //5--------------------------------- elemento
	 * IdiomaJapones--------------------------------- WebElement linkJapones =
	 * driver.findElement(By.id("js-link-box-ja"));
	 * Assert.assertTrue(linkJapones.isDisplayed());
	 * Utiles.reportes("El link del idioma Japones esta disponible");
	 * 
	 * //6--------------------------------- elemento
	 * IdiomaChino?--------------------------------- WebElement idiomaChino =
	 * driver.findElement(By.id("js-link-box-zh"));
	 * Assert.assertTrue(idiomaChino.isDisplayed());
	 * Utiles.reportes("El link del idioma Chino esta disponible");
	 * 
	 * //7--------------------------------- elemento Busqueda
	 * --------------------------------- WebElement txtBusqueda =
	 * driver.findElement(By.id("searchInput"));
	 * Assert.assertTrue(txtBusqueda.isDisplayed());
	 * Utiles.reportes("El campo de busqueda esta disponible");
	 * 
	 * //8--------------------------------- elemento
	 * botonBusqueda--------------------------------- WebElement btnBuscar =
	 * driver.findElement(By.
	 * xpath("//*[@class='pure-button pure-button-primary-progressive']"));
	 * Assert.assertTrue(btnBuscar.isDisplayed());
	 * Utiles.reportes("El boton Buscar esta Disponible");
	 * 
	 * //9--------------------------------- elemento
	 * desplegableDeIdiomas--------------------------------- WebElement
	 * desplegableDeIdiomas = driver.findElement(By.id("js-lang-list-button"));
	 * Assert.assertTrue(desplegableDeIdiomas.isDisplayed());
	 * Utiles.reportes("La grilla de idiomas esta disponible");
	 * 
	 * //10--------------------------------- elemento linkDonacion
	 * --------------------------------- WebElement linkDonacion =
	 * driver.findElement(By.xpath("//div[@class='footer-sidebar-text']/a"));
	 * Assert.assertTrue(linkDonacion.isDisplayed());
	 * Utiles.reportes("El link del donacion esta disponible");
	 * 
	 * //11--------------------------------- elemento linkCommons
	 * --------------------------------- WebElement linkCommons =
	 * driver.findElement(By.xpath("//a[@href='//commons.wikimedia.org/']"));
	 * Assert.assertTrue(linkCommons.isDisplayed());
	 * Utiles.reportes("El link de commons esta disponible");
	 * 
	 * //12--------------------------------- elemento link WikiViajes
	 * --------------------------------- WebElement linkWikiViajes =
	 * driver.findElement(By.xpath("//a[@href='//www.wikivoyage.org/']"));
	 * Assert.assertTrue(linkWikiViajes.isDisplayed());
	 * Utiles.reportes("El link de Viajes esta disponible");
	 * 
	 * //13--------------------------------- elemento link Wikcionario
	 * --------------------------------- WebElement linkWikcionario =
	 * driver.findElement(By.xpath("//a[@href='//www.wiktionary.org/']"));
	 * Assert.assertTrue(linkWikcionario.isDisplayed());
	 * Utiles.reportes("El link de diccionario esta disponible");
	 * 
	 * //14--------------------------------- elemento link
	 * WikiData--------------------------------- WebElement linkWikiData =
	 * driver.findElement(By.xpath("//a[@href='//www.wikidata.org/']"));
	 * Assert.assertTrue(linkWikiData.isDisplayed());
	 * Utiles.reportes("El link de WikiDate esta disponible");
	 * 
	 * //15--------------------------------- elemento
	 * terminosDeUso--------------------------------- WebElement linkterminosDeUso =
	 * driver.findElement(By.xpath(
	 * "//a[@href='https://meta.wikimedia.org/wiki/Terms_of_use/es']"));
	 * Assert.assertTrue(linkterminosDeUso.isDisplayed());
	 * Utiles.reportes("El link de terminos de Uso esta disponible");
	 * 
	 * //16--------------------------------- elemento WikiNoticias
	 * --------------------------------- WebElement linkWikiNoticias=
	 * driver.findElement(By.xpath("//a[@href='//www.wikinews.org/']"));
	 * Assert.assertTrue(linkWikiNoticias.isDisplayed());
	 * Utiles.reportes("El link de Noticias esta disponible");
	 * 
	 * //17--------------------------------- elemento
	 * WikiVersidad--------------------------------- WebElement linkWikiVersidad =
	 * driver.findElement(By.xpath("//a[@href='//www.wikiversity.org/']"));
	 * Assert.assertTrue(linkWikiVersidad.isDisplayed());
	 * Utiles.reportes("El link de Universidad esta disponible");
	 * 
	 * //18--------------------------------- elemento
	 * DowloadAppStore--------------------------------- WebElement linkWikiAppStore=
	 * driver.findElement(By.xpath(
	 * "//a[@href='https://itunes.apple.com/app/apple-store/id324715238?pt=208305&ct=portal&mt=8']"
	 * )); Assert.assertTrue(linkWikiAppStore.isDisplayed());
	 * Utiles.reportes("El link de AppStore esta disponible");
	 * 
	 * //19--------------------------------- elemento WikiEspecies
	 * --------------------------------- WebElement linkWikiEspecie =
	 * driver.findElement(By.xpath("//a[@href='//species.wikimedia.org/']"));
	 * Assert.assertTrue(linkWikiEspecie.isDisplayed());
	 * Utiles.reportes("El link de Especies esta disponible");
	 * 
	 * //20--------------------------------- elemento
	 * WikiQuote--------------------------------- WebElement linkWikiQuote =
	 * driver.findElement(By.xpath("//a[@href='//www.wikiquote.org/']"));
	 * Assert.assertTrue(linkWikiQuote.isDisplayed());
	 * Utiles.reportes("El link de Quote esta disponible");
	 * 
	 * }
	 */

}
