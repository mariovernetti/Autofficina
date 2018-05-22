import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * La classe manutenzione rappresenta la modellizzazione informatica di un'autofficina che effettua manutenzioni su automezzi. 
 * 
 * La classe è una lista di Manutenzione.
 *  
 * La classe consente di creare e gestire oggetti di tipo Autofficina
 * 
 * @author Mario Vernetti
 * @version 1.0
 *
 */

public class Autofficina implements Serializable
{
	//Attributi
	private Nodo head;
	private int elementi;
	
	String nomefile = "autofficina.bin";
	
	//Costruttori	
	/**
	 * Metodo costruttore per inizzializzare un oggetto Autofficina vuoto.
	 */
	public Autofficina()
	{
		head = null;
		elementi = 0;
	}
	
	/**
	 * Metodo costruttore di copia per la classe Autofficina
	 * 
	 * @param Oggetto autofficina da copiare.
	 */
	public Autofficina(Autofficina x)
	{
		this.head = x.head;
		this.elementi = x.elementi;
	}
	
	
	
	//Metodi
	/**
	 * Return dell'attributo elementi.
	 * 
	 * @return Numero elementi di cui è composta la lista Autofficina
	 */
	public int getElementi() 
	{
		return elementi;
	}
	
	/**
	 * Metodo per la creazone di un oggetto di tipo Nodo
	 * 
	 * @param La manutenzione che sarà contenuta nel nodo
	 * @param Link di collegamenti di questo nodo nella lista
	 * 
	 * @return Il Nodo creato dal metodo.
	 */
	private Nodo creaNodo(Manutenzione manutenzione, Nodo link)
	{
		Nodo nodo = new Nodo(manutenzione);
		nodo.setLink(link);
		return nodo;
	}
	
	/**
	 * Metodo per ottenere il nodo presente in una determinata posizione.
	 * 
	 * @param Posizione della quale restituire il nodo.
	 * 
	 * @return Il nodo nella posizione data
	 *
	 * @throws AutofficinaException - Eccezione invocata sia nel caso di inserimento di una posizione non valida,
	 *  							  sia nel caso la lista sia vuota.
	 */
	private Nodo getLinkPosizione(int posizione) throws AutofficinaException
	{
		Nodo nodo;
		int x = 1;
		
		nodo = head;

		if(posizione < 1 || posizione>  elementi)
			throw new AutofficinaException("Posizione non valida");
		
		if(elementi==0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		while(nodo.getLink() != null && x < posizione)
		{
			nodo = nodo.getLink();	
			x++;
		}
		
		return nodo;
	}
	
	/**
	 * Metodo per creare un nuovo nodo contenente un Manutenzione alla testa della lista Autofficina.
	 *
	 * @param La manutenzione da inserire nel nodo in testa alla lista.
	 * 
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws FileException - Eccezione invocara nel caso il file di salvataggio sia irraggiungibile.
	 */
	public void registraManutenzioneInTesta(Manutenzione manutenzione) throws IOException, FileException
	{
		Nodo nodo = creaNodo(manutenzione, head); 
		head = nodo; 
		elementi++;
	}
	
	/**
	 * Metodo per creare un nuovo nodo contenente un Manutenzione alla coda della lista Autofficina.
	 * 
	 * @param La manutenzione da inserire nel nodo in coda alla lista.
	 * 
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws AutofficinaException - Eccezione invocata sia nel caso di inserimento di una posizione non valida.
	 * @throws FileException - Eccezione invocara nel caso il file di salvataggio sia irraggiungibile.
	 */
	public void registraManutenzioneInCoda(Manutenzione manutenzione) throws IOException, FileException, AutofficinaException
	{
		if(elementi == 0)
		{
			registraManutenzioneInTesta(manutenzione);
			return;
		}
		Nodo nodoProvvisorio = creaNodo(manutenzione, null);
		Nodo nodo = getLinkPosizione(elementi);
		nodo.setLink(nodoProvvisorio);
		
		elementi++;
	}
	
	/**
	 * Metodo per creare un nodo contenente un Manutenzione in una data posizione della lista Autofficina.
	 * 
	 * @param La manutenzione da inserire nel nodo in coda alla lista.
	 * @param La posizione in cui inserire il nodo della manutanzione.
	 * 
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws AutofficinaException - Eccezione invocata sia nel caso di inserimento di una posizione non valida.
	 * @throws FileException - Eccezione invocara nel caso il file di salvataggio sia irraggiungibile.
	 */
	public void registraManutenzioneInPosizione(int posizione,Manutenzione manutenzione) throws AutofficinaException, IOException, FileException
	{
		if(posizione <= 1)
		{
			registraManutenzioneInTesta(manutenzione);
			return;
		}
		
		if(posizione>elementi)
		{
			registraManutenzioneInCoda(manutenzione);
			return;
		}
		
		Nodo nodo = creaNodo(manutenzione, getLinkPosizione(posizione));
		Nodo nodoPrecedente = getLinkPosizione(posizione-1);
		nodoPrecedente.setLink(nodo);
		elementi++;
	}
	
	/**
	 * Metodo per eliminare un nuovo nodo contenente un Manutenzione alla testa della lista Autofficina.
	 * 
	 * @throws AutofficinaException - Eccezione invocata nel caso la lista sia vuota.
	 */
	public void eliminaInTesta() throws AutofficinaException
	{
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		head = head.getLink();
		elementi--;
	}
	
	/**
	 * Metodo per eliminare un nodo contenente un Manutenzione alla coda della lista Autofficina.
	 * 
	 * @throws AutofficinaException - Eccezione invocata nel caso la lista sia vuota.
	 */
	public void eliminaInCoda() throws AutofficinaException
	{
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		if(elementi == 1)
		{
			eliminaInTesta();
			return;
		}
		
		Nodo nodo = getLinkPosizione(elementi-1);
		nodo.setLink(null);
		elementi--;
	}
	
	/**
	 * Metodo per eliminare un nodo contenente un Manutenzione in una data posizione della lista Autofficina.
	 * 
	 * @throws AutofficinaException - Eccezione invocata sia nel caso di inserimento di una posizione non valida,
	 *  							  sia nel caso la lista sia vuota.
	 */
	public void eliminaInPosizione(int posizione) throws AutofficinaException
	{
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		if(posizione < 0 || posizione > elementi)
			throw new AutofficinaException("Posizione non valida");
		
		if(posizione == 1)
		{
			eliminaInTesta();
			return;
		}
		
		if(posizione == elementi)
		{
			eliminaInCoda();
			return;
		}
		
		Nodo nodo = getLinkPosizione(posizione);
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(nodo.getLink());
		elementi--;
	}
	
	/**
	 * Metodo per l'eliminazione di tutte le manutenzioni con una data matricola presenti nella lista.
	 * 
	 * @param La matricola della quale eliminare le manutenzioni.
	 *  
	 *@throws AutofficinaException - Eccezione invocata nel caso la lista sia vuota.
	 */
	public void eliminaManutenzione(String matricola) throws AutofficinaException
	{
		int elementiPrimaDiEliminazione = elementi;
		
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		for (int i = 1; i <= elementi; i++) 
		{
			boolean avvenutaEliminazione = false;
			if((i == 1) && (getLinkPosizione(i).getInfo().getMatricolaAutomezzo().compareTo(matricola) == 0))
			{
				eliminaInTesta();
				avvenutaEliminazione = true;	
			}
				
			if((i==elementi)&&(getLinkPosizione(i).getInfo().getMatricolaAutomezzo().compareTo(matricola) == 0))
			{
				eliminaInCoda();
				avvenutaEliminazione = true;
				return;
			}
			
			if(avvenutaEliminazione == false)
			{
				if(getLinkPosizione(i).getInfo().getMatricolaAutomezzo().compareTo(matricola) == 0)
				{
					Nodo nodo = getLinkPosizione(i);
					Nodo nodoPrecedente = getLinkPosizione(i-1);
					nodoPrecedente.setLink(nodo.getLink());
					elementi--;
				}
			}
			
			if(elementi > 0 && getLinkPosizione(i).getInfo().getMatricolaAutomezzo().compareTo(matricola) == 0)
				i = 0;
		}
	
		if(elementiPrimaDiEliminazione == getElementi())
			System.out.println("Matricola automezzo non trovata, eliminazione fallita.");			
	}
	
	/**
	 * Metodo per ottenere la Manutenzione presenti in una data posizione della lista.
	 * 
	 * @param La posizione della quale restituire la Manutenzione presente.
	 * 
	 * @return La Manutenzione presente nella data posizione
	 * 
	 * @throws AutofficinaException - Eccezione invocata sia nel caso di inserimento di una posizione non valida,
	 *  							  sia nel caso la lista sia vuota.
	 */
	public Manutenzione getManutenzione(int posizione)throws AutofficinaException
	{
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		if(posizione < 0 || posizione > elementi)
			throw new AutofficinaException("Posizione non valida");
		
		Nodo nodo = getLinkPosizione(posizione);
		return nodo.getInfo();
	}
	
	/**
	 * Metodo per la simulazione di una stampa.
	 * Consente di salvare i dati di una Manutenzione in un file di testo.
	 * 
	 * @param Nome del file di testo sul quale salvare i dati della Manutenzione
	 * @param La manutenzione che si vuole stampare.
	 * 
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws @throws AutofficinaException - Eccezione invocata sia nel caso di inserimento di una posizione non valida,
	 *  							  sia nel caso la lista sia vuota.
	 * @throws FileException - Eccezione invocara nel caso il file di salvataggio sia irraggiungibile
	 */
	public void stampaRapporto(String nomeFile, Manutenzione manutenzione)throws IOException, FileException, AutofficinaException
	{
		
			TextFile file = new TextFile (nomeFile,'W');
			String rapporto;
			
			
			rapporto = 		"Id manutenzione: "+ manutenzione.getCodiceIdentificativo()+'\r'+'\n'+
							"Matricola automezzo: "+manutenzione.getMatricolaAutomezzo()+'\r'+'\n'+
							"Tipo di intervento: "+manutenzione.getDescrizioneIntervento()+'\r'+'\n'+
							"Data in cui è stato svolto l'intervento: "+manutenzione.getDataManutenzione()+'\r'+'\n'+
							"Data prossima manutenzione: "+manutenzione.getProssimoIntervento();
			
			file.toFile(rapporto);
			
			file.closeFile();
			
	}
	
	/**
	 * Metodo per calcolare la prossima data di manutenzione partendo dai giorni che dovranno trascorrere tra una manutenzione e l'altra.
	 * 
	 * @param La Manutenzione di cui calcolare la data del prossimo intervento.
	 * 
	 * @return La data del prossimo intervento.
	 */
	public LocalDate prossimaManutenzione(Manutenzione manutenzione)
	{
		LocalDate data = manutenzione.getDataManutenzione();
		data = data.plusDays(manutenzione.getProssimoIntervento());
		return data;
	}
	
	/**
	 * Metodo per serializzare la lista su un file binario.
	 * 
	 * @param Nome del file binario dove serializzare la lista.
	 * 
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 */
	public void salvaAutofficina(String nomeFile) throws IOException
	{
		FileOutputStream file = new FileOutputStream(nomeFile);
		ObjectOutputStream writer = new ObjectOutputStream(file);
		
		writer.writeObject(this);
		writer.flush();
		writer.close();
	}
	
	/**
	 * Metodo per deserializzare una lista dal un file binario.
	 * 
	 * @param Nome del file binario dove serializzare la lista.
	 * 
	 * @return L'oggetto lista Autofficina deserializzto dal file binario.
	 * 
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws ClassNotFoundException - Eccezione invocata nel caso il file con contenga un classe Autofficina
	 */
	public Autofficina caricaAutofficina(String nomeFile) throws IOException, ClassNotFoundException
	{
		FileInputStream file = new FileInputStream(nomeFile);
		ObjectInputStream reader = new ObjectInputStream(file);
		
		Autofficina autoOfficina;
		autoOfficina = (Autofficina)reader.readObject();
		file.close();
		return autoOfficina;
	}
	
	/**
	 * Metodo per visualizzare tutte le manutanzioni effettuate su una data Autovettura,
	 * 
	 * @param La matricola dell'automezzo del quale comunicare le manutenzioni.
	 * 
	 * @return Le manutenzioni avvenute sulla data autovettura raccole in una String
	 * 
	 * @throws AutofficinaException - Eccezione invocata nel caso la lista sia vuota
	 */
	public String visualizzaManutenzioniMacchina(String matricola) throws AutofficinaException
	{
		String risultato = "";
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		System.out.println("Manutenzioni svolte sull'automezzo di matricola "+matricola);
		
		for (int i = 1; i <= elementi; i++) {
			if(getManutenzione(i).getMatricolaAutomezzo().compareTo(matricola) == 0)
			{
				if(getManutenzione(i).getDataManutenzione().isBefore(LocalDate.now()))	
				{
					risultato = risultato+getManutenzione(i).getDataManutenzione()+" --> "+getManutenzione(i).getDescrizioneIntervento()+'\n';
				}
				
				else if(getManutenzione(i).getDataManutenzione().isEqual(LocalDate.now()))	
				{
					risultato = risultato+getManutenzione(i).getDataManutenzione()+" --> "+getManutenzione(i).getDescrizioneIntervento()+'\n';
				}
				
				else if(prossimaManutenzione(getManutenzione(i)).isBefore(LocalDate.now() )&& !prossimaManutenzione(getManutenzione(i)).isEqual(getManutenzione(i).getDataManutenzione()))
				{
					risultato=risultato+prossimaManutenzione(getManutenzione(i))+" --> "+getManutenzione(i).getDescrizioneIntervento()+'\n';
				}
			}
		}
		
		if(risultato == "")
			System.out.println("Nessuna manutenzione registrata per l'automezzo di matricola "+matricola);
		
		return risultato;
	}
	
	/**
	 * Metodo per visualizzare le manutenzioni programmate su una data autovettura.
	 * 
	 * @param La matricola dell'automezzo del quale comunicare le manutenzioni.
	 * 
	 * @return Le manutenzioni programate sulla data autovettura raccolte in una String
	 * @throws AutofficinaException - Eccezione invocata nel caso la lista sia vuota
	 */
	public String visualizzaProssimeManutenzioni(String matricola) throws AutofficinaException
	{
		String risultato = "";
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		System.out.println("Manutenzioni future per l'automezz di matricola "+matricola);
	
		for (int i = 1; i <= elementi; i++) 
		{
			if(getManutenzione(i).getMatricolaAutomezzo().compareTo(matricola) == 0)
			{
				if(prossimaManutenzione(getManutenzione(i)).isAfter(LocalDate.now()))
				{
					risultato = risultato+getManutenzione(i).getMatricolaAutomezzo()+" intervento: "+ getManutenzione(i).getDescrizioneIntervento()+ " in data: "+prossimaManutenzione(getManutenzione(i))+'\n';
				}
			}
		}
		if(risultato == "")
			System.out.println("Nessuna manutenzione futura per l'automezzo di matricola "+matricola);
		
		return risultato;
	}
	
	/**
	 * Metodo per visualizzare tutte le manutanzioni effettuate in una data.
	 * 
	 * @param La data della quale comunicare le manutenzioni.
	 * 
	 * @return Le manutenzioni programate il dato giorno raccolte in una String
	 * 
	 * @throws AutofficinaException - Eccezione invocata nel caso la lista sia vuota
	 */
	public String visualizzaManutenzioniGiorno(LocalDate data) throws AutofficinaException
	{
		String risultato = "";
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		System.out.println("Manutenzioni da svolgere in data: "+data);
		
		for (int i = 1; i <= elementi; i++) 
		{
			if(getManutenzione(i).getDataManutenzione().isEqual(data))
				risultato=risultato+getManutenzione(i).getMatricolaAutomezzo()+" --> "+getManutenzione(i).getDescrizioneIntervento()+'\n';
		
			if(prossimaManutenzione(getManutenzione(i)).isEqual(data))
			{
				risultato=risultato+getManutenzione(i).getMatricolaAutomezzo()+" --> "+getManutenzione(i).getDescrizioneIntervento()+'\n';
			}
		}
		
		if(risultato == "")
			System.out.println("Nessuna manutenzione programmata per il giorno "+data);
		
		return risultato;
	}
	
	/**
	 * Metodo per visualizzare tutte le manutanzioni effettuate ordinate in base alla data del prossimo intervento.
	 * 
	 * @return Le manutenzioni avvenute sulla data autovettura raccole in una String e ordinate in base alla data del prossimo intervento.
	 * 
	 * @throws ClassNotFoundException - Eccezione invocata nel caso il file non contenga un classe Autofficina.
	 * @throws AutofficinaException - Eccezione invocata nel caso la lista sia vuota.
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws FileException - Eccezione invocara nel caso il file di salvataggio sia irraggiungibile.
	 */
	public String visualizzaProssimeManutenzioniProgrammate() throws ClassNotFoundException, AutofficinaException, IOException, FileException
	{
		String risultato = "";
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		Manutenzione[] array = Ordinatore.ordinaDateProssimaManutenzionePiùVicina(this);
		
		for (int i = 0; i < array.length; i++) 
		{
			if(array[i].getDataManutenzione().isBefore(LocalDate.now()) && array[i].getProssimoInterventoData().isAfter(LocalDate.now()))
				risultato=risultato+array[i].toString()+" Data prossima manutenzione: "+array[i].getProssimoInterventoData()+'\n';
			else if (array[i].getDataManutenzione().isEqual(LocalDate.now()) && array[i].getProssimoInterventoData().isAfter(LocalDate.now()))
				risultato=risultato+array[i].toString()+" Data prossima manutenzione: "+array[i].getProssimoInterventoData()+'\n';
		}
		
		return risultato;
	}
	
	/**
	 * Metodo per visulizzare tutti le manutenzioni nella lista ordinate dalla più vecchia alla più recente.
	 * 
	 * @return Le manutenzioni avvenute sulla data autovettura raccolte in una String e ordinate in base alla data.
	 * 
	 * @throws ClassNotFoundException - Eccezione invocata nel caso il file non contenga un classe Autofficina.
	 * @throws AutofficinaException - Eccezione invocata nel caso la lista sia vuota.
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws FileException - Eccezione invocara nel caso il file di salvataggio sia irraggiungibile.
	 */
	public String visualizzaTutteManutenzioni() throws ClassNotFoundException, AutofficinaException, IOException, FileException
	{
		String risultato = "";
		if(elementi == 0)
			throw new AutofficinaException("L'autofficina non ha manutenzioni registrate.");
		
		risultato = risultato+Ordinatore.OrdinaDateManutenzioniCrescenti(this).toString();
		
		return risultato;
	}
	
	/**
	 * Metodo per convertire in array la lista Autofficina.
	 * 
	 * @return L'array di Manutenzioni.
	 * 
	 * @throws AutofficinaException - Eccezione invocata nel caso la lista sia vuota.
	 */
	public Manutenzione[] convertiListaArray() throws AutofficinaException
	{
		Manutenzione[] copia = new Manutenzione[elementi];
		
		for (int i = 0; i < copia.length; i++)
		{
			copia[i]=getManutenzione(i+1);	
		}
		
		return copia;
	}
	
	/**
	 * Metodo per restituire una String contenente tutte le Manutenzioni dell'Autofficina
	 * 
	 * @return La String con tutte le Manutenzioni dell'
	 */
	public String toString()
	{
		String risultato = "";
		Nodo nodo = head;
		while(nodo != null)
		{
			risultato += nodo.getInfo().toString()+"\n";
			nodo = nodo.getLink(); 
		}
		return risultato;
	}
}
