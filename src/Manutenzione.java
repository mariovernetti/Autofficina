import java.io.Serializable;
import java.time.LocalDate;

/**
 * La classe manutenzione rappresenta la modellizzazione informatica di una manutenzione effettuata su un automezzo. 
 * 
 * Gli attributi presenti sono:
 * -Il codice identificativo della manutenzione (incrementato in maniera progressiva)
 * -La matricola univoca dell'automezzo
 * -La descrizione dell'intevento effettuato sull'automezzo
 * -La data di effettuazione dell'intervento
 * -Il numero di giorni alla venuta del prossimo intervento
 * 
 * La classe consente di creare e gestire oggetti di tipo Manutenzione
 * 
 * @author Mario Vernetti
 * @version 1.0
 *
 */

public class Manutenzione implements Serializable
{
	//Attributi
	private int codiceIdentificativo;
	private String matricolaAutomezzo;
	private String descrizioneIntervento;
	private LocalDate dataManutenzione;
	private int prossimoIntervento;
	private static int nunmeroInterventiEseguiti = 0;

	//Costruttori
	/**
	 * Metodo costruttore per inizzializzare un oggetto Manutenzione.
	 *
	 * @param La matricola dell'automezzo.
	 * @param Descrizione dell'inervento di manutenzione.
	 * @param La data in cui si è svolta la manutenzione.
	 * @param Numero di giorni al prossimo intervento sull'automezzo.
	 */
	public Manutenzione (String matricolaAutomezzo, String descrizioneIntervento, LocalDate dataManutenzione, int prossimoIntervento)
	{
		nunmeroInterventiEseguiti++;
		setCodiceIdentificativo(nunmeroInterventiEseguiti);
		setMatricolaAutomezzo(matricolaAutomezzo);
		setDescrizioneIntervento(descrizioneIntervento);
		setDataManutenzione(dataManutenzione);
		setProssimoIntervento(prossimoIntervento);		
	}
	
	/**
	 * Metodo costruttore di copia per la classe Manutenzione.
	 * 
	 * @param Manutenzione da copiare.
	 */
	public Manutenzione(Manutenzione manutenzioneDaCopiare)
	{
		this.codiceIdentificativo = manutenzioneDaCopiare.getCodiceIdentificativo();
		setDescrizioneIntervento(manutenzioneDaCopiare.getDescrizioneIntervento());
		setDataManutenzione(manutenzioneDaCopiare.getDataManutenzione());
		setProssimoIntervento(manutenzioneDaCopiare.getProssimoIntervento());
		setMatricolaAutomezzo(manutenzioneDaCopiare.getMatricolaAutomezzo());	
	}
	
	/**
	 * Metodo costruttore per inizzializzare un oggetto Manutenzione vuoto.
	 */
	public Manutenzione()
	{
		nunmeroInterventiEseguiti++;
		setCodiceIdentificativo(nunmeroInterventiEseguiti);
		setDescrizioneIntervento("");
		setDataManutenzione(null);
		setProssimoIntervento(0);
		setMatricolaAutomezzo("");
	}
	
	//Metodi
	/**
	 * Getter per il codice indentificativo.
	 * 
	 * @return Il codice identificativo dell'autovettura.
	 */
	public int getCodiceIdentificativo() 
	{
		return codiceIdentificativo;
	}
	
	/**
	 * Setter per il codice indentificativo.
	 * 
	 * @param L'intero rappresentante il codice identificativo.
	 */
	public void setCodiceIdentificativo(int codiceIdentificativo) 
	{
		this.codiceIdentificativo = codiceIdentificativo;
	}
	
	/**
	 * Getter per la matricola dell'automezzo.
	 * 
	 * @return La matricola dell'autovettura.
	 */
	public String getMatricolaAutomezzo() 
	{
		return matricolaAutomezzo;
	}
	
	/**
	 * Setter per la matricola dell'automezzo.
	 * 
	 * @param L'intero rappresentante la matricola dell'automezzo.
	 */
	public void setMatricolaAutomezzo(String matricolaAutomezzo) 
	{
		this.matricolaAutomezzo = matricolaAutomezzo;
	}
	
	/**
	 * Getter per descrizione dell'intervento di manutenzione.
	 * 
	 * @return La descrizione dell'intervento.
	 */
	public String getDescrizioneIntervento() 
	{
		return descrizioneIntervento;
	}
	
	/**
	 * Setter per la descrizione dell'intervento.
	 * 
	 * @param La String rappresentante la descrizione dell'intervento sull'automezzo.
	 */
	public void setDescrizioneIntervento(String descrizioneIntervento) 
	{
		this.descrizioneIntervento = descrizioneIntervento;
	}
	
	/**
	 * Getter per la data di manutenzione.
	 * 
	 * @return La data della manutenzione.
	 */
	public LocalDate getDataManutenzione() 
	{
		return dataManutenzione;
	}
	
	/**
	 * Setter per la data di manutenzione.
	 * 
	 * @param La LocalDate rappresentante la data della manutenzione.
	 */
	public void setDataManutenzione(LocalDate dataManutenzione) 
	{
		this.dataManutenzione = dataManutenzione;
	}
	
	/**
	 * Getter i giorni al prossimo intervento.
	 * 
	 * @return I giorni al prossimo intervento.
	 */
	public int getProssimoIntervento() 
	{
		return prossimoIntervento;
	}
	
	/**
	 * Setter per i giorni al prossimo intervento.
	 * 
	 * @param L'int rappresentante i giorni al prossimo intervento.
	 */
	public void setProssimoIntervento(int prossimoIntervento) 
	{
		this.prossimoIntervento = prossimoIntervento;
	}
	
	/**
	 * Metodo per restituire una stringa contenente i dati della manutenzione.
	 * 
	 * @return Una String contenente tutti i dati relativi alla manutenzione.
	 */
	public String toString()
	{
		return codiceIdentificativo+": "+matricolaAutomezzo+", "+descrizioneIntervento+", "+dataManutenzione+", "+prossimoIntervento+'\n';													
	}
	
	/**
	 * Metodo per convertire e restituire una LocalDate rappresentante la data della prossima manutenzione sull'autovettura.
	 * 
	 * @return La LocalDate della prossima manutenzione
	 */
	public LocalDate getProssimoInterventoData()
	{
		LocalDate data = this.getDataManutenzione();
		data = data.plusDays(this.getProssimoIntervento());
		return data;
	}
}
