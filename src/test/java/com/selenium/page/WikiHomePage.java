package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	public void seleccionarIdioma(WebDriverWait wait, String idioma) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchLanguage")));
		Select selectbuscar = new Select(driver.findElement(By.id("searchLanguage")));
		for (WebElement e : selectbuscar.getOptions()) {
			//System.out.println(e.getText());//muestra todos los idiomas disponible
			if (e.getText().contains(idioma)) {
				Utiles.reportes("Seleccionamos el Idioma: " + e.getText());//muestra el idioma seleccionado
				e.click();
				break;
			}
		}
	}

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

	public void IngresarDatoCajaBusqueda ( String dato){
		
		Utiles.reportes("Esperamos, Localizamos y comprobamos que la caja de busqueda se muestra");
		Assert.assertTrue((caja.isDisplayed()), "La caja de busqueda NO se visualiza");
		Assert.assertTrue(caja.getText().toString().isEmpty(), "La caja de texto NO esta vacia");
		Utiles.reportes("Ingresar la palabra " + dato);
		caja.sendKeys(dato);
		Utiles.reportes("Presionar Enter");
		caja.sendKeys(Keys.ENTER);
		}
}
