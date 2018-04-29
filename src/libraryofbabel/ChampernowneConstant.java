package libraryofbabel;

import java.math.BigInteger;

/**
 *
 * @author Thomas
 */
public class ChampernowneConstant {
    
    private int BASE;
    private BigInteger BBASE;
    
    public class ChampernowneConstantResponse{
        
        public final BigInteger NUMBER;
        public final int POSITION_IN_NUMBER;
        public final char DECIMAL;
        public final String DECIMALS;
        
        public ChampernowneConstantResponse(BigInteger number, int position, char digit, String decimals){
            this.NUMBER=number;
            this.POSITION_IN_NUMBER=position;
            this.DECIMAL=digit;
            this.DECIMALS=decimals;
        }
    }
    
    public ChampernowneConstant(){
        this.BASE=10;
        this.BBASE=BigInteger.TEN;
    }
    
    public ChampernowneConstant(int base){
        this.BASE=base;
        this.BBASE=new BigInteger(String.valueOf(base));
    }
    
    public void setBase(int base){this.BASE=base;}
    public int getBase(){return this.BASE;}
    
    public ChampernowneConstantResponse getDecimal(BigInteger decimale){
        BigInteger encadrementDecimale=this.encadrement(decimale);
        BigInteger startCalcul=BigInteger.ZERO;
        BigInteger decount=encadrementDecimale.subtract(BigInteger.ONE);
        while(decount.compareTo(BigInteger.ZERO)>0){
            startCalcul=startCalcul.add(this.pow(this.BBASE, decount));
            decount=decount.subtract(BigInteger.ONE);
        }
        BigInteger number=decimale.add(startCalcul).divide(encadrementDecimale);
        int position_in_number=decimale.add(startCalcul).mod(encadrementDecimale).intValueExact();
        return new ChampernowneConstantResponse(number,position_in_number,number.toString(this.BASE).charAt(position_in_number),"");
    }
    
    public ChampernowneConstantResponse getDecimals(BigInteger decimale,int size){
        ChampernowneConstantResponse decimaleChampResponse=this.getDecimal(decimale);
        BigInteger numberChamp=decimaleChampResponse.NUMBER;
        String decimals=numberChamp.toString(this.BASE).substring(decimaleChampResponse.POSITION_IN_NUMBER);
        while(decimals.length()<size){
            numberChamp=numberChamp.add(BigInteger.ONE);
            decimals+=numberChamp.toString(this.BASE);
        }
        if(decimals.length()>size)decimals=decimals.substring(0, size);
        return new ChampernowneConstantResponse(decimaleChampResponse.NUMBER,decimaleChampResponse.POSITION_IN_NUMBER,decimaleChampResponse.DECIMAL,decimals);
    }
    
    public BigInteger getPosition(String chaine){
        boolean append=false;
        if(chaine.charAt(0)=='0'){
            chaine='1'+chaine;
            append=true;
        }
        BigInteger position=new BigInteger(chaine);
        if(chaine.length()==1)return position;
        BigInteger limit=this.giveLimit(chaine.length()-2);
        position=position.subtract(this.BBASE.pow(chaine.length()-1)).multiply(new BigInteger(String.valueOf(chaine.length()))).add(limit.add(BigInteger.ONE));
        if(append)position=position.add(BigInteger.ONE);
        return position;
    }
    
    private BigInteger giveLimit(int pas){
        BigInteger limit=this.BBASE.subtract(BigInteger.ONE);
        int base_below=limit.intValueExact();
        while(pas>0){
            limit=limit.add(new BigInteger(String.valueOf((pas+1)*base_below)).multiply(this.BBASE.pow(pas)));
            pas--;
        }
        return limit;
    }
    
    private BigInteger encadrement(BigInteger decimale){
        BigInteger base_below=this.BBASE.subtract(BigInteger.ONE);
        BigInteger step=this.BBASE.subtract(BigInteger.ONE);
        BigInteger result_return=BigInteger.ONE;
        BigInteger result_return_PLUS1_MUL_MUL_POW;
        while(decimale.compareTo(step)>0){
            result_return_PLUS1_MUL_MUL_POW=result_return.add(BigInteger.ONE).multiply(base_below).multiply(this.pow(this.BBASE, result_return));
            step=step.add(result_return_PLUS1_MUL_MUL_POW);
            result_return=result_return.add(BigInteger.ONE);
        }
        return result_return;
    }
    
    private BigInteger pow(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum()>0){
          if (exponent.testBit(0)) result = result.multiply(base);
          base = base.multiply(base);
          exponent = exponent.shiftRight(1);
        }
        return result;
    }
    
}
