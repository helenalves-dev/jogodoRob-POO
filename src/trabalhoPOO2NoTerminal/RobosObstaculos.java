package trabalhoPOO2NoTerminal;

import java.util.Scanner;

public class RobosObstaculos implements ModoJogoStrategy {
    @Override
    public void executar() {
        Scanner scanner=new Scanner(System.in);
        Robo roboBurro=new Robo(Texto.AZUL);
        RoboInteligente roboInteligente=new RoboInteligente(Texto.VERDE);
        Robo[] robos={roboBurro, roboInteligente};
        System.out.println("---Posição do Alimento---");
        int x,y;
        boolean valido=false;
        Alimento comida=null;
        do{
            try{
                System.out.print("Indice x: ");
                x=scanner.nextInt();
                System.out.print("Indice y: ");
                y=scanner.nextInt();
                comida=new Alimento(x, y);
                valido=true;
            }catch(ForaDoLimiteGridException e){
                System.out.println(e.getMessage());
                System.out.println("Tente Novamente");
            }
        }while(!valido);
        Grid grid=new Grid();
        grid.mostrarGrid(robos, comida);

        System.out.print("Digite a quantidade de rochas(até 3): ");
        int qtdRochas = scanner.nextInt();
        while (qtdRochas < 1 || qtdRochas > 3) {
            System.out.println("Valor inválido!");
            System.out.print("Digite a quantidade de bombas(até 3): ");
            qtdRochas = scanner.nextInt();
        }

        System.out.print("Digite a quantidade de bombas(até 3): ");
        int qtdBombas = scanner.nextInt();
        while (qtdBombas < 1 || qtdBombas > 3) {
            System.out.println("Valor inválido!");
            System.out.print("Digite a quantidade de bombas(até 3): ");
            qtdBombas = scanner.nextInt();
        }
        
        for (int i = 0; i < qtdRochas; i++) {
            valido=false;
            Rocha rocha=null;
            System.out.printf("---Posição da %d° Rocha---\n", i+1);
            do{
                try{
                    System.out.print("Indice x: ");
                    x=scanner.nextInt();
                    System.out.print("Indice y: ");
                    y=scanner.nextInt();
                    if (Obstaculo.procurarObstaculo(x, y) == null && (comida.getPosicaoX() != x || comida.getPosicaoY() != y) && (x != 0 || y != 0)) {
                        valido = true;
                        rocha=new Rocha(1, x, y);
                    } else {
                        System.out.println("Já existe algo nessa posição");
                        System.out.println("Tente Novamente");
                    }
                }catch(ForaDoLimiteGridException e){
                    --i;
                    System.out.println(e.getMessage());
                    System.out.println("Tente Novamente");
                }
            }while(!valido);
            grid.mostrarGrid(robos, comida, Obstaculo.getObstaculos());
        }
        for (int i = 0; i < qtdBombas; i++) {
            valido=false;
            Bomba bomba=null;
            System.out.printf("---Posição da %d° bomba---\n", i+1);
            do{
                try{
                    System.out.print("Indice x: ");
                    x=scanner.nextInt();
                    System.out.print("Indice y: ");
                    y=scanner.nextInt();
                    if (Obstaculo.procurarObstaculo(x, y) == null && (comida.getPosicaoX() != x || comida.getPosicaoY() != y) && (x != 0 || y != 0)) {
                        valido = true;
                        bomba=new Bomba(2, x, y);
                    } else {
                        System.out.println("Já existe algo nessa posição");
                        System.out.println("Tente Novamente");
                    }
                }catch(ForaDoLimiteGridException e){
                    --i;
                    System.out.println(e.getMessage());
                    System.out.println("Tente Novamente");
                }
            }while(!valido);
            grid.mostrarGrid(robos, comida, Obstaculo.getObstaculos());
        }
        scanner.nextLine();
        String continuar;
        System.out.println("Aperte ENTER para continuar: ");
        continuar=scanner.nextLine();
        do{
            for (int i = 0; i < 2; ++i) {
                if (!robos[i].getAtivo()) {
                    continue;
                }
                try{
                    robos[i].mover();
                }catch(MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                    System.out.println("Tente Novamente");
                }catch(ForaDoLimiteGridException e){
                    System.out.println(e.getMessage());
                    System.out.println("Tente Novamente");
                }
                grid.mostrarGrid(robos, comida, Obstaculo.getObstaculos());
                System.out.println("Aperte ENTER para continuar: ");
                continuar=scanner.nextLine();
                if (robos[i].encontrarAlimento(comida)) {
                    break;
                }
            }
        }while((!robos[0].encontrarAlimento(comida) && !robos[1].encontrarAlimento(comida)) && (robos[0].getAtivo() || robos[1].getAtivo()));
        if(robos[0].encontrarAlimento(comida)==true){
            System.out.println("O robô 1 encontrou a comida!");
        }
        else if(robos[1].encontrarAlimento(comida)){
            System.out.println("O robô 2 encontrou a comida!");
        }

        System.out.println("Acabou!");
        System.out.println("---Robô 1---");
        System.out.println("Movimentos Válidos: "+robos[0].getMovimentosValidos());
        System.out.println("Movimentos Inválidos: "+robos[0].getMovimentosInvalidos());
        System.out.println("Posição Jogador: "+"("+robos[0].getPosicaoX()+","+robos[0].getPosicaoY()+")");
        System.out.println("---Robô 2---");
        System.out.println("Movimentos Válidos: "+robos[1].getMovimentosValidos());
        System.out.println("Movimentos Inválidos: "+robos[1].getMovimentosInvalidos());
        System.out.println("Posição Jogador: "+"("+robos[1].getPosicaoX()+","+robos[1].getPosicaoY()+")");
    }
    
}
