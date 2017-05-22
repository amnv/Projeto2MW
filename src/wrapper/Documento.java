package wrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
		BufferedReader br = new BufferedReader(new FileReader("equipe4.txt"));

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
				criarCSV();
			}
		}
		
	}
	
	public void criarCSV() throws IOException
	{
		FileWriter fr = new FileWriter(new File("wrapper.csv"), true);
		StringBuilder sb =  new StringBuilder();
		sb.append("Author: ");
		sb.append(";");
		sb.append(this.autor);
		sb.append("\n");
		sb.append("Title: ");
		sb.append(";");
		sb.append(this.title);
		sb.append("\n");
		sb.append("Subjects: ");
		sb.append(";");
		sb.append(this.subjects);
		sb.append("\n");
		sb.append("Link: ");
		sb.append(";");
		sb.append(this.link);
		sb.append("\n\n\n\n\n");
		
		fr.write(sb.toString());
		fr.close();
	}
	
}
