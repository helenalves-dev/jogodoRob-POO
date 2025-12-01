package trabalhoPOO2NoTerminal;

import java.util.Scanner;

public class Main {
	public static void main(String args[]){
		boolean jogoAtivo = true;
		Scanner scanner = new Scanner(System.in);
		int opcao;
        do{
            System.out.println("---Menu Inicial---");
            System.out.println("1) Robô Controlado pelo Usuário");
			System.out.println("2) Robôs Automáticos");
			System.out.println("3) Robô Inteligente e Robô Burro");
            System.out.println("4) Robôs e Obstáculos");
            System.out.println("5) Sair");
            do{
                System.out.print("Digite uma opção válida: ");
                opcao=scanner.nextInt();
            }while(opcao!=1 && opcao!=2 && opcao!=3 && opcao!=4 && opcao!=5);
            ModosJogo modo = ModosJogo.escolherModo(opcao);
            if (modo != null) {
                modo.executarModo();
            }else{
                jogoAtivo=false;
            }
        }while(jogoAtivo);
        scanner.close();
	}
}
