package trabalhoPOO2NoTerminal;

import java.util.ArrayList;
import java.util.Random;

public class RoboInteligente extends Robo{
	private ArrayList<Integer> movimentosAnteriores = new ArrayList<>();

	public RoboInteligente(String cor, int indiceX, int indiceY, int movimentosValidos, int movimentosInvalidos){
		super(cor, indiceX, indiceY, movimentosValidos, movimentosInvalidos);
	}
	public RoboInteligente(String cor){
		super(cor);
	}
	public int gerarAção(){
		Random random = new Random();
		return random.nextInt(4)+1;
	}
	public void mover() throws MovimentoInvalidoException, ForaDoLimiteGridException{
		if (!getAtivo()) {
			return;
		}
		int escolha;
		do{
			escolha=gerarAção();
		}while(movimentosAnteriores.contains(escolha));
		Movimentos movimento = Movimentos.acao(escolha);
		int[] novaPosicao = {-1,-1};
        if (movimento != null) {
            novaPosicao=movimento.mover(this);
        }
		int x=novaPosicao[0];
        int y=novaPosicao[1];
		if(x<0 || y<0){
			movimentosAnteriores.add(escolha);
			setMovimentosInvalidos(getMovimentosInvalidos()+1);
            throw new MovimentoInvalidoException();
        }
        if(x>4 || y>4){
			movimentosAnteriores.add(escolha);
			setMovimentosInvalidos(getMovimentosInvalidos()+1);
            throw new ForaDoLimiteGridException();
        }
		setPosicaoAnteriorX(getPosicaoX());
		setPosicaoAnteriorY(getPosicaoY());
        setPosicaoX(x);
        setPosicaoY(y);
		setMovimentosValidos(getMovimentosValidos()+1);
		movimentosAnteriores.clear();
		Obstaculo obstaculo = Obstaculo.procurarObstaculo(x, y);
		if (obstaculo != null) {
			obstaculo.bater(this);
		}
	}
}