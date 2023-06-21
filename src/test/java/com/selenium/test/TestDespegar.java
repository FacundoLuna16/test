package com.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.selenium.MetodosUtiles.*;
import com.selenium.driver.DriverFactory;
import com.selenium.page.DespegarHomePage;
import org.openqa.selenium.support.PageFactory;
import java.time.LocalDate;

public class TestDespegar {
	WebDriver driver;
	WebElement btnEscLogin;
	WebElement btnNoLogin;
	WebElement btnCookies;
	WebElement txtDestino;
	WebElement txtOrigen;
	LocalDate fechaActual;
	int diaActual;
	int mesActual;
	int anioActual;
	String fecha;
	String xpathIda;
	String xpathVuelta;
	WebElement fechaIda;
	WebElement selectFechaIda;
	WebElement fechaVuelta;
	WebElement selectFechaVuelta;
	WebElement btnAplicarPeriodo;
	WebElement habitaciones;
	WebElement btnBuscar;
	

	@BeforeMethod()
	public void abrirBrowser(ITestContext context) throws Exception {
		driver = DriverFactory.LevantarBrowser(driver, context);
		
	}

	@AfterMethod()
	public void cerrarBrowser() throws Exception {
		Utiles.reportes("Fin CP.");
		DriverFactory.FinalizarBrowser(driver);

	}

	@DataProvider(name = "datos")
	public Object[][] createData() {
		return new Object[][] {
				// Origen      ,Destino    , Fecha ida(cant dias a partir de hoy), Fecha Vuelta(cant dias),CantAdultos,CantNiños,clase
				{ "Buenos Aires, Argentina", "San Carlos de Bariloche, Rio Negro, Argentina", 2, 13,2,1,"Económica"},
				{ "Buenos Aires, Argentina", "Villa Carlos Paz, Córdoba, Argentina", 2, 3,1,2,"Premium economy"},
				{ "Buenos Aires, Argentina", "Villa La Angostura, Neuquén, Argentina", 2, 4,2,1,"Primera Clase"} };
	}

	@Test(dataProvider = "datos", description = "Buscar vuelos en Despegar.com")
	public void validarDespegar(String Origen, String destino, int salida, int duracion, int cantAdultos, int cantNinos,String clase) throws Exception {

		DespegarHomePage despegarHomePage = PageFactory.initElements(driver, DespegarHomePage.class);
		
		//despegarHomePage.aceptarCookies();
		
		despegarHomePage.cerrarLogin();

		// Ingresar Origen
		despegarHomePage.ingresarSalida(Origen);

		// Ingresar Destino
		despegarHomePage.ingresarDestino(destino);

		// Seleccion dias
		despegarHomePage.seleccionarFechaIda(salida, duracion);

		// Seleccionamos la fecha de vuelta
		despegarHomePage.seleccionFechaVuelta(salida, duracion);
		
		// Agregar habitacion y Personas
		//TODO terminar las esperas inteligente para el correcto funcionamiento de la funcion
		despegarHomePage.seleccionarCantPersonas(cantAdultos,cantNinos,clase);

		// Buscamos los Paquetes
		despegarHomePage.realizarBusqueda();

		// Verificamos si la url es de la busqueda
		despegarHomePage.verificarUrlBusqueda();

	}
}
