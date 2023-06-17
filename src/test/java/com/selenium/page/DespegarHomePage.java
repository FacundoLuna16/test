package com.selenium.page;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.MetodosUtiles.Utiles;

public class DespegarHomePage {
	WebDriver driver;
	WebElement btnNoLogin;
	LocalDate fechaActual;
	int diaActual;
	int mesActual;
	int anioActual;
	String fecha;
	String xpathIda;
	String xpathVuelta;
	WebElement selectFechaIda;
	WebElement selectFechaVuelta;
	WebElement btnAplicarPeriodo;
	WebElement habitaciones;

	@FindBy(xpath = "//input[@placeholder='Ingresa hacia dónde viajas']")
	private WebElement txtDestino;
	@FindBy(xpath = "//input[@placeholder='Ingresa desde dónde viajas']")
	private WebElement txtOrigen;
	@FindBy(xpath = "//*[@class='lgpd-banner--button shifu-3-btn -white -md']")
	private WebElement btnCookies;
	@FindBy(xpath = "//input[@placeholder='Ida']")
	private WebElement fechaIda;
	@FindBy(xpath = "//input[@placeholder='Vuelta']")
	private WebElement fechaVuelta;
	@FindBy(xpath = "//em[normalize-space()='Buscar']")
	private WebElement btnBuscar;
	@FindBy(xpath = "//*[@class='login-incentive--button login-incentive--button-close shifu-3-btn-ghost']")
	private WebElement btnEscLogin;

	public DespegarHomePage(WebDriver ldriver) {
		this.driver = ldriver;
		fechaActual = LocalDate.now();
		diaActual = fechaActual.getDayOfMonth();
		mesActual = fechaActual.getMonthValue();
		anioActual = fechaActual.getYear();
	}

	public void cerrarLogin() {
		try {
		    // Código que puede generar un error
			btnEscLogin.click();
			
			Utiles.reportes("Cerramos el login a la pagina");
		} catch (Exception excepcion) {
		    // Reportamos la Exceptcion
		    Utiles.reportes("No se pudo ejecutar: " + excepcion.getMessage());
		}
	}

	public void aceptarCookies() {
		btnCookies.click();
		Utiles.reportes("Aceptamos  las Cookies.");
	}

	public void ingresarSalida(String salida) throws Exception {
		Assert.assertTrue(txtOrigen.isDisplayed(), "La caja de texto Origen, NO esta Visible");
		txtOrigen.click();
		txtOrigen.clear();
		txtOrigen.sendKeys(salida);
		Thread.sleep(2000);
		txtDestino.sendKeys(Keys.CONTROL);
		Thread.sleep(2000);
		txtDestino.sendKeys(Keys.ENTER);
		Utiles.reportes("Ingresamos el Origen: " + salida);
	}

	public void ingresarDestino(String destino) throws Exception {
		Assert.assertTrue(txtDestino.isDisplayed(), "La caja de texto Destino, NO esta Visible");
		txtDestino.click();
		txtDestino.sendKeys(destino);
		Thread.sleep(2000);
		txtDestino.sendKeys(Keys.CONTROL);
		Thread.sleep(2000);
		txtDestino.sendKeys(Keys.ENTER);
		Utiles.reportes("Ingresamos el destino:" + destino);
	}

	
	private String calcularFechaRegreso(int dia, int mes, int anio, int duracion) {
	    int diasEnMes = obtenerDiasEnMes(mes, anio);
	    int diaRegreso = dia + duracion;
	    int mesRegreso = mes;
	    int anioRegreso = anio;

	    if (diaRegreso > diasEnMes) {
	        diaRegreso -= diasEnMes;
	        mesRegreso++;

	        if (mesRegreso > 12) {
	            mesRegreso = 1;
	            anioRegreso++;
	        }
	    }

	    return String.format("%04d-%02d-%02d", anioRegreso, mesRegreso, diaRegreso);
	}
	private int obtenerDiasEnMes(int mes, int anio) {
	    int[] diasEnMeses = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	    if (mes == 2 && esAnioBisiesto(anio)) {
	        return 29; // Febrero tiene 29 días en años bisiestos
	    }

	    return diasEnMeses[mes - 1];
	}

	private boolean esAnioBisiesto(int anio) {
	    return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
	}
	


	public void seleccionarFechaIda(int salida,int duracion) throws Exception {
		
		String fechaida = String.format("%04d-%02d", anioActual, mesActual);
		int diasDelMes = obtenerDiasEnMes(mesActual,anioActual);
		int diaSalida= diaActual + salida;
		//seteamos las variables a utilizar
		if (diaSalida > diasDelMes) {
			
			diaSalida = diasDelMes - diaActual + salida;//me da la diferencia 
			mesActual ++;
			fechaida= String.format("%04d-%02d", anioActual , mesActual); 
			xpathIda = "//div[@data-month='" + fechaida
					+ "']//div[@class='sbox5-monthgrid-datenumber-number'][normalize-space()='" + (5)
					+ "']";
		}else {
		xpathIda = "//div[@data-month='" + fechaida
				+ "']//div[@class='sbox5-monthgrid-datenumber-number'][normalize-space()='" + (diaActual + salida)
				+ "']";
		}
		
		selectFechaIda = driver.findElement(By.xpath(xpathIda));
		
		// Seleccionamos la fecha de ida
		fechaIda.click();
		Thread.sleep(2000);
		Assert.assertTrue(selectFechaIda.isEnabled(), "La grilla de fecha Ida NO esta disponible");
		selectFechaIda.click();
		Utiles.reportes("Seleccionamos la fecha de ida: " + fechaida + "-" + (diaActual + salida));
	}

	public void seleccionFechaVuelta(int salida,int duracion)throws Exception {
				
		String fechaRegreso = calcularFechaRegreso(diaActual + salida, mesActual, anioActual,duracion);
		fecha = fechaRegreso.substring(0, 7);
		int diaRegreso= Integer.parseInt(fechaRegreso.substring(8));
		xpathVuelta = "//div[@data-month='" + fecha
				+ "']//div[@class='sbox5-monthgrid-datenumber-number'][normalize-space()='"
				+ (diaRegreso) + "']";
		selectFechaVuelta = driver.findElement(By.xpath(xpathVuelta));
		
		fechaVuelta.click();
		Assert.assertTrue(selectFechaVuelta.isEnabled(),"La grilla de fecha vuelta NO esta disponible");
		selectFechaVuelta.click();
		Utiles.reportes("Seleccionamos la fecha de vuelta:" + fecha +"-"+(diaRegreso));
		
		//Aplicamos las fechas seleccionadas
		btnAplicarPeriodo = driver.findElement(By.xpath("//button[@class='sbox5-3-btn -primary -md']"));
		Assert.assertTrue(btnAplicarPeriodo.isDisplayed(),"El boton Aplicar NO esta disponible");
		btnAplicarPeriodo.click();
		Utiles.reportes("Aceptamos");
	}
	public void realizarBusqueda() {
		btnBuscar.click();
		Utiles.reportes("Clickeamos el boton Buscar");
	}
	public void verificarUrlBusqueda(WebDriverWait wait) {
		wait.until(ExpectedConditions.urlContains("results"));
		Utiles.reportes("Los resultados de la busqueda se realizaron de forma exitosa!");
	}

}
