import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput 
{
	//Attributi
	private InputStreamReader input;
	private BufferedReader reader;
	
	//Costruttori
	public ConsoleInput ()
	{
		input = new InputStreamReader(System.in);
		reader = new BufferedReader(input);
	}
	
	//Metodi
	public int readInt () throws NumberFormatException, IOException
	{
		return(Integer.parseInt(reader.readLine()));
	}
	
	public short readShort () throws NumberFormatException, IOException
	{
		return(Short.parseShort(reader.readLine()));
	}
	
	public long readLong () throws NumberFormatException, IOException
	{
		return(Long.parseLong(reader.readLine()));
	}
	
	public float readfloat () throws NumberFormatException, IOException
	{
		return(Float.parseFloat(reader.readLine()));
	}
	
	public double readDouble () throws NumberFormatException, IOException
	{
		return(Double.parseDouble(reader.readLine()));
	}
	
	public byte readByte () throws NumberFormatException, IOException
	{
		return(Byte.parseByte(reader.readLine()));
	}
	
	public boolean readBoolean () throws NumberFormatException, IOException
	{
		return(Boolean.parseBoolean(reader.readLine()));
	}
	
	public char readChar () throws NumberFormatException, IOException
	{
		return(reader.readLine().charAt(0));
	}
	
	public String readString () throws NumberFormatException, IOException
	{
		return(reader.readLine());
	}
}


