package wrapper;

public class Main {
	public static void main(String[] args)  {
		Documento doc = new Documento();
		try 
		{
			doc.executa();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		
	}

}
