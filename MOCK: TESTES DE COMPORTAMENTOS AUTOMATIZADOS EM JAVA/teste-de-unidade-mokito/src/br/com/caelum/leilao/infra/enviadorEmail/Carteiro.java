package br.com.caelum.leilao.infra.enviadorEmail;

import br.com.caelum.leilao.dominio.Leilao;

public interface Carteiro {
	void envia(Leilao leilao);
}
