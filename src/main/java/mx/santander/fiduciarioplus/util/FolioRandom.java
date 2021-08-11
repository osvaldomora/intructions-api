package mx.santander.fiduciarioplus.util;


public class FolioRandom {
	
	public static String getRandomFolio() {
		String numeroRandom1 = String.valueOf((int)(Math.random()*99999));
		String numeroRandom2 = String.valueOf((int)(Math.random()*99999));
		StringBuffer folio = new StringBuffer(numeroRandom1 + numeroRandom2);
		return folio.toString();
	}
	
}
