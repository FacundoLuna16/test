package com.selenium.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.MetodosUtiles.Utiles;

public class GoogleHomePage {
	WebDriver driver;
	public GoogleHomePage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	@FindBy(id="APjFqb")
	private WebElement inputBusqueda;
	
	public void ingresarDatosBusqueda(String dato) {
		Assert.assertTrue(inputBusqueda.isDisplayed(),"La caja de busqueda no esta dispobible");
		Utiles.reportes("Buscamos la caja de busqueda");
		inputBusqueda.sendKeys(dato);
		Utiles.reportes("Ingresa a buscar:" + dato);
		inputBusqueda.sendKeys(Keys.ENTER);
		Utiles.reportes("Presiona enter realizar la busqueda");
	}
	//No me parece necesario crear una clase aparte para identificar si la url contiene la busqueda
	public void validarUrlBusqueda(WebDriverWait wait, String resultado) {
		wait.until(ExpectedConditions.urlContains(resultado));
		Assert.assertTrue(SeVisualizaCaja(),"No se visualiza la caja correctamente");
		Utiles.reportes("La busqueda fue realizada con exito");
		
	}
	public boolean SeVisualizaCaja() {
		return inputBusqueda.isDisplayed();
	}

}
