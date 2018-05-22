import java.io.Serializable;

/**
 * La classe Nodo rappresenta il componente della lista. 
 * 
 * Gli attributi presenti sono:
 * -Il reference all'aggetto presente nel nodo.
 * -Il reference al prossimo nodo della lista.
 * 
 * La classe consente di creare e gestire oggetti di tipo Nodo.
 * 
 * @author Mario Vernetti
 * @version 1.0
 *
 */

public class Nodo implements Serializable
{
	//Attributi
	private Manutenzione info;
	private Nodo link;
	
	//Costruttore
	/**
	 * Metodo costruttore per inizzializzare un oggetto Manutenzione.
	 *
	 * @param La Manutenzione che verrà contenuta nel nodo.
	 */
	public Nodo (Manutenzione manutenzione)
	{
		setInfo(manutenzione);
		setLink(null);	
	}
	
	//Metodi
	/**
	 * Getter per il link al prossimo Nodo.
	 * 
	 * @return Il reference alla link del prossimo Nodo.
	 */
	public Nodo getLink() 
	{
		return link;
	}
	
	/**
	 * Setter per il link al prossimo Nodo.
	 * 
	 * @param Il reference alla link del prossimo Nodo.
	 */
	public void setLink(Nodo link) 
	{
		this.link = link;
	}
	
	/**
	 * Getter per il reference alla Manutenzione.
	 * 
	 * @return Il reference alla Manutenzione.
	 */
	public Manutenzione getInfo() 
	{
		return info;
	}
	
	/**
	 * Setter per il reference alla Manutenzione.
	 * 
	 * @param Il reference alla Manutenzione.
	 */
	public void setInfo(Manutenzione info) 
	{
		this.info = info;
	}
	
	
}