package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaDosLances = 0;
	private List<Lance> maiores;

    public void avalia(Leilao leilao) {
    	int count = 0;

        for(Lance lance : leilao.getLances()) {
            if(lance.getValor() > maiorDeTodos) {
                maiorDeTodos = lance.getValor();
            }
            if(lance.getValor() < menorDeTodos) {
                menorDeTodos = lance.getValor();
            }
			count++;
            mediaDosLances += lance.getValor();
        }
        mediaDosLances = mediaDosLances/count;
        pegaOsMaioresNo(leilao);
        
    }

    private void pegaOsMaioresNo(Leilao leilao) {
    	 maiores = new ArrayList<Lance>(leilao.getLances());
         
         maiores.sort(Comparator.<Lance>comparingDouble(Lance::getValor));
         Collections.reverse(maiores);
         
         maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}

	public double getMaiorLance() { return maiorDeTodos; }
    public double getMenorLance() { return menorDeTodos; }
    public double getMediaLance() { return mediaDosLances; }
    public List<Lance> getTresMaiores() { return this.maiores; }
	
}
