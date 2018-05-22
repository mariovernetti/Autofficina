import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Test;

public class AutofficinaTest {

	LocalDate data= LocalDate.of(2018, 1, 1);
	LocalDate data2= LocalDate.of(2018, 2, 1);
	LocalDate data3= LocalDate.of(2018, 3, 1);
	
	Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
	Manutenzione manutenzione2 = new Manutenzione("ax13", "Cambio Motore", data2, 20);
	Manutenzione manutenzione3 = new Manutenzione("ax14", "Cambio Carrozzeria", data3, 30);
	
	
	@Test
	public void testAutofficinaCostruttoreGetter() 
	{
		Autofficina autofficina = new Autofficina();
		
		assertEquals("Elementi ", 0, autofficina.getElementi());
	}

	@Test
	public void testAutofficinaCopia() 
	{
		Autofficina autofficina = new Autofficina();
		
		assertEquals("Autofficina copia ", autofficina, new Autofficina(autofficina));	
	}

	@Test
	public void testRegistraManutenzioneInTesta() throws IOException, FileException, AutofficinaException
	{
		Autofficina autofficina = new Autofficina();
		autofficina.registraManutenzioneInTesta(manutenzione);
		
		assertEquals("Registrazione in testa: ", autofficina.getManutenzione(1), manutenzione);
	}

	@Test
	public void testRegistraManutenzioneInCoda() throws AutofficinaException, IOException, FileException
	{
		Autofficina autofficina = new Autofficina();
		autofficina.registraManutenzioneInTesta(manutenzione);
		autofficina.registraManutenzioneInTesta(manutenzione2);
		
		assertEquals("Registrazione in coda: ", autofficina.getManutenzione(1), manutenzione2);
		
		autofficina.registraManutenzioneInCoda(manutenzione3);
		
		assertEquals("Registrazione in coda: ", autofficina.getManutenzione(3), manutenzione3);
		
	}

	@Test
	public void testRegistraManutenzioneInPosizione() throws AutofficinaException, IOException, FileException
	{
		Autofficina autofficina = new Autofficina();
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		autofficina.registraManutenzioneInTesta(manutenzione2);
		
		autofficina.registraManutenzioneInPosizione(2, manutenzione3);
		
		assertEquals("Registrazione in posizione: ", autofficina.getManutenzione(2), manutenzione3);
	
		
	}

	@Test
	public void testEliminaInTesta() throws AutofficinaException, IOException, FileException
	{
		Autofficina autofficina = new Autofficina();
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		autofficina.registraManutenzioneInTesta(manutenzione2);
		autofficina.registraManutenzioneInTesta(manutenzione3);
		
		autofficina.eliminaInTesta();
		assertEquals("Elimina in testa: ", autofficina.getManutenzione(1), manutenzione2);
	}

	@Test
	public void testEliminaInCoda() throws IOException, FileException, AutofficinaException
	{
		Autofficina autofficina = new Autofficina();
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		autofficina.registraManutenzioneInTesta(manutenzione2);
		autofficina.registraManutenzioneInTesta(manutenzione3);
		
		autofficina.eliminaInCoda();
		
		assertEquals(autofficina.getManutenzione(2), manutenzione2);
	}

	@Test
	public void testEliminaInPosizione() throws IOException, FileException, AutofficinaException
	{
		Autofficina autofficina = new Autofficina();
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		autofficina.registraManutenzioneInTesta(manutenzione2);
		autofficina.registraManutenzioneInTesta(manutenzione3);
		
		autofficina.eliminaInPosizione(2);
		assertEquals(autofficina.getManutenzione(2), manutenzione);
		
		autofficina.eliminaInPosizione(1);
		assertEquals(autofficina.getManutenzione(1), manutenzione);
	}

	@Test
	public void testEliminaManutenzione() throws IOException, FileException, AutofficinaException
	{
		Autofficina autofficina = new Autofficina();
		
		Manutenzione manutenzione4 = new Manutenzione("ax12", "Cambio Casa", data, 10);
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		autofficina.registraManutenzioneInTesta(manutenzione2);
		autofficina.registraManutenzioneInTesta(manutenzione4);
		
		autofficina.eliminaManutenzione("ax12");
		
		assertEquals(autofficina.getManutenzione(1), manutenzione2);
	}

	@Test
	public void testGetManutenzione() throws IOException, FileException, AutofficinaException
	{
		Autofficina autofficina = new Autofficina();
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		autofficina.registraManutenzioneInTesta(manutenzione2);
		autofficina.registraManutenzioneInTesta(manutenzione3);	
		
		assertEquals(autofficina.getManutenzione(1), manutenzione3);
	}

	@Test
	public void testStampaRapporto() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testProssimaManutenzione() throws IOException, FileException 
	{
		
		Autofficina autofficina = new Autofficina();
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		LocalDate data2= LocalDate.of(2018, 1, 11);
		
		assertEquals(data2, autofficina.prossimaManutenzione(manutenzione));
	}

	@Test
	public void testSalvaAutofficina() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testCaricaAutofficina() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testVisualizzaManutenzioniMacchina() throws IOException, FileException, AutofficinaException 
	{
		Autofficina autofficina = new Autofficina();
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		
		assertEquals("2018-01-01 --> ax12", autofficina.visualizzaManutenzioniMacchina("ax12"));
	}

	@Test
	public void testVisualizzaProssimeManutenzioni() throws IOException, FileException, AutofficinaException 
	{
		Autofficina autofficina = new Autofficina();
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		
		assertEquals("2018-01-01 --> Cambio Olio", autofficina.visualizzaProssimeManutenzioni("ax12"));
	}

	@Test
	public void testVisualizzaManutenzioniGiorno() throws IOException, FileException, AutofficinaException 
	{
		Autofficina autofficina = new Autofficina();
		
		LocalDate data2= LocalDate.of(2018, 1, 11);
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		
		assertEquals("ax12 --> Cambio Olio", autofficina.visualizzaManutenzioniGiorno(data2));
	}

	@Test
	public void testVisualizzaProssimeManutenzioniProgrammate() throws IOException, FileException 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testVisualizzaTutteManutenzioni() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testConvertiListaArray() throws IOException, FileException, AutofficinaException
	{
		Autofficina autofficina = new Autofficina();
		
		autofficina.registraManutenzioneInTesta(manutenzione);
		
		Manutenzione[] array = autofficina.convertiListaArray();
		
		assertTrue(array[0].equals(autofficina.getManutenzione(1)));
	}

	@Test
	public void testToString() 
	{
		fail("Not yet implemented");
	}

}
