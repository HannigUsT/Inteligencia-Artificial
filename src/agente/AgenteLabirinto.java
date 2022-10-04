package agente;

import java.util.HashMap;
import ambiente.Labirinto;
import geral.PosicaoXY;

public class AgenteLabirinto {
	
	private Labirinto labirinto;

	private MovimentosAgenteLabirinto movimento;

	private PosicaoXY posXY;
	
	private int pilhaMovimentos;
	
	private HashMap<String, PosicaoXY> validar_posicao;


	public AgenteLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		labirinto.setAgente(this);
		this.posXY = new PosicaoXY();
		this.movimento = MovimentosAgenteLabirinto.DIREITA;
		this.validar_posicao = new HashMap<String, PosicaoXY>();
	}
	
	public void movimentar() {
		if (this.pilhaMovimentos >= 4) {
			return;
		}
		PosicaoXY proximoMovimento = retornarMovimento();
		String valor = this.labirinto.retornarValorPosicaoLabirinto(proximoMovimento);
		this.validar_posicao = this.labirinto.getEspacos_limpos();
		if (valor.equals("*A*")) {
			proximoMovimento();
			aumentarPilha();
			movimentar();
		} else {
			this.labirinto.limpar();
			this.posXY = proximoMovimento;
		}
	}

	private void aumentarPilha() {
		this.pilhaMovimentos++;
	}

	private void proximoMovimento() {
		switch(this.movimento) {
			case CIMA:
				this.movimento = MovimentosAgenteLabirinto.BAIXO;
				break;
			case BAIXO:
				this.movimento = MovimentosAgenteLabirinto.ESQUERDA;
				break;
			case ESQUERDA:
				this.movimento = MovimentosAgenteLabirinto.DIREITA;
				break;
			case DIREITA:
				this.movimento = MovimentosAgenteLabirinto.CIMA;
				break;
		}
	}

	public PosicaoXY retornarMovimento() {
		int retornoPosX = this.posXY.getPosX();
		int retornoPosY = this.posXY.getPosY();
	
		switch(movimento) {
			case CIMA:
				if (retornoPosX > 0) {
					if(this.validar_posicao.get(Integer.toString((retornoPosX - 1))+Integer.toString(retornoPosY)) == null) {
						retornoPosX -= 1;
					}
				}
			break;
			case BAIXO:
				if (retornoPosX < this.labirinto.getTamanhoLabirinto() - 1) {
					if(this.validar_posicao.get(Integer.toString((retornoPosX + 1))+Integer.toString(retornoPosY)) == null) {
						retornoPosX += 1;
						this.validar_posicao.remove(Integer.toString((retornoPosX + 1))+Integer.toString(retornoPosY));
					}
				}
			break;
			case ESQUERDA:
				if (retornoPosY > 0) {
					if(this.validar_posicao.get(Integer.toString((retornoPosX))+Integer.toString(retornoPosY - 1)) == null) {
						retornoPosY -= 1;
						this.validar_posicao.remove(Integer.toString((retornoPosX))+Integer.toString(retornoPosY - 1));
					}
					
				}
			break;
			case DIREITA:
				if (retornoPosY < this.labirinto.getTamanhoLabirinto() - 1) {
					if(this.validar_posicao.get(Integer.toString((retornoPosX))+Integer.toString(retornoPosY + 1)) == null) {
						retornoPosY += 1;
						this.validar_posicao.remove(Integer.toString((retornoPosX))+Integer.toString(retornoPosY + 1));
					}
				}
			break;
		}
		return new PosicaoXY(retornoPosX, retornoPosY);
	}

	public PosicaoXY getPosicao() {
		return this.posXY;
	}

	public boolean isAindaLimpando() {
		return true;
	}

	public void zerarPilha() {
		this.pilhaMovimentos = 0;
	}

	public void setPosicao(PosicaoXY posicaoXY) {
		this.posXY = posicaoXY;
	}
	
}
