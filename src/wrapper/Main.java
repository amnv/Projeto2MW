package wrapper;

public class Main {
	public static void main(String[] args)  {
		String arq = "texto.txt";
		OpenIE ie = new OpenIE();
		try 
		{
			ie.extractRelations(arq);
		}
		catch (Exception e)
		{
			e.getMessage();
		}	
	}
}
