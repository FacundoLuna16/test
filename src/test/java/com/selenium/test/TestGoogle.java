package com.selenium.test;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.selenium.MetodosUtiles.*;
import com.selenium.driver.DriverFactory;
import com.selenium.page.GoogleHomePage;

import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;

public class TestGoogle {
	WebDriver driver;
	WebDriverWait wait;
		
		@BeforeMethod()
		public void abrirBrowser(ITestContext context) throws Exception {
			//Seteamos el sistema
			driver = DriverFactory.LevantarBrowser(driver,context);
			wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		}
		
		@AfterMethod()
		public void cerrarBrowser() throws Exception{
			DriverFactory.FinalizarBrowser(driver);
		}
		
		@DataProvider(name = "datos")
		public Object[][] createData() {
		 return new Object[][] {
		   { "Selenium","Selenium"},
		   {  "TDD", "TDD"},
		   {"Utn","Utn"} 
		 };
		}
		
		@Test(dataProvider = "datos" , description = "Buscar los elementos de google.com")
		public void validarGoogle(String busqueda, String resultado) throws Exception {		
			
			GoogleHomePage googleHomePage = PageFactory.initElements(driver, GoogleHomePage.class);
			googleHomePage.ingresarDatosBusqueda(busqueda);
			Utiles.reportes("validar que realice la busqueda correcta desde la URL");
			//Verificamos si la url contiene la busqueda
			googleHomePage.validarUrlBusqueda(wait, resultado);
			Utiles.reportes("Fin de la prueba");
			
		}
}
