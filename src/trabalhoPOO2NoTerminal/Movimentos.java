package trabalhoPOO2NoTerminal;

public enum Movimentos {
    UP(1, "UP"){
        public int[] mover(Robo robo){
            int y=robo.getPosicaoY()+1;
            return new int[]{robo.getPosicaoX(), y};
        }
    },
    DOWN(2, "DOWN"){
        public int[] mover(Robo robo){
            int y=robo.getPosicaoY()-1;
            return new int[]{robo.getPosicaoX(), y};
        }
    },
    RIGHT(3, "RIGHT"){
        public int[] mover(Robo robo){
            int x=robo.getPosicaoX()+1;
            return new int[]{x, robo.getPosicaoY()};
        }
    },
    LEFT(4, "LEFT"){
        public int[] mover(Robo robo){
            int x=robo.getPosicaoX()-1;
            return new int[]{x, robo.getPosicaoY()};
        }
    };

    final int value;
    final String name;

    Movimentos(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Movimentos acao(int movimentoJogador) {
        for (Movimentos movimento : values()) {
            if (movimento.value == movimentoJogador) {
                return movimento;
            }
        }
            return null;
    }

    public static Movimentos acao(String movimentoJogador) {
        for (Movimentos movimento : values()) {
            if (movimento.name.equals(movimentoJogador)) {
                return movimento;
            }
        }
            return null;
    }

    public abstract int[] mover(Robo robo);
}
