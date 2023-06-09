package com.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.page.*;
import com.selenium.driver.*;
import com.selenium.MetodosUtiles.*;

public class TestCaseWiki2 {
	WebDriver driver;

	@DataProvider(name = "datos")
	public Object[][] createData() {
		return new Object[][] { 
			{ "Selenium", "Selenium" }, 
			{ "TDD", "Desarrollo guiado por pruebas" },
			{"JAVA","Java (lenguaje de programación)"},
			{ "DATA DRIVEN TESTING", "Data-driven testing" } 
		};
	}
	//TODO "Crear un metodo para obtener datos externos."
	@BeforeMethod
	public void abrirBrowser(ITestContext context) {
		
		driver = DriverFactory.LevantarBrowser(driver, context);
	}
	@Test(dataProvider = "datos", description="Validar y Verificar que Wikipedia cotiene el campo")
	public void validarCajaTextoNuevo(String varBuscar, String resultado) throws Exception{
		WikiHomePage wikihomepage = PageFactory.initElements(driver, WikiHomePage.class);
		wikihomepage.IngresarDatoCajaBusqueda(varBuscar);
		
		WikiResultadosPage wikiRdopage= PageFactory.initElements(driver, WikiResultadosPage.class);
		Utiles.reportes("Validar que el titulo sea "+resultado);
		Assert.assertTrue((wikiRdopage.ObtenerTitulo().contains(resultado)), "El valor "+resultado+" no se encuentra en el titulo");
	}
	
	@AfterMethod
	public void CerrarBrowser() {
		Utiles.reportes("Cerrar Browser");
		DriverFactory.FinalizarBrowser(driver);
	}
	
	
}
