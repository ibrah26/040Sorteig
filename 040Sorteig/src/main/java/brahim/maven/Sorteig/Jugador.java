package brahim.maven.Sorteig;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable{
	
	int diners;
	String nomJugador;
	Carta CartaMesGran;
	
	ArrayList<Carta> cartesJugador = new ArrayList<Carta>();

	public Jugador(int diners, String nomJugador) {
		this.diners = diners;
		this.nomJugador = nomJugador;
	}
	
	public int getMonedesPerApostar(int numeroMaxim){
		
		int N=0;
		int M=diners;
		
		if(this.diners>numeroMaxim){
			M = numeroMaxim;
		}
		
		int valorEntero = (int) Math.floor(Math.random()*(N-M+1)+M);
		
		this.diners -= valorEntero;
		
		return valorEntero;
	}

	public void setCarta(Carta c){
		
		cartesJugador.add(c);
	}
	
	public void CalculaCartaMesAlta(){
		
		Carta cartaGran = cartesJugador.get(0);
		
		for(Carta c: cartesJugador){
			
			if(c.getNumero() > cartaGran.getNumero()){
				
				cartaGran = c;
			}	
		}
		
		this.CartaMesGran = cartaGran;
	}
	
	public void EliminarCartesDeLaMa(){
		
		cartesJugador.clear();
		CartaMesGran = null;
		
	}
	
	public void CobrarDiners(int numDiners){
		this.diners += numDiners;
	}
	
	public Carta getCartaMesAlta(){
		return this.CartaMesGran;
	}
	
	public int getDiners() {
		return diners;
	}
	
	public String getNomJugador() {
		return nomJugador;
	}

	public void setDiners(int diners) {
		this.diners = diners;
	}
}
