package wrapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class OpenIE {
	
	private Document document;
	
	public OpenIE() {
		this.document = new Document();
	}

	public void extractRelations(String fileName) throws IOException {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		String text = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			text = sb.toString();
		} finally {
			br.close();
		}

		// Annotate an example document.
		Annotation doc = new Annotation(text);
		pipeline.annotate(doc);

		this.writeTXT(doc);

	}
	
	public void extractFields(String fileName) throws IOException
	{
		this.loadFile(fileName);
		boolean isLink = false;
		String linhas[] = this.document.getDoc().split("\n\r|\n|\r");
		
		for (int i = 0; i < linhas.length; i++) {
			
			if (linhas[i].startsWith("[") || isLink == true) {			
					//this.link = linhas[i++].substring(5,22).trim();
				this.document.setLink(linhas[i++].split(" ")[2]);
					isLink = !isLink;
					this.document.setTitle(linhas[i++].substring(0).trim());
					this.document.setAutor(linhas[i++].substring(0).trim());
					isLink = !isLink;
			}
			
			if (linhas[i].startsWith("Comments:")) {
				this.document.setComments(linhas[i].substring(10).trim());
			}

            if (linhas[i].startsWith("Journal-ref:")) {
            	this.document.setJournalRef(linhas[i].substring(10).trim());
            }

			
			if (linhas[i].startsWith("Subjects:")) {
				this.document.setSubjects(linhas[i].substring(10).trim());
				this.writeCSV();
			}
		}
		
	}
	
	private void loadFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		while (br.ready()) {
			this.document.setLinha(br.readLine());
			this.document.setDoc(this.document.getDoc() + "\n" + this.document.getLinha());
		}

		br.close();
	}
	
	public void writeCSV() throws IOException
	{
		FileWriter fr = new FileWriter(new File("template.csv"), true);
		StringBuilder sb =  new StringBuilder();
		sb.append("Authors: ");
		sb.append(this.document.getAutor());
		sb.append(";");
		sb.append("\n");
		sb.append("Title: ");
		sb.append(this.document.getTitle());
		sb.append(";");
		sb.append("\n");
		sb.append("Subjects: ");
		sb.append(this.document.getSubjects());
		sb.append(";");
		sb.append("\n");
		sb.append("Comments: ");
		if(this.document.getComments() == null){
			sb.append("NULL");
		} else {
			sb.append(this.document.getComments());
		}
		sb.append(";");
		sb.append("\n");
		sb.append("Journal-ref: ");
		if(this.document.getJournalRef() == null){
			sb.append("NULL");
		} else {
			sb.append(this.document.getJournalRef());
		}
		sb.append(";");
		sb.append("\n");
		sb.append("Link: ");
		sb.append(this.document.getLink());
		sb.append(";");
		sb.append("\n\n\n\n\n");
		
		fr.write(sb.toString());
		fr.close();
	}

	private void writeTXT(Annotation doc) {
		StringBuilder sb = new StringBuilder();
		
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			// Loop over sentences in the document
			for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
				// Get the OpenIE triples for the sentence
				Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
				// Print the triples
				for (RelationTriple triple : triples) {
					sb.append(triple.confidence + "\t" + triple.subjectLemmaGloss() + "\t"
							+ triple.relationLemmaGloss() + "\t" + triple.objectLemmaGloss());
					sb.append("\n");
				}
			}

			fw = new FileWriter("saida.txt");
			bw = new BufferedWriter(fw);
			bw.write(sb.toString());


		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}

}
