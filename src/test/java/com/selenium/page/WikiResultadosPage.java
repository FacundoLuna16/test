package com.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

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

}
