package brahim.maven.Sorteig;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Joc implements Serializable{
	
	ArrayList<Jugador> jugadors = new ArrayList<Jugador>();
	
	ArrayList<Carta> Cartes = new ArrayList<Carta>();
	
	int dinersApostats=0;
	
	int NumeroCartesHaDeixar = jugadors.size();
	
	static final String fitxer = "objectes.txt";
	
	public Joc(int numJugadors) {
		
		CrearJugadors(numJugadors);
		CrearCartes();
		StartJoc();
	}
	
	public void GuardarParitda(){
		
		try {
			FileOutputStream file = new FileOutputStream(fitxer);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			out.writeObject(jugadors);
			out.writeObject(Cartes);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public  void CrearJugadors(int numeroJugadors){
		
		String[] NomJugadors = {"pepe", "juan", "marc", "lore", "cuak", "lay", "dan", "san", "leke",};
		
		for(int i=1; i < numeroJugadors+1; i++){
			
			Jugador j = new Jugador(ObtenirNumerosAleatoris(10, 20), NomJugadors[i]);
			
			jugadors.add(j);
		}
	}
		
	public void StartJoc(){
		
		while(MirarSiHaAcabat()){
			System.out.println("******************************* NOVA PARTIDA *********************************************+");
			NumeroCartesHaDeixar = jugadors.size();
			AgafarCartes();
			CompararCartes();
			MirarSiHaAcabat();
			ReinicarVariables();
		}
	}
	
	public  int ObtenirNumerosAleatoris(int N, int M){
		
		int valorEntero = (int) Math.floor(Math.random()*(N-M+1)+M);
		
		return valorEntero;
	}
	
	public void AgafarCartes(){
		
		for(Jugador j : jugadors){
			System.out.print("Nom: "+j.nomJugador+", Abans €: "+j.getDiners());
			int MaximMonedesAapostar= Cartes.size() - NumeroCartesHaDeixar;
			int numMonedes = j.getMonedesPerApostar( MaximMonedesAapostar );
			System.out.print(", Apostats € :"+numMonedes+", Ara €: "+j.getDiners());
			dinersApostats += numMonedes;
			DonarCartesAlsJugadors(numMonedes, j);
			NumeroCartesHaDeixar--;
			GuardarParitda();
		}	
	}
	
	public void CompararCartes(){
		
		ArrayList<Jugador> JugadorsGuanyadors = new ArrayList<Jugador>();
			
		ArrayList<Jugador> JugadorsAcomprarar = new ArrayList<Jugador>(jugadors);
		
	    JugadorsGuanyadors.add(jugadors.get(0));
	    
	    JugadorsAcomprarar.remove(0);
	    
		for(Jugador j: JugadorsAcomprarar){
			
			if(j.getCartaMesAlta().getNumero()>JugadorsGuanyadors.get(0).getCartaMesAlta().getNumero()){
				JugadorsGuanyadors.clear();
				JugadorsGuanyadors.add(j);
				
			}else if(j.getCartaMesAlta().getNumero() == JugadorsGuanyadors.get(0).getCartaMesAlta().getNumero()){
				JugadorsGuanyadors.add(j);
			}
		}
		RepartirDinersApostats(JugadorsGuanyadors);
	}
	
	public void RepartirDinersApostats(ArrayList<Jugador> JugadorsGuanyadors){
		
		System.out.println("Diners totals Apostats= "+ dinersApostats);
		
		int residu =   dinersApostats % JugadorsGuanyadors.size();
		
		int DinersArepartir = dinersApostats - residu;
		
		dinersApostats -= DinersArepartir; 
		
		int DinersPerPersona = DinersArepartir / JugadorsGuanyadors.size();
		
		System.out.println("Diners per Persona = "+DinersPerPersona);
			
		for(Jugador j : JugadorsGuanyadors){
			System.out.print(j.getNomJugador()+" €= "+j.getDiners());
			j.CobrarDiners(DinersPerPersona);
			System.out.println(" Final €: "+j.getDiners()+", Carta : "+j.getCartaMesAlta().getNumero());
		}
		System.out.println();
		
	}
	
	public void CrearCartes(){
	
		String[] noms = {"cors", "diamants", "pirques", "trèvols"};
		
		for(int r=0; r<4; r++){
			for(int i=0; i<12; i ++){
				Carta c = new Carta(i, noms[r]);
				Cartes.add(c);
			}
		}
	}
	
	public void DonarCartesAlsJugadors(int numMonedes, Jugador j){
		
		for(int i=0; i<numMonedes; i++){
			j.setCarta(getCartesAlAtzar());
		}
		
		j.CalculaCartaMesAlta();
		
		System.out.println(" Carta: "+j.getCartaMesAlta().getNumero());

	}
	
	public boolean MirarSiHaAcabat(){
		
		int JugadorsAmbDiners=0;
		
		for(int i =0 ; i<jugadors.size(); i++){
			
			Jugador j = jugadors.get(i);
			
			if(j.getDiners()>0){
				JugadorsAmbDiners++;
			}else{
				jugadors.remove(i);
			}
		}
		
		if(JugadorsAmbDiners>1){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void ReinicarVariables(){
		
		for(Jugador j : jugadors){
			j.EliminarCartesDeLaMa();
		}
		Cartes.clear();
		CrearCartes();
	}
	
	public Carta getCartesAlAtzar(){
		
		int N=0;
		int M = Cartes.size();
		
		int valorEntero = (int) Math.floor(Math.random()*(N-M+1)+M);
		
		Carta CartaAenviar = Cartes.get(valorEntero);
		
		Cartes.remove(valorEntero);
		
		return CartaAenviar;
	}
}