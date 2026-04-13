//Trabalho JARENA de programação orientada a objetos

//Integrantes: Veronica Antonini Martini (Turma do professor Andrei Braga) e Joao Francisco Arcari (Turma do professor Geomar Schreiner)
//Nome da equipe: Agentes Fortoes

package br.uffs.cc.jarena;

public class AgentesFortoes extends Agente{

    private boolean fugir;              
    private int cogumelosX[]; 
    private int cogumelosY[];    
    private int numCogumelos;    
    private int direcaoAtual;         
    private int turnosSemEnergia;    
    private int ultimoX, ultimoY;      
    private boolean coletando;
    private boolean jaDividiu;
    
    


public AgentesFortoes(Integer x, Integer y, Integer energia) {
	super(x, y, energia);

        fugir = false;
        cogumelosX = new int[40];
        cogumelosY = new int[40];
        numCogumelos = 0;
        if (getId() % 2 == 0) {
            direcaoAtual = DIREITA;
        } else {
            direcaoAtual = ESQUERDA;
        }
        turnosSemEnergia = 0;
        ultimoX = x;
        ultimoY = y;
        coletando = false;
        jaDividiu = false;

}


	public void pensa() {

        if (coletando) {
            turnosSemEnergia++;
            if (turnosSemEnergia > 2) {
                for (int i = 0; i < numCogumelos; i++) {
                    if (Math.abs(cogumelosX[i] - getX()) <= 2 && Math.abs(cogumelosY[i] - getY()) <= 2) {
                        cogumelosX[i] = cogumelosX[numCogumelos-1];
                        cogumelosY[i] = cogumelosY[numCogumelos-1];
                        numCogumelos--;
                        i--;
                    }
                }
                coletando = false;
                turnosSemEnergia = 0;
                int novaDir = geraDirecaoAleatoria();
                while (!podeMoverPara(novaDir)) {
                    novaDir = geraDirecaoAleatoria();
                }
                direcaoAtual = novaDir;
                setDirecao(direcaoAtual);
                return;
            } else {
                para();
                return;
            }
        }

        if (fugir) {
            int oposto;
            if (direcaoAtual == DIREITA) oposto = ESQUERDA;
            else if (direcaoAtual == ESQUERDA) oposto = DIREITA;
            else if (direcaoAtual == CIMA) oposto = BAIXO;
            else oposto = CIMA;
            if (podeMoverPara(oposto)) {
                setDirecao(oposto);
                direcaoAtual = oposto;
            } else {
                int novaDir = geraDirecaoAleatoria();
                if (podeMoverPara(novaDir)) {
                    setDirecao(novaDir);
                    direcaoAtual = novaDir;
                } else {
                    para();
                }
            }
            fugir = false;
            return;
        }

        if (numCogumelos > 0) {
            int maisProx = -1;
            double menorDist = Double.MAX_VALUE;
            for (int i = 0; i < numCogumelos; i++) {
                double d = Math.hypot(cogumelosX[i] - getX(), cogumelosY[i] - getY());
                if (d < menorDist) {
                    menorDist = d;
                    maisProx = i;
                }
            }
            if (maisProx != -1) {
                int alvoX = cogumelosX[maisProx];
                int alvoY = cogumelosY[maisProx];
                if (Math.abs(alvoX - getX()) <= 1 && Math.abs(alvoY - getY()) <= 1) {
                    para();
                    coletando = true;
                    turnosSemEnergia = 0;
                    return;
                }
                if (alvoX > getX() && podeMoverPara(DIREITA)) {
                    setDirecao(DIREITA);
                    direcaoAtual = DIREITA;
                    return;
                }
                if (alvoX < getX() && podeMoverPara(ESQUERDA)) {
                    setDirecao(ESQUERDA);
                    direcaoAtual = ESQUERDA;
                    return;
                }
                if (alvoY > getY() && podeMoverPara(BAIXO)) {
                    setDirecao(BAIXO);
                    direcaoAtual = BAIXO;
                    return;
                }
                if (alvoY < getY() && podeMoverPara(CIMA)) {
                    setDirecao(CIMA);
                    direcaoAtual = CIMA;
                    return;
                }
            }
        }

        if (!jaDividiu && getEnergia() > 700 && podeDividir()) {
            divide();
            jaDividiu = true;
            return;
        }

        if (!podeMoverPara(direcaoAtual)) {
            if (direcaoAtual == DIREITA || direcaoAtual == ESQUERDA) {
                if (podeMoverPara(CIMA)) {
                    direcaoAtual = CIMA;
                } else if (podeMoverPara(BAIXO)) {
                    direcaoAtual = BAIXO;
                } else {
                    direcaoAtual = geraDirecaoAleatoria();
                }
            } else {
                if (podeMoverPara(DIREITA)) {
                    direcaoAtual = DIREITA;
                } else if (podeMoverPara(ESQUERDA)) {
                    direcaoAtual = ESQUERDA;
                } else {
                    direcaoAtual = geraDirecaoAleatoria();
                }
            }
        }

        setDirecao(direcaoAtual);
    }





	public void recebeuEnergia() {
        turnosSemEnergia = 0;
        coletando = true;
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
                } }
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
    }
	
}

