package trabalhoPOO2NoTerminal;

import java.util.Scanner;

public class InteligenteBurro implements ModoJogoStrategy {
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
                    grid.mostrarGrid(robos, comida);
                    System.out.println("Aperte ENTER para continuar: ");
                    continuar=scanner.nextLine();
                    robos[i].encontrarAlimento(comida);

                }
            }while(!robos[0].encontrarAlimento(comida) || !robos[1].encontrarAlimento(comida));
            System.out.println("Robôs encontraram a comida!");
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
