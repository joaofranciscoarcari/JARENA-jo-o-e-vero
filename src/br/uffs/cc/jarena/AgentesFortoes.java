//Trabalho JARENA de programação orientada a objetos

//Integrantes: Veronica Antonini Martini (Turma do professor Andrei) e Joao Francisco Arcari (Turma do professor Geomar)
//Nome da equipe: Agentes Fortoes

package br.uffs.cc.jarena;

public class AgentesFortoes extends Agente{

    private boolean fugir;              
    private int cogumelosX[]; 
    private int cogumelosY[];    
    private int numCogumelos; 
    private int contadordemsg;              

public AgentesFortoes(Integer x, Integer y, Integer energia) {
	super(x, y, energia);

    fugir = false;
    cogumelosX = new int[20];
    cogumelosY = new int[20];
    numCogumelos = 0;
    contadordemsg= 0;

}

	public void cerebro() {
     if (fugir) {
        int dir = geraDirecaoAleatoria();
        if (podeMoverPara(dir)) {
            setDirecao(dir);
            fugir = false; 
    }
    else {
    para();}
        return;
        
		}
	}
	
	public void recebeuEnergia() {
	}
	
	@Override
    public void tomouDano(int energiaRestanteInimigo) {
        if (getEnergia() < energiaRestanteInimigo) {
            fugir = true;
        } else {
            fugir = false;
        }
    }
	
	public void ganhouCombate() {

    }
	
	public void recebeuMensagem(String msg) {
	}
	
	public String getEquipe() {
		return "Agentes Fortoes";
	}



}

