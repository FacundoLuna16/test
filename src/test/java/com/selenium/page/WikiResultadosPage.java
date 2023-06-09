package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.selenium.MetodosUtiles.Utiles;

public class WikiResultadosPage {

	WebDriver driver;

	public WikiResultadosPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(id = "firstHeading")
	private WebElement lblTitulo;

	public String ObtenerTitulo() {
		Reporter.log("Localizar y comprobar que el titulo este disponible");
		return lblTitulo.getText();
	}
	
	public void ValidarTitulo(WebDriverWait wait,String titulo) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
		WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
		Assert.assertTrue(tituloResultado.getText().contains(titulo), "El titulo No es el correcto");
		Utiles.reportes("Texto encontrado " + tituloResultado.getText());
	}

}
