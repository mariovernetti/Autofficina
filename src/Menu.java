
import java.io.IOException;

public class Menu 
{
	//Attributi
	private int numeroVoci;
	private String[] elencoVoci;
	
	//Costruttori
	public Menu(String[] elenco)
	{
		numeroVoci = elenco.length;
		elencoVoci = new String[numeroVoci];
		
		for (int i = 0; i < numeroVoci; i++) 
		{
			elencoVoci[i] = elenco[i];
		}
	}
	
	//Metodi
	public void visualizza()
	{
		for (int i = 0; i < numeroVoci; i++) 
		{
			System.out.println(elencoVoci[i]);
		}
	}  
	
	public int scelta()
	{
		ConsoleInput tastiera = new ConsoleInput();
		int voceScelta = -1;
		do
		{
			visualizza();
			System.out.println("Scegli...");
			
			try 
			{
				voceScelta = tastiera.readInt();
				
				if(voceScelta >= numeroVoci || voceScelta < 0)
				{
					System.out.println("Opzione non prevista");
				}
			}
			
			catch (NumberFormatException e) 
			{
				System.out.println("Formato dato inserito non corretto. Rinserire");
			}
			
			catch(IOException e)
			{
				System.out.println("Impossibile leggere dal dispositivo di input. Riprovare");
			}
		}
		while(voceScelta < 0 || voceScelta >= numeroVoci);
		
		return voceScelta;
	}
}
