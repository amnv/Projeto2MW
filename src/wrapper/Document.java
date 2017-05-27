package wrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Document {

	private String linha;
	private String doc;
	private String comments;
	private String link;
	private String subjects;
	private String autor;
	private String title;
	private String journalRef;

	private String fileName;
	
	public Document() {
		this.linha = null;
		this.doc = null;
		this.comments = null;
		this.link = null;
		this.subjects = null;
		this.autor = null;
		this.title = null;
		this.journalRef = null;
		
		this.fileName = fileName;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJournalRef() {
		return journalRef;
	}

	public void setJournalRef(String journalRef) {
		this.journalRef = journalRef;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
