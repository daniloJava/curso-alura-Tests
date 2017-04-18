package br.com.caelum.desafios;

import org.junit.Assert;
import org.junit.Test;

public class PalindormoTest {

	@Test
	public void verificandoSeaFraseInseridaEhUmPalindormoComCaracteres(){
		Palindromo palindromo = new Palindromo();
		
		Assert.assertTrue("Eh um Palindromo", palindromo.ehPalindromo("Socorram-me subi no onibus em Marrocos"));
		
	}
	
	@Test
    public void deveIdentificarPalindromo() {
        Palindromo p = new Palindromo();

        Assert.assertTrue(p.ehPalindromo(
                "Anotaram a data da maratona"));
    }
	
	 @Test
	    public void deveIdentificarSeNaoEhPalindromo() {
	        Palindromo p = new Palindromo();

	        Assert.assertFalse(p.ehPalindromo(
		            "E preciso amar as pessoas como se nao houvesse amanha"));
	    }
	
}
