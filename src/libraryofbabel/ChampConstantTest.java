/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryofbabel;

import java.math.BigInteger;

/**
 *
 * @author Thomas
 */
public class ChampConstantTest {
    
    public static void main(String[] args){
        LibraryOfBabel babel=new LibraryOfBabel();
        LibraryOfBabel.BabelAdress adresse=babel.getPosition("Une phrase au hasard");
        System.out.println(babel.getPositionBigInteger(adresse));
        System.out.println(adresse);
        System.out.println(babel.getPageContent(adresse));
    }
    
}
