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
        System.out.println(babel.getPositionBigInteger(BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ONE,new BigInteger("6"),new BigInteger("10")));
        LibraryOfBabel.BabelAdress adresse=babel.getPositionBabelAdress(new BigInteger("6"));
        System.out.println(adresse);
        System.out.println(babel.getPageContent(adresse));
    }
    
}
