package trabalhoPOO2NoTerminal;

public class Texto {

    private Texto() {
        // Construtor privado para evitar instanciação
    }

    public static final String RESET = "\u001B[0m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String ROXO = "\u001B[35m";
    public static final String CIANO = "\u001B[36m";

    public static String textoColorido(String texto, String cor){
        return cor + texto + RESET;
    }
}
