//Trabalho JARENA de programação orientada a objetos

//Integrantes: Veronica Antonini Martini (Turma do professor Andrei) e Joao Francisco Arcari (Turma do professor Geomar)
//Nome da equipe: Agentes Fortoes

package br.uffs.cc.jarena;

public class AgentesFortoes extends Agente{

    private boolean fugir;              
    private int cogumelosX[]; 
    private int cogumelosY[];    
    private int numCogumelos;    
    private int direcaoAtual;         


public AgentesFortoes(Integer x, Integer y, Integer energia) {
	super(x, y, energia);

        fugir = false;
        cogumelosX = new int[40];
        cogumelosY = new int[40];
        numCogumelos = 0;
        direcaoAtual = geraDirecaoAleatoria();

}


	public void pensa() {
     if (fugir) {
        int dir = geraDirecaoAleatoria();
        if (podeMoverPara(dir)) {
            setDirecao(dir);
            fugir = false; 
    }else {
    para();
}
        return;
        
}
    if (numCogumelos > 0) {
            int maisProx = -1;
            double menorDist = 100100000;
            for (int i = 0; i < numCogumelos; i++) {
                int dx = cogumelosX[i] - getX();
                int dy = cogumelosY[i] - getY();
                double dist = Math.sqrt(dx*dx + dy*dy);
                if (dist < menorDist) {
                    menorDist = dist;
                    maisProx = i;
                }
            }
if (maisProx != -1) {
           int alvoX = cogumelosX[maisProx];
            int alvoY = cogumelosY[maisProx];
            if (alvoX > getX() && podeMoverPara(DIREITA)) {
                setDirecao(DIREITA);
                return;}
            if (alvoX < getX() && podeMoverPara(ESQUERDA)) {
                setDirecao(ESQUERDA);
                return;
                }
            if (alvoY > getY() && podeMoverPara(BAIXO)) {
                setDirecao(BAIXO);
                return;
                }
            if (alvoY < getY() && podeMoverPara(CIMA)) {
                setDirecao(CIMA);
                return;
            }
            }
        }



if (getEnergia() > 600 && podeDividir()) {
        boolean cogumeloperto = false;
        for (int i = 0; i < numCogumelos; i++) {
            int dx = cogumelosX[i] - getX();
            int dy = cogumelosY[i] - getY();
            double dist = Math.sqrt(dx*dx + dy*dy);
            if (dist <= 7.0) {
                cogumeloperto = true;
                break;
}
        }
        if (cogumeloperto) {
            divide();
            return;
        }
        }

    if (!podeMoverPara(direcaoAtual)) {
            for (int t = 0; t < 4; t++) {
                int novaDir = geraDirecaoAleatoria();
                if (podeMoverPara(novaDir)) {
                    direcaoAtual = novaDir;
                    break;
                }
            }
        }

        setDirecao(direcaoAtual);
}

	public void recebeuEnergia() {
        String msg = getX() + "," + getY();
		enviaMensagem(msg);
	}

	@Override
    public void tomouDano(int energiaRestanteInimigo) {
        if (getEnergia() < energiaRestanteInimigo) {
            fugir = true;
        } else {
            fugir = false;
        }
    }
	
	public void recebeuMensagem(String msg) {
        String[] partes = msg.split(",");
        int x = Integer.parseInt(partes[0]);
        int y = Integer.parseInt(partes[1]);
        boolean existe = false;
        for (int i = 0; i < numCogumelos; i++) {
            if (cogumelosX[i] == x && cogumelosY[i] == y) {
                existe = true;
                break;
            }
        }
        if (!existe && numCogumelos < 40) {
            cogumelosX[numCogumelos] = x;
            cogumelosY[numCogumelos] = y;
            numCogumelos++;
        }
        }
 

	@Override
	public String getEquipe() {
		return "Agentes Fortoes";
	}



    @Override
	public void ganhouCombate() {
    enviaMensagem("ganhou!!!");
    }
	


}




