package ambiente;

import java.util.LinkedList;
import agente.AgenteLabirinto;
import geral.PosicaoXY;

import java.util.HashMap;

public class Labirinto implements Runnable{

	private int tamanhoLabirinto;
	
	private String[][] labirinto;
	
	private AgenteLabirinto agente;
	
	private LinkedList <PosicaoXY> espaco_limpos;

	private HashMap<String, PosicaoXY> espacos_limpos;

	Thread t;
	
	public Labirinto(int tamanhoLabirinto) {
		this.tamanhoLabirinto = tamanhoLabirinto;
		this.construirNovoLabirinto();
		this.espaco_limpos = new LinkedList<PosicaoXY>();
		this.espacos_limpos = new HashMap<String, PosicaoXY>();
	}
	
	public void run(){
		try{
			retirarEspacos(t.getName());
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

	public LinkedList<PosicaoXY> getEspaco_limpos(){
		return this.espaco_limpos;
	}

	public HashMap<String, PosicaoXY> getEspacos_limpos(){
		return this.espacos_limpos;
	}

	public String retornarValorPosicaoLabirinto(PosicaoXY posicao) {
		return this.labirinto[posicao.getPosX()][posicao.getPosY()];
	}

	public void setAgente(AgenteLabirinto agente) {
		this.agente = agente;
	}

	public void limpar() {
		PosicaoXY posicao = this.agente.getPosicao();
		this.espacos_limpos.put(Integer.toString(posicao.getPosX())+Integer.toString(posicao.getPosY()),this.agente.getPosicao());
		labirinto[posicao.getPosX()][posicao.getPosY()] = "L";
		this.t = new Thread(this, Integer.toString(posicao.getPosX())+Integer.toString(posicao.getPosY()));
		this.t.start();	
	}

	public synchronized void retirarEspacos(String posicao) throws InterruptedException {
		try {
			Thread.sleep(10000);
			this.espacos_limpos.remove(posicao);
		} catch(Exception e) {
			// TODO: handle exception
		}
    }
}
