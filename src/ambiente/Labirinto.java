package ambiente;

import java.util.LinkedList;

import agente.AgenteLabirinto;
import geral.PosicaoXY;

public class Labirinto implements Runnable{

	private int tamanhoLabirinto;
	
	private String[][] labirinto;
	
	private AgenteLabirinto agente;
	
	private LinkedList <PosicaoXY> espaco_limpos;

	Thread t;
	
	public Labirinto(int tamanhoLabirinto) {
		this.tamanhoLabirinto = tamanhoLabirinto;
		this.construirNovoLabirinto();
		this.espaco_limpos = new LinkedList<PosicaoXY>();
		this.t = new Thread(this);
		this.t.start();	
	}
	
	public void run(){
		try{
			retirarEspacos();
		}catch (Exception e) {
			e.printStackTrace();
		}            
    }

	private void construirNovoLabirinto() {
		labirinto = new String[this.tamanhoLabirinto][this.tamanhoLabirinto];
		for (int i = 0; i < this.tamanhoLabirinto; i++) {
			for (int j = 0; j < this.tamanhoLabirinto; j++) {
				this.labirinto[i][j] = "S";
			}
		}
	}
	
	public void exibirLabirinto() {
		atualizarPosicaoAgente();
		for (int i = 0; i < tamanhoLabirinto; i++) {
			for (int j = 0; j < tamanhoLabirinto; j++) {
				if (labirinto[i][j].equals("*A*")) {
					System.out.print("|" + labirinto[i][j] + "|");
				}else{
					System.out.print("| " + labirinto[i][j] + " |");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}

	private void atualizarPosicaoAgente() {
		if (this.agente != null) {
			PosicaoXY posAgente = this.agente.getPosicao();
			labirinto[posAgente.getPosX()][posAgente.getPosY()] = "*A*";
		}
	}

	public int getTamanhoLabirinto() {
		return this.tamanhoLabirinto;
	}

	public String retornarValorPosicaoLabirinto(PosicaoXY posicao) {
		return this.labirinto[posicao.getPosX()][posicao.getPosY()];
	}

	public void setAgente(AgenteLabirinto agente) {
		this.agente = agente;
	}

	public void limpar() {
		PosicaoXY posicao = this.agente.getPosicao();
		this.espaco_limpos.add(this.agente.getPosicao());
		System.out.println("Entrou na posicao " + posicao.getPosX() + " " + posicao.getPosY() + "\n");
		labirinto[posicao.getPosX()][posicao.getPosY()] = "L";
	}
	

	public synchronized void retirarEspacos() throws InterruptedException{
		try {
			while(true){

				Thread.sleep(3000);
				PosicaoXY posicaoXY = this.espaco_limpos.getFirst();
				System.out.println("Saiu da posicao " + posicaoXY.getPosX() + " " + posicaoXY.getPosY() + "\n");
				this.espaco_limpos.removeFirst();
			}
		} catch(Exception e) {
			// TODO: handle exception
		}
    }
}
