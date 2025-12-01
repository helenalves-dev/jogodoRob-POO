package trabalhoPOO2NoTerminal;

import java.util.ArrayList;

public abstract class Obstaculo{
   private static ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
   private int id;
   private int posicaoX;
   private int posicaoY;

   protected Obstaculo(int id, int indiceX, int indiceY) throws ForaDoLimiteGridException{
        this.id=id;
        if (indiceX <= 4 && indiceY <= 4 && indiceX >=0 && indiceY >= 0) {
         this.posicaoX = indiceX;
         this.posicaoY = indiceY;
         obstaculos.add(this);
      } else {
         throw new ForaDoLimiteGridException();
      }
    }

   public int getId(){
        return id;
    }

   public void setId(int id){
      this.id=id;
    }

   public int getPosicaoX() {
      return this.posicaoX;
   }

   public void setPosicaoX(int var1) {
      this.posicaoX = var1;
   }

   public int getPosicaoY() {
      return this.posicaoY;
   }

   public void setPosicaoY(int var1) {
      this.posicaoY = var1;
   }

   public abstract void bater(Robo robo);
   
   public static void removerObstaculo(Obstaculo o) {
      obstaculos.remove(o);
   }

   public static ArrayList<Obstaculo> getObstaculos() {
       return obstaculos;
   }

   public static Obstaculo procurarObstaculo(int x, int y) {
      for (Obstaculo o : obstaculos) {
         if (o.getPosicaoX() == x && o.getPosicaoY() == y) {
            return o;
         }
      }
      return null;

   }
}