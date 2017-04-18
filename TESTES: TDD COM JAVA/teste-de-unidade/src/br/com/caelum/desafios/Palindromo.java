package br.com.caelum.desafios;

public class Palindromo {

	public boolean ehPalindromo(String frase) {

		String fraseFiltrada = frase.toUpperCase().replace(" ", "")
				.replace("-", "");

		for (int i = 0; i < fraseFiltrada.length(); i++) {
			// bug corrigido na linha abaixo!
			int outroLado = fraseFiltrada.length() - i - 1;
			if (fraseFiltrada.charAt(i) != fraseFiltrada.charAt(outroLado)) {
				return false;
			}
		}

		return true;
	}

}
