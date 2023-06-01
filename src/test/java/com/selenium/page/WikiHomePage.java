package com.selenium.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.selenium.MetodosUtiles.*;


public class WikiHomePage {
	WebDriver driver;

	/**
	 * Contructor que en este caso utiliza el driver enviado por parametro
	 **/
	public WikiHomePage(WebDriver ldriver) {
		driver = ldriver;
	}

	// ***** IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR EJEMPLO ID O XPATH 
	@FindBy(id="js-link-box-es")
	private WebElement idiomaEspaniol;
	@FindBy(id = "searchInput")
	private WebElement caja;
	@FindBy(id = "searchLanguage")
	private WebElement languageCombo;

	public void ClickEnEspaniol() throws Exception {
		Assert.assertTrue((idiomaEspaniol.isDisplayed()), "El idioma no se visualiza");	
		idiomaEspaniol.click();
	}

	public String getCaja() {
		Utiles.reportes("Obtiene el contenido de la caja de busqueda");
		return caja.getText();
		}

	public boolean SeVisualizaCaja() {
		Utiles.reportes("Validar que exista la caja de busqueda");
		return caja.isDisplayed();
		}

	public void IngresarDatoCajaBusqueda ( String dato)
		{
		Utiles.reportes("Localizar y comprobar que la caja de busqueda se muestra");
		Assert.assertTrue((caja.isDisplayed()), "La caja de busqueda no se visualiza");
		Utiles.reportes("Ingresar la palabra " + dato);
		caja.sendKeys(dato);
		Utiles.reportes("Presionar Enter");
		caja.sendKeys(Keys.ENTER);
		}
}
