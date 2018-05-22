import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class ManutenzioneTest {

	@Test
	public void testCostruttore() 
	{
		LocalDate data= LocalDate.of(2018, 1, 1);
		Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
		
		manutenzione.setCodiceIdentificativo(1);
		
		assertEquals("Codice Idetificativo: ", 1, manutenzione.getCodiceIdentificativo());
		assertEquals("Matricola: ","ax12", manutenzione.getMatricolaAutomezzo());
		assertEquals("Intervento: ", "Cambio Olio",manutenzione.getDescrizioneIntervento());
		assertEquals("Data: ", data, manutenzione.getDataManutenzione());
		assertEquals("Giorni: ", 10, manutenzione.getProssimoIntervento());
	}

	@Test
	public void testManutenzioneCopia() 
	{
		LocalDate data= LocalDate.of(2018, 1, 1);
		Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
		
		manutenzione.setCodiceIdentificativo(1);
		
		assertEquals("Costruttore copia ", manutenzione, new Manutenzione(manutenzione));	
	}

	@Test
	public void testManutenzioneVuoto() 
	{
		Manutenzione manutenzioneVuoto = new Manutenzione("","",null,0);
		manutenzioneVuoto.setCodiceIdentificativo(1);
		
		Manutenzione manutenzione = new Manutenzione();
		assertEquals("Costruttore vuoto creato errato", manutenzioneVuoto, manutenzione);
	}

	@Test
	public void testSetter() 
	{
		Manutenzione manutenzioneVuoto = new Manutenzione("","",null,0);
		LocalDate data= LocalDate.of(2018, 1, 1);
		
		manutenzioneVuoto.setCodiceIdentificativo(1);
		manutenzioneVuoto.setMatricolaAutomezzo("ax12");;
		manutenzioneVuoto.setDescrizioneIntervento("Cambio Olio");
		manutenzioneVuoto.setDataManutenzione(data);
		manutenzioneVuoto.setProssimoIntervento(10);
		
		
		assertEquals("Codice Idetificativo: ", 1, manutenzioneVuoto.getCodiceIdentificativo());
		assertEquals("Matricola: ","ax12", manutenzioneVuoto.getMatricolaAutomezzo());
		assertEquals("Intervento: ", "Cambio Olio",manutenzioneVuoto.getDescrizioneIntervento());
		assertEquals("Data: ", data, manutenzioneVuoto.getDataManutenzione());
		assertEquals("Giorni: ", 10, manutenzioneVuoto.getProssimoIntervento());
	}
	
	@Test
	public void testGetter() 
	{
		LocalDate data= LocalDate.of(2018, 1, 1);
		Manutenzione manutenzioneVuoto = new Manutenzione("ax12", "Cambio Olio", data, 10);
	
		manutenzioneVuoto.setCodiceIdentificativo(1);
		
		assertEquals("Codice Idetificativo: ", 1, manutenzioneVuoto.getCodiceIdentificativo());
		assertEquals("Matricola: ","ax12", manutenzioneVuoto.getMatricolaAutomezzo());
		assertEquals("Intervento: ", "Cambio Olio",manutenzioneVuoto.getDescrizioneIntervento());
		assertEquals("Data: ", data, manutenzioneVuoto.getDataManutenzione());
		assertEquals("Giorni: ", 10, manutenzioneVuoto.getProssimoIntervento());
	}

	@Test
	public void testToString() 
	{
		LocalDate data= LocalDate.of(2018, 1, 1);
		Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
	
		manutenzione.setCodiceIdentificativo(1);
		
		String string = "1: ax12, Cambio Olio, 2018-01-01, 10\n";
		assertEquals("ToString ", string, manutenzione.toString());	
	}

	@Test
	public void testGetProssimoInterventoData() 
	{
		LocalDate data = LocalDate.of(2018, 1, 1);
		LocalDate data2 = LocalDate.of(2018, 1, 11);
		
		Manutenzione manutenzione = new Manutenzione("ax12", "Cambio Olio", data, 10);
		
		assertEquals("Data prossimo intervento: ", data2, manutenzione.getProssimoInterventoData());	
	}

}
