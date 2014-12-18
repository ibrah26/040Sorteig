package brahim.maven.Sorteig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class App{
	
	private static String  fitxer = "objectes.txt";
	
    public static void main( String[] args ){
    	
    	boolean existeix = MirarSiExisteixFitxer();
    	
    	if(existeix == true){
    		System.out.println("Carreguem el joc");
    	}else{
    		int numeroJugadors = ObtenirJugadors();
        	
        	Joc j = new Joc(numeroJugadors);
    	}
    	
    }
    
  
    public static boolean MirarSiExisteixFitxer(){
    	
    	boolean existeixFitxer = true;
    	
    	BufferedReader is = null;
		
		try{
			
			is = new BufferedReader(new FileReader(fitxer));			
			
			
		}catch(IOException e){
			
			existeixFitxer = false;
		
			System.out.println("Eroooooooooooooooooooooooooooooooooooooooooooooor");
			
			FileOutputStream out = null;
			
			try {
				out = new FileOutputStream(fitxer);
			
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			} 
		}
		
		return existeixFitxer;
    }
    
	public static int ObtenirJugadors(){
		
		return 4;
	}
}

