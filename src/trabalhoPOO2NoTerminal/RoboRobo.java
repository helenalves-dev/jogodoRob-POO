package trabalhoPOO2NoTerminal;

import java.util.Scanner;

public class RoboRobo implements ModoJogoStrategy {
    @Override
    public void executar() {
        Scanner scanner=new Scanner(System.in);
            Robo robo1=new Robo(Texto.AZUL);
            Robo robo2=new Robo(Texto.VERDE);
            Robo[] robos={robo1, robo2};
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
                    if (robos[i].encontrarAlimento(comida)) {
                        break;
                    }
                }
            }while(!robo1.encontrarAlimento(comida) && !robo2.encontrarAlimento(comida));
            if(robo1.encontrarAlimento(comida)==true){
                System.out.println("Robô 1 encontrou a comida!");
            }
            else {
                System.out.println("Robô 2 encontrou a comida!");
            }
            System.out.println("---Robô 1---");
            System.out.println("Movimentos Válidos: "+robo1.getMovimentosValidos());
            System.out.println("Movimentos Inválidos: "+robo1.getMovimentosInvalidos());
            System.out.println("Posição Jogador: "+"("+robo1.getPosicaoX()+","+robo1.getPosicaoY()+")");
            System.out.println("---Robô 2---");
            System.out.println("Movimentos Válidos: "+robo2.getMovimentosValidos());
            System.out.println("Movimentos Inválidos: "+robo2.getMovimentosInvalidos());
            System.out.println("Posição Jogador: "+"("+robo2.getPosicaoX()+","+robo2.getPosicaoY()+")");
    }
    
}
