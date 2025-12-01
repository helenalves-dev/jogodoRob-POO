package trabalhoPOO2NoTerminal;

public enum ModosJogo {
    USUARIO_ROBO(1, new UsuarioRobo()),
    ROBO_ROBO(2, new RoboRobo()),
    INTELIGENTE_BURRO(3, new InteligenteBurro()),
    ROBOS_OBSTACULOS(4, new RobosObstaculos());

    final int opcao;
    final ModoJogoStrategy estrategia;
    ModosJogo(int opcao, ModoJogoStrategy estrategia) {
        this.opcao = opcao;
        this.estrategia = estrategia;
    }

    public void executarModo() {
        estrategia.executar();
    }

    public static ModosJogo escolherModo(int modoEscolhido) {
        for (ModosJogo modo : values()) {
            if (modo.opcao == modoEscolhido) {
                return modo;
            }
        }
            return null;
    }

}
