
public class AutofficinaException extends Exception 
{
	private String messaggio;
	
	public AutofficinaException(String messaggio)
	{
		this.messaggio = messaggio;
	}
	
	public String toString()
	{
		return messaggio;
	}
}