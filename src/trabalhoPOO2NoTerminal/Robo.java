package trabalhoPOO2NoTerminal;

import java.util.Random;

public class Robo {
	private String cor;
	private int posicaoX;
	private int posicaoY;
	private int posicaoAnteriorX;
	private int posicaoAnteriorY;
	private int movimentosValidos;
	private int movimentosInvalidos;
	private boolean ativo=true;
	private Random random = new Random();

	public Robo(String cor, int indiceX, int indiceY, int movimentosValidos, int movimentosInvalidos){
		this.cor=cor;
		this.posicaoX=indiceX;
		this.posicaoY=indiceY;
		this.movimentosValidos=movimentosValidos;
		this.movimentosInvalidos=movimentosInvalidos;
	}

	public Robo(String cor){
		this.cor=cor;
		this.posicaoX=0;
		this.posicaoY=0;
		this.movimentosValidos=0;
		this.movimentosInvalidos=0;
	}
	
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	public int getPosicaoAnteriorX() {
		return posicaoAnteriorX;
	}

	public void setPosicaoAnteriorX(int posicaoX) {
		this.posicaoAnteriorX = posicaoX;
	}

	public int getPosicaoAnteriorY() {
		return posicaoAnteriorY;
	}

	public void setPosicaoAnteriorY(int posicaoY) {
		this.posicaoAnteriorY = posicaoY;
	}

	public int getMovimentosValidos() {
		return movimentosValidos;
	}
	public void setMovimentosValidos(int movimentosValidos) {
		this.movimentosValidos = movimentosValidos;
	}

	public int getMovimentosInvalidos() {
		return movimentosInvalidos;
	}
	public void setMovimentosInvalidos(int movimentosInvalidos) {
		this.movimentosInvalidos = movimentosInvalidos;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void mover(String escolha) throws MovimentoInvalidoException, ForaDoLimiteGridException{
		if (!getAtivo()) {
			return;
		}
		Movimentos movimento = Movimentos.acao(escolha);
		int[] novaPosicao = {-1,-1};
        if (movimento != null) {
            novaPosicao=movimento.mover(this);
        }
		int x=novaPosicao[0];
        int y=novaPosicao[1];
        if(x<0 || y<0){
			movimentosInvalidos++;
            throw new MovimentoInvalidoException();
        }
        if(x>4 || y>4){
			movimentosInvalidos++;
            throw new ForaDoLimiteGridException();
        }
		posicaoAnteriorX=posicaoX;
		posicaoAnteriorY=posicaoY;
        posicaoX=x;
        posicaoY=y;
		movimentosValidos++;
	}

	public void mover(int escolha) throws MovimentoInvalidoException, ForaDoLimiteGridException{
		if (!getAtivo()) {
			return;
		}
		Movimentos movimento = Movimentos.acao(escolha);
		int[] novaPosicao = {-1,-1};
        if (movimento != null) {
            novaPosicao=movimento.mover(this);
        }
		int x=novaPosicao[0];
        int y=novaPosicao[1];
        if(x<0 || y<0){
			movimentosInvalidos++;
            throw new MovimentoInvalidoException();
        }
        if(x>4 || y>4){
			movimentosInvalidos++;
            throw new ForaDoLimiteGridException();
        }
		posicaoAnteriorX=posicaoX;
		posicaoAnteriorY=posicaoY;
        posicaoX=x;
        posicaoY=y;
		movimentosValidos++;
	}

	public void mover() throws MovimentoInvalidoException, ForaDoLimiteGridException{
		if (!getAtivo()) {
			return;
		}
		int escolha=random.nextInt(4)+1;
		Movimentos movimento = Movimentos.acao(escolha);
		int[] novaPosicao = {-1,-1};
        if (movimento != null) {
            novaPosicao=movimento.mover(this);
        }
		int x=novaPosicao[0];
        int y=novaPosicao[1];
		if(x<0 || y<0){
			setMovimentosInvalidos(getMovimentosInvalidos()+1);
            throw new MovimentoInvalidoException();
        }
        if(x>4 || y>4){
			setMovimentosInvalidos(getMovimentosInvalidos()+1);
            throw new ForaDoLimiteGridException();
        }
		setPosicaoAnteriorX(getPosicaoX());
		setPosicaoAnteriorY(getPosicaoY());
        setPosicaoX(x);
        setPosicaoY(y);
		setMovimentosValidos(getMovimentosValidos()+1);
		Obstaculo obstaculo = Obstaculo.procurarObstaculo(x, y);
		if (obstaculo != null) {
			obstaculo.bater(this);
		}
	}

	public boolean encontrarAlimento(Alimento comida){
        if(comida.getPosicaoX()==posicaoX && comida.getPosicaoY()==posicaoY){
			setAtivo(false);
            return true;
        }else{
            return false;
        }
    }
}