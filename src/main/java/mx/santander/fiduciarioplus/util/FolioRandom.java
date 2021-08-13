package mx.santander.fiduciarioplus.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class FolioRandom {

	public static String getRandomFolio() {
		StringBuffer folio = new StringBuffer();
		try {
			SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
			for (int i = 0; i < 10; i++) {
				folio.append(number.nextInt(10));
			}
		} catch (NoSuchAlgorithmException e) {
		}
		return folio.toString();
	}

}
