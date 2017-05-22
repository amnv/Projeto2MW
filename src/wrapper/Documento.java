package wrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Documento {

	private String linha;
	private String doc;
	private String coments;
	private String link;
	private String subjects;
	private String autor;
	private String title;

	public Documento() {
		this.linha = null;
		this.doc = null;
		this.coments = null;
		this.link = null;
		this.subjects = null;
		this.autor = null;
		this.title = null;
	}

	private void loadFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src\\equipe4.txt"));

		while (br.ready()) {
			linha = br.readLine();
			doc = doc + "\n" + linha;
		}

		br.close();
	}
	
	public void executa() throws IOException
	{
		loadFile();
		boolean isLink = false;
		String linhas[] = doc.split("\n\r|\n|\r");
		
		for (int i = 0; i < linhas.length; i++) {
			
			if (linhas[i].startsWith("[") || isLink == true) {			
					this.link = linhas[i++].substring(0).trim();
					System.out.println(link);
					isLink = !isLink;
					this.title = linhas[i++].substring(0).trim();
					this.autor = linhas[i++].substring(0).trim();
				
					System.out.println(title);
					System.out.println(autor);
					isLink = !isLink;
			}
			
			if (linhas[i].startsWith("Comments:")) {
				coments = linhas[i].substring(10).trim();
				System.out.println("Comentários: "+coments);
			}
			
			if (linhas[i].startsWith("Subjects:")) {
				subjects = linhas[i].substring(10).trim();
				System.out.println("Subjects: " +subjects);
			}
		}
	
	}
	
	public void criarCSV()
	{
		
	}
	
}
