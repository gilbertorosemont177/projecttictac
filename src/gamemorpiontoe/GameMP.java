/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemorpiontoe;

import java.util.List;
import java.util.ArrayList;

public class GameMP implements InterfaceGame{
int[] tabIndexGagnantes= new int [3]; // X in tab 
String grilleJeu[]=new String[9];
int best_index_for_O;
final  int [][] combinaisongagnante= new int[] []{ {0, 4, 8},{0, 1, 2},
        {0, 3, 6}, {6, 7, 8}, {6, 4, 2}, {3, 4, 5}, {1, 4, 7}, {2, 5, 8} };
    
    @Override
    public void initialise() {
        
          for (int i = 0; i < grilleJeu.length; i++) 
                grilleJeu[i]="";
    }
    @Override
    public void setX(int cellule) {
        if(grilleJeu[cellule].equals(""))
            grilleJeu[cellule]="X";
    }
    
    public List<Integer> Casesdisponibles(){
        List<Integer> liste= new ArrayList<>();
       
        for (int i = 0; i < grilleJeu.length; i++) {
               if(grilleJeu[i]=="")
                    liste.add(i);
        }
        return liste;
    }
    
    private void placerXouO(int index,String Jouee){
        if(grilleJeu[index]=="")
           grilleJeu[index]=Jouee;
    }
    public int MinimaxAlgorithme(int tours_deep, String JoueurVsMachine ){
    int min_value_X= Integer.MAX_VALUE;
    int max_value_O= Integer.MIN_VALUE;
    if(gagnant("O",tabIndexGagnantes))
        return 1;
    if(gagnant("X",tabIndexGagnantes))
        return -1;
    if(this.Casesdisponibles().isEmpty())
        return 0;  
    
     
    for (int i = 0; i < this.Casesdisponibles().size(); i++) {

       int  indexdisponible= this.Casesdisponibles().get(i);
       if("O".equals(JoueurVsMachine)){
           this.placerXouO(indexdisponible, JoueurVsMachine);
           int points_Max_X= MinimaxAlgorithme(tours_deep+1, "X");
        
            if(max_value_O<points_Max_X){
               max_value_O=points_Max_X;
               if(tours_deep==0){
                    if(max_value_O>=0)
                        best_index_for_O=indexdisponible;
               }
            }
            grilleJeu[indexdisponible]="";
        }
        else if ("X".equals(JoueurVsMachine)){
                this.placerXouO(indexdisponible, JoueurVsMachine);
                int points_Min_O=MinimaxAlgorithme(tours_deep+1, "O");
               
                if(min_value_X>points_Min_O)
                  min_value_X=points_Min_O;
               grilleJeu[indexdisponible]="";
        }
      }
     return ("O".equals(JoueurVsMachine))? max_value_O :min_value_X;
   }
   
    @Override
    public int getO() {
       int maxO=this.MinimaxAlgorithme(0, "O");
       grilleJeu[best_index_for_O]="O";
       return best_index_for_O ;
    }
    
    @Override
    public boolean gagnant(String joueur, int[] pos) {

     for (int[] combinaisongagnante1 : combinaisongagnante) {
         if (grilleJeu[combinaisongagnante1[0]].equals(grilleJeu[combinaisongagnante1[1]]) && grilleJeu[combinaisongagnante1[0]].equals(grilleJeu[combinaisongagnante1[2]]) && grilleJeu[combinaisongagnante1[0]].equals(joueur)) {
             if (pos.length>0)
                 System.arraycopy(combinaisongagnante1, 0, pos, 0, 3);
             return true;
         }
         if (grilleJeu[combinaisongagnante1[0]].equals(grilleJeu[combinaisongagnante1[1]]) && grilleJeu[combinaisongagnante1[0]].equals(grilleJeu[combinaisongagnante1[2]]) && grilleJeu[combinaisongagnante1[0]].equals(joueur)) {
             if (pos.length>0)
                 System.arraycopy(combinaisongagnante1, 0, pos, 0, 3);
             return true;
         }
     }
        return false;
    }
    @Override
    public boolean isPartieNulle() {     
        return (this.Casesdisponibles().size()<1);
    }
    @Override
    public void testDebug(int[] indicesCoups) {
        System.out.println("manque implementer");
    }   
}