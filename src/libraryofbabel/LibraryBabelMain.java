/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryofbabel;

/**
 *
 * @author tributsch
 */
public class LibraryBabelMain {
    
    public static void main(String[] args){
        
        //Erreur nombre de parametres
        if(args.length<2)
            System.out.println("Error : Missing parameters");
        
        else{
            LibraryOfBabel library=new LibraryOfBabel();
            
            //rechercher
            if(args[0].equals("search")){
                if(args[1].equals("-d"))System.out.println(library.getPosition(args[3],args[2]));
                else System.out.println(library.getPosition(args[1]));
            }
            //parcourir
            else if(args[0].equals("move")){
                LibraryOfBabel.BabelAdress adresse=library.new BabelAdress(args[1],
                    args[2],
                    args[3],
                    args[4],
                    args[5],
                    args[6],
                    args[7],
                    args[8],
                    args[9],
                    args[10],
                    args[11],
                    args[12]);
                System.out.println(adresse);
            }
            else{
                System.out.println("Error : Bad parameters");
            }
        }
    }  
}
