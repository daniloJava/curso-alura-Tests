package br.com.caelum.leilao.teste;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.modelo.Leilao;
import br.com.caelum.leilao.modelo.Usuario;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

public class LeiloesWSTest {

	@Test
    public void deveRetornarUmLeilao() {
        JsonPath path = given()
                .parameter("leilao.id", 1)
                .header("Accept", "application/json")
                .get("/leiloes/show")
                .andReturn().jsonPath();

        Leilao leilao = path.getObject("leilao", Leilao.class);

        Usuario mauricio = new Usuario(1l, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
        Leilao esperado = new Leilao(1l, "Geladeira", 800.0, mauricio, false);

        assertEquals(esperado, leilao);
    }
	
	 @Test
	    public void deveInserirLeiloes() {
	        Usuario mauricio = new Usuario(1l, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
	        Leilao leiloes = new Leilao(1l, "Geladeira", 800.0, mauricio, false);

	        XmlPath retorno = 
	                given()
	                    .header("Accept", "application/xml")
	                    .contentType("application/xml")
	                    .body(leiloes)
	                .expect()
	                    .statusCode(200)
	                .when()
	                    .post("/leiloes")
	                .andReturn()
	                    .xmlPath();

	        Leilao resposta = retorno.getObject("leilao", Leilao.class);

	        assertEquals("Geladeira", resposta.getNome());

	        // deletando aqui
	        given()
	            .contentType("application/xml")
	            .body(resposta)
	        .expect()
	            .statusCode(200)
	        .when()
	            .delete("/leiloes/deleta")
	        .andReturn().asString();
	    }
	
}
