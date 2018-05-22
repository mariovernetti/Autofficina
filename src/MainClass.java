import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class MainClass {

	public static void main(String[] args) 
	{
		String [] elenco= 
			{
				"1 --> Registra una nuova manutenzione e stampa il rapporto di revisione.",
				"2 --> Elimina un automezzo e tutte le manutenzioni relative.",
				"3 --> Visualizza tutte le manutenzioni effettuate (ordinate dall più remota alla più recente).",
				"4 --> Visualizza prossimi interventi di manutenzione.",
				"5 --> Visualizza tutte le manutenzioni effettuate su un automezzo.",
				"6 --> Visualizza le prossime manutenzioni programmate per un automezzo.",
				"7 --> Visualizza manutenzioni da svolgere in un determinato giorno.",
				"0 --> Esci e Salva"
			};
		
		ConsoleInput tastiera = new ConsoleInput();
		Menu menu= new Menu(elenco);
		Autofficina autofficina = new Autofficina();
		
		String nomefile= "autofficina.bin";
			
		int continuare=1;
		
		try 
		{
			autofficina= autofficina.caricaAutofficina(nomefile);
		}
		
		catch (ClassNotFoundException e) 
		{
			System.out.println("Nessun file di tipo Autofficina caricabile");
		}
		
		catch (IOException e) 
		{
			System.out.println("File irraggiungibile,impossibile accedervi");
		}
		System.out.println("Recupero dati riuscito");
		
		try 
		{
			autofficina.salvaAutofficina(nomefile);
		}
		
		catch (IOException e2) 
		{
			System.out.println("Errore nella procedura di salvataggio");
		}
		
		
		do
		{
			switch (menu.scelta()) 
			{
			
				case 1:
				{
			
						Manutenzione manutenzione = new Manutenzione();
						LocalDate data = LocalDate.of(1, 1, 1);
			
						try 
						{
							System.out.println("Inserisci matricola automezzo: ");
							manutenzione.setMatricolaAutomezzo(tastiera.readString());
				
							System.out.println("Inserisci la descrizione dell'intervento: ");
							manutenzione.setDescrizioneIntervento(tastiera.readString());
				
							System.out.println("Inserisci l'anno in cui è stata svolta la manutenzione (numero): ");
							data=data.plusYears(tastiera.readInt()-1);
				
							System.out.println("Inserisci il mese (numero): ");
							data=data.plusMonths(tastiera.readInt()-1);
				
							System.out.println("Inserisci il giorno (numero): ");
							data=data.plusDays(tastiera.readInt()-1);
							manutenzione.setDataManutenzione(data);
				
							System.out.println("Inserisci il numero di giorni per effetture una nuova manutenzione di questo tipo, su questa macchina: ");
							manutenzione.setProssimoIntervento(tastiera.readInt());
				
							autofficina.registraManutenzioneInTesta(manutenzione);
							autofficina.stampaRapporto("Rapporto-"+manutenzione.getCodiceIdentificativo()+"-"+manutenzione.getMatricolaAutomezzo()+".txt", manutenzione);
							autofficina.salvaAutofficina(nomefile);
						} 
			
						catch (NumberFormatException e) 
						{
							System.out.println("Il dato inserito non è del tipo corretto, registrazione manutenzione fallita");
							break;
						}
			
						catch (IOException e) 
						{
							System.out.println("Errore nella procedura di salvataggio");
							break;
						} 
			
						catch (AutofficinaException e) 
						{
							System.out.println(e.toString());
							break;
						} 
			
						catch (FileException e) 
						{
							System.out.println(e.toString());
							break;
						}
						
						break;
				}
		
				case 2:
				{
			
					System.out.println("Il processo eliminerà tutte le manutenzioni con la data matricola."+'\n'+"Inserisci la matricola: ");
					
					try 
					{
						autofficina.eliminaManutenzione(tastiera.readString());
					}
			
					catch (NumberFormatException e) 
					{
						System.out.println("Il dato inserito non è del tipo corretto, registrazione manutenzione fallita");
						break;
					} 
			
					catch (AutofficinaException e) 
					{
						System.out.println(e.toString());
						break;
					} 
			
					catch (IOException e) 
					{
						System.out.println("Errore di lettura o scrittura");
						break;
					}
			
					try 
					{
						autofficina.salvaAutofficina(nomefile);
					} 
			
					catch (IOException e) 
					{
						System.out.println("Errore nella procedura di salvataggio");
						break;
					}
			
					System.out.println("Salvataggio avvenuto con successo");
					break;
				}
		
				case 3:
				{	
					try 
					{
						System.out.println(autofficina.visualizzaTutteManutenzioni());
					} 
			
					catch (ClassNotFoundException e) 
					{
						System.out.println("Nessun file di tipo Autofficina caricabile");
						break;
					} 
			
					catch (AutofficinaException e) 
					{
						System.out.println(e.toString());
						break;
					} 
			
					catch (IOException e) 
					{
						System.out.println("Errore di lettura o scrittura");
						break;
					} 
			
					catch (FileException e) 
					{
						System.out.println(e.toString());
						break;
					}
					break;
				}
		
				case 4:
				{
			
					try 
					{
						System.out.println(autofficina.visualizzaProssimeManutenzioniProgrammate());
					} 
			
					catch (ClassNotFoundException e) 
					{
						System.out.println("Nessun file di tipo Autofficina caricabile");
						break;
					} 
			
					catch (AutofficinaException e) 
					{
						System.out.println(e.toString());
						break;
					} 
			
					catch (IOException e) 
					{
						System.out.println("Errore di lettura o scrittura");
						break;
					} 
			
					catch (FileException e) 
					{
						System.out.println(e.toString());
						break;
					}
					break;
				}
		
				case 5:
				{
					System.out.println("Inserisci la matricola dell'autovettura: ");
			
					try 
					{
						System.out.println(autofficina.visualizzaManutenzioniMacchina(tastiera.readString()));
					} 
			
					catch (NumberFormatException e) 
					{
						System.out.println("Il dato inserito non è del tipo corretto, impossibile visualizzare.");
						break;
					} 
			
					catch (AutofficinaException e) 
					{
						System.out.println(e.toString());
						break;
					} 
			
					catch (IOException e) 
					{
						System.out.println("Errore di lettura o scrittura");
						break;
					}
					break;
				}
		
				case 6:
				{
					System.out.println("Inserisci la matricola dell'autovettura: ");
			
					try 
					{
						System.out.println(autofficina.visualizzaProssimeManutenzioni(tastiera.readString()));
					} 
					
					catch (NumberFormatException e) 
					{
						System.out.println("Formato dato inserito non corretto, impossibile avviare la ricerca");
						break;
					} 
			
					catch (AutofficinaException e) 
					{
						System.out.println(e.toString());
						break;
					} 
			
					catch (IOException e) 
					{
						System.out.println("Errore di lettura o scrittura");
						break;
					}
					break;
				}
		
				case 7:
				{
					LocalDate data= LocalDate.of(1, 1, 1);
			
					try 
					{
						System.out.println("Inserisci l'anno (numero): ");
						data=data.plusYears(tastiera.readInt()-1);
						
						System.out.println("Inserisci il mese (numero): ");
						data=data.plusMonths(tastiera.readInt()-1);
				
						System.out.println("Inserisci il giorno (numero): ");
						data=data.plusDays(tastiera.readInt()-1);		
					} 
			
					catch (NumberFormatException e) 
					{
						System.out.println("Il dato inserito non è del tipo corretto, impossibile visualizzare.");
						break;
					} 
			
					catch (IOException e) 
					{
						System.out.println("Errore di lettura o scrittura");
						break;
					}
		
					try 
					{
						System.out.println(autofficina.visualizzaManutenzioniGiorno(data));
					}	 
			
					catch (AutofficinaException e) 
					{
						System.out.println(e.toString());
					}
					break;
				}
		
			}
		
			System.out.println("Vuoi continuare a usare il programma? (1--> si) (altro-->no)");
		
			try 
			{
				continuare = tastiera.readInt();
			}	 
		
			catch (NumberFormatException e) 
			{
				System.out.println("Formato dato inserito non corretto, il programma verrà interrotto.");
				break;
			} 
		
			catch (IOException e) 
			{
				System.out.println("Errore di lettura o scrittura");
				break;
			}
		
			try 
			{
				autofficina.salvaAutofficina(nomefile);
			} 
		
			catch (IOException e) 
			{
				System.out.println("Errore nella procedura di salvataggio");
				break;
			}
		
			System.out.println("Salvataggio avvenuto con successo");
		}
		while(continuare == 1);
	}
}