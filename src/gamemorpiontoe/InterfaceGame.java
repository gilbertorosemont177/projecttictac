
package gamemorpiontoe;





public interface InterfaceGame {

   
   public void initialise();
   

   
   public void setX( int cellule);

   
   public int getO();

   
   public boolean gagnant(String joueur, int[] pos );

   
   public boolean isPartieNulle();

   public void testDebug(int[] indicesCoups);
}