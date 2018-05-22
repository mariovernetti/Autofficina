import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import junit.framework.TestCase;

public class NodoTest extends TestCase {

	public void testNodo() 
	{
		LocalDate data= LocalDate.of(2018, 1, 1);
		Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
		
		Nodo nodo = new Nodo(manutenzione);
		assertTrue("Corrispondenza errata",nodo.getInfo().equals(manutenzione)&& nodo.getLink()==null);
	}

	public void testGetLink() 
	{
		LocalDate data= LocalDate.of(2018, 1, 1);
		Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
		
		Nodo nodo = new Nodo(manutenzione);
		assertEquals("Link", null, nodo.getLink());	
	}

	public void testSetLink() {
		LocalDate data= LocalDate.of(2018, 1, 1);
		Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
		
		Nodo nodo = new Nodo(manutenzione);
		Nodo nodo2 = new Nodo(manutenzione);
		
		nodo.setLink(nodo2);
	
		assertEquals("Link", nodo2, nodo.getLink());	
	}

	public void testGetInfo() 
	{
		LocalDate data= LocalDate.of(2018, 1, 1);
		Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
	
		Nodo nodo = new Nodo(manutenzione);
	
		assertEquals("Link", manutenzione, nodo.getInfo());
	}

	public void testSetInfo() 
	{
		LocalDate data= LocalDate.of(2018, 1, 1);
		LocalDate data2= LocalDate.of(2019, 1, 1);
		Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
		Manutenzione manutenzione2 = new Manutenzione("ax13", "Cambio Carrozzeria", data2, 10);
	
		Nodo nodo = new Nodo(manutenzione);
		
		nodo.setInfo(manutenzione2);
	
		assertEquals("Link", manutenzione2, nodo.getInfo());
	}

}
