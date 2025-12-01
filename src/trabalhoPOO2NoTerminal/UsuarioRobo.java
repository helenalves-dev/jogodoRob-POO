package trabalhoPOO2NoTerminal;

import java.util.Scanner;

public class UsuarioRobo implements ModoJogoStrategy {
    @Override
    public void executar() {
        Scanner scanner=new Scanner(System.in);
            Robo robo;
                int cor;
                System.out.println("---Cor do Robô---");
                System.out.println("1) Azul");
                System.out.println("2) Amarelo");
                System.out.println("3) Verde");
                System.out.println("4) Roxo");
                do{
                    System.out.print("Digite uma opção válida: ");
                    cor=scanner.nextInt();
                }while(cor!=1 && cor!=2 && cor!=3 && cor!=4);
                if(cor==1){
                    robo=new Robo(Texto.AZUL);
                }else if(cor==2){
                    robo=new Robo(Texto.AMARELO);
                }else if(cor==3){
                    robo=new Robo(Texto.VERDE);
                }else{
                    robo=new Robo(Texto.ROXO);
                }
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
                int movimento;
                Grid grid=new Grid();
                grid.mostrarGrid(robo, comida);
                do{
                    System.out.println("---Movimento do Robô---");
                    System.out.println("1) Up");
                    System.out.println("2) Down");
                    System.out.println("3) Right");
                    System.out.println("4) Left");
                    valido=false;
                    do{
                        do{
                            System.out.print("Digite uma opção válida: ");
                            movimento=scanner.nextInt();
                        }while(movimento!=1 && movimento!=2 && movimento!=3 && movimento!=4);
                        try{
                            robo.mover(movimento);
                            valido=true;
                        } catch (MovimentoInvalidoException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Tente Novamente");
                        } catch (ForaDoLimiteGridException e){
                            System.out.println(e.getMessage());
                            System.out.println("Tente Novamente");
                        }
                    }while(!valido);
                    grid.mostrarGrid(robo, comida);
                }while(!robo.encontrarAlimento(comida));
                System.out.println("Robô encontrou a comida!");
				System.out.println("Posição Jogador: "+"("+robo.getPosicaoX()+","+robo.getPosicaoY()+")");
    }
    
}
