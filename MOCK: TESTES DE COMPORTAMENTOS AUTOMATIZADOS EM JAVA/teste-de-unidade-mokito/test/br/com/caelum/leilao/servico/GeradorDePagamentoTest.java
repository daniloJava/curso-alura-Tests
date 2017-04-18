package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Pagamento;
import br.com.caelum.leilao.dominio.Usuario;

public class GeradorDePagamentoTest {

	
	 @Test
	    public void deveGerarPagamentoParaUmLeilaoEncerrado() {

	        RepositorioDeLeiloes leiloes = mock(RepositorioDeLeiloes.class);
	        RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);
	        Avaliador avaliador = mock(Avaliador.class);

	        Leilao leilao = new CriadorDeLeilao()
	            .para("Playstation")
	            .lance(new Usuario("José da Silva"), 2000.0)
	            .lance(new Usuario("Maria Pereira"), 2500.0)
	            .constroi();

	        when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));
	        when(avaliador.getMaiorLance()).thenReturn(2500.0);

	        GeradorDePagamento gerador = 
	            new GeradorDePagamento(leiloes, pagamentos, avaliador);
	        gerador.gera();

	        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
	        verify(pagamentos).salva(argumento.capture());
	        Pagamento pagamentoGerado = argumento.getValue();
	        assertEquals(2500.0, pagamentoGerado.getValor(), 0.00001);
	    }
}
