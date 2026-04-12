//Trabalho JARENA de programação orientada a objetos

//Integrantes: Veronica Antonini Martini (Turma do professor Andrei) e Joao Francisco Arcari (Turma do professor Geomar)
//Nome da equipe: Agentes Fortoes

package br.uffs.cc.jarena;

public class AgentesFortoes extends Agente{

public AgentesFortoes(Integer x, Integer y, Integer energia) {
	super(x, y, energia);
	setDirecao(geraDirecaoAleatoria());}

    	
	public void pensa() {
		if(!podeMoverPara(getDirecao())) {
			setDirecao(geraDirecaoAleatoria());
		}
		
		if(podeDividir() && getEnergia() >= 800) {
			divide();
		}
	}
	
	public void recebeuEnergia() {
	}
	
	public void tomouDano(int energiaRestanteInimigo) {
	}
	
	public void ganhouCombate() {
        
    }
	
	public void recebeuMensagem(String msg) {
	}
	
	public String getEquipe() {
		return "Agentes Fortoes";
	}
}
