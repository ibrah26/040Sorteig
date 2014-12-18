package brahim.maven.Sorteig;

import java.io.Serializable;

public class Carta implements Serializable{
	
	int numero;
	
	String nom;

	public Carta(int numero, String nom) {
		this.numero = numero;
		this.nom = nom;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
