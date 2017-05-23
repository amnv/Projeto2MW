package wrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Documento {

	private String linha;
	private String doc;
	private String comments;
	private String link;
	private String subjects;
	private String autor;
	private String title;
	private String journalRef;

	public Documento() {
		this.linha = null;
		this.doc = null;
		this.comments = null;
		this.link = null;
		this.subjects = null;
		this.autor = null;
		this.title = null;
		this.journalRef = null;
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
					//this.link = linhas[i++].substring(5,22).trim();
				this.link = linhas[i++].split(" ")[2];
					System.out.println(link);
					isLink = !isLink;
					this.title = linhas[i++].substring(0).trim();
					this.autor = linhas[i++].substring(0).trim();
				
					System.out.println(title);
					System.out.println(autor);
					isLink = !isLink;
			}
			
			if (linhas[i].startsWith("Comments:")) {
				comments = linhas[i].substring(10).trim();
				System.out.println("Comments: "+comments);
			}

            if (linhas[i].startsWith("Journal-ref:")) {
                journalRef = linhas[i].substring(10).trim();
                System.out.println("Journal-ref: "+journalRef);
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
		FileWriter fr = new FileWriter(new File("template.csv"), true);
		StringBuilder sb =  new StringBuilder();
		sb.append("Authors: ");
		sb.append(this.autor);
		sb.append(";");
		sb.append("\n");
		sb.append("Title: ");
		sb.append(this.title);
		sb.append(";");
		sb.append("\n");
		sb.append("Subjects: ");
		sb.append(this.subjects);
		sb.append(";");
		sb.append("\n");
		sb.append("Comments: ");
		if(comments == null){
			sb.append("NULL");
		} else {
			sb.append(this.comments);
		}
		sb.append(";");
		sb.append("\n");
		sb.append("Journal-ref: ");
		if(journalRef == null){
			sb.append("NULL");
		} else {
			sb.append(this.journalRef);
		}
		sb.append(";");
		sb.append("\n");
		sb.append("Link: ");
		sb.append(this.link);
		sb.append(";");
		sb.append("\n\n\n\n\n");
		
		fr.write(sb.toString());
		fr.close();
	}
	
}
