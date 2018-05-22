import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * La classe Orinatore contiene un insieme di metodi utili ad ordinare i dati delle manutenzioni nell'Autofficina.
 *
 * @author Mario Vernetti
 * @version 1.0
 */

public class Ordinatore implements Serializable
{
	/**
	 * Metodo per scambiare due oggetti Manutenzione all'interno della lista.
	 * 
	 * @param La lista Autofficina
	 * @param La posizione della prima Manutenzione nella lista.
	 * @param La posizione della seconda Manutenzione nella lista.
	 * 
	 * @throws AutofficinaException - Eccezione invocata sia nel caso di inserimento di una posizione non valida,
	 *  							  sia nel caso la lista sia vuota.
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws FileException - Eccezione invocara nel caso il file di salvataggio sia irraggiungibile.
	 */
	public static void scambia (Autofficina autofficina, int pos1, int pos2) throws AutofficinaException, IOException, FileException
	{
		if(pos1 > pos2)
			{
				int s;
				s = pos1;
				pos1 = pos2;
				pos2  =s;
			}
		
		if (pos1 < 1 || pos2 < 1 || pos1>autofficina.getElementi() || pos2 > autofficina.getElementi())
			throw new AutofficinaException("Posizioni di scambio non valide");
		
		Manutenzione m1 = new Manutenzione(autofficina.getManutenzione(pos1));
		Manutenzione m2 = new Manutenzione(autofficina.getManutenzione(pos2));
		
		autofficina.registraManutenzioneInPosizione(pos2, m1);
		
		autofficina.eliminaInPosizione(pos1+1);
		autofficina.eliminaInPosizione(pos2+1);	
	}	
	
	
	/**
	 * Metodo per scambiare due oggetti Manutenzione all'interno dell'array.
	 * 
	 * @param Array composto dagli oggetti Manutenizone precedentemente presenti nell lista.
	 * @param La posizione della prima Manutenzione nella array.
	 * @param La posizione della seconda Manutenzione nella array.
	 */
	public static void scambia (Manutenzione[] array, int pos1, int pos2)
	{
		Manutenzione s;
		if (pos1<0 || pos2<0 || pos1>=array.length || pos2>=array.length)
			return;
		else
		{
			s=array[pos1];
			array[pos1]=array[pos2];
			array[pos2]=s;
		}			
	}
	
	/**
	 * Metodo per copiare la lista Autofficina sfruttando la serializzazione e deserializzazione
	 * 
	 * @param L'Autofficina da copiare
	 * 
	 * @return La copia dell'oggetto autofficina
	 * 
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws ClassNotFoundException - Eccezione invocata nel caso il file non contenga un classe Autofficina.
	 */
	private static Autofficina copiaAutofficina (Autofficina autofficina) throws IOException, ClassNotFoundException
	{
		Autofficina autofficina2 = new Autofficina();
		autofficina.salvaAutofficina("autofficinaCopia.bin");
		autofficina2 = autofficina2.caricaAutofficina("autofficinaCopia.bin");
		
		return autofficina2;
	}
	
	/**
	 * Ordina l'array di Manutenzioni in base alle date delle prossime manutenzioni, dalla più vicina alla più lontana.
	 * 
	 * @param L'Autofficina da copiare (prima da trasformare in array con il metodo convertiListaArray)
	 * 
	 * @return L'array di Manutenzioni ordinato.
	 * 
	 * @throws AutofficinaException - Eccezione invocata sia nel caso di inserimento di una posizione non valida,
	 *  							  sia nel caso la lista sia vuota.
	 * @throws ClassNotFoundException - Eccezione invocata nel caso il file non contenga un classe Autofficina.
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws FileException - Eccezione invocara nel caso il file di salvataggio sia irraggiungibile.
	 */
	public static Manutenzione[] ordinaDateProssimaManutenzionePiùVicina(Autofficina officina) throws AutofficinaException, ClassNotFoundException, IOException, FileException
	{
		Manutenzione[] array = new Manutenzione[officina.getElementi()];
		array = officina.convertiListaArray();
		boolean scambioAvvenuto;
		
		do
		{
			scambioAvvenuto = false;
			for (int i = 0; i < array.length-1; i++) 
			{
				if(array[i].getProssimoInterventoData().isAfter(array[i+1].getProssimoInterventoData()))
				{
					scambia(array, i, i+1);
					scambioAvvenuto = true;
				}
			}
		}	
		while(scambioAvvenuto == true);
		
		return array;
	}

	/**
	 * Metodo che ordina l'Autofficina in base alle date di manutenzione.
	 * 
	 * @param L'Autofficina da riordinare.
	 * 
	 * @return L'oggetto Autofficina ordinato.
	 * @throws AutofficinaException - Eccezione invocata sia nel caso di inserimento di una posizione non valida,
	 *  							  sia nel caso la lista sia vuota.
	 * @throws ClassNotFoundException - Eccezione invocata nel caso il file non contenga un classe Autofficina.
	 * @throws IOException - Eccezione invocata in caso di errori di input o output.
	 * @throws FileException - Eccezione invocara nel caso il file di salvataggio sia irraggiungibile.
	 */
	public static Autofficina OrdinaDateManutenzioniCrescenti(Autofficina autofficina) throws AutofficinaException, ClassNotFoundException, IOException, FileException
	{
		Autofficina autofficina2 = copiaAutofficina(autofficina);
		boolean scambioAvvenuto;
		
		do
		{
			scambioAvvenuto = false;
			for (int i = 1; i < autofficina2.getElementi(); i++) 
			{
				if(autofficina2.getManutenzione(i).getDataManutenzione().compareTo(autofficina2.getManutenzione(i+1).getDataManutenzione())>0)
				{
					scambia(autofficina2,i,i+1);
					scambioAvvenuto = true;
				}
			}
		}
		while(scambioAvvenuto == true);
		
		return autofficina2;
	}
}