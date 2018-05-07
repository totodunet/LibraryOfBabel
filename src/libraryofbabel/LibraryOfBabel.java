package libraryofbabel;

import java.math.BigInteger;

/**
 *
 * @author Thomas
 */
public class LibraryOfBabel {

    //PARAMETERS
    private BigInteger AVENUES;
    private BigInteger BUILDINGS;
    private BigInteger FLOORS;
    private BigInteger ROOMS;
    private BigInteger BOOKCASES;
    private BigInteger SHELVES;
    private BigInteger BOOKS;
    private BigInteger PAGES;
    private BigInteger LINES;
    private BigInteger CHARACTERS;
    
    private ChampernowneConstant CONSTANTE;
    private short ENCODE;
    
    public class BabelAdress{
        
        public final BigInteger DIMENSION;
        public final BigInteger AREA;
        public final BigInteger AVENUE;
        public final BigInteger BUILDING;
        public final BigInteger FLOOR;
        public final BigInteger ROOM;
        public final BigInteger BOOKCASE;
        public final BigInteger SHELF;
        public final BigInteger BOOK;
        public final BigInteger PAGE;
        public final BigInteger LINE;
        public final BigInteger CHARACTER;
        
        public BabelAdress(BigInteger dimension,BigInteger area,BigInteger avenue,BigInteger building,BigInteger floor,BigInteger room,BigInteger bookcase,BigInteger shelf,BigInteger book,BigInteger page,BigInteger line,BigInteger character){
            this.DIMENSION=dimension;
            this.AREA=area;
            this.AVENUE=avenue;
            this.BUILDING=building;
            this.FLOOR=floor;
            this.ROOM=room;
            this.BOOKCASE=bookcase;
            this.SHELF=shelf;
            this.BOOK=book;
            this.PAGE=page;
            this.LINE=line;
            this.CHARACTER=character;
        }
        
        public BabelAdress(String dimension,String area,String avenue,String building,String floor,String room,String bookcase,String shelf,String book,String page,String line,String character){
            this.DIMENSION=new BigInteger(dimension);
            this.AREA=new BigInteger(area);
            this.AVENUE=new BigInteger(avenue);
            this.BUILDING=new BigInteger(building);
            this.FLOOR=new BigInteger(floor);
            this.ROOM=new BigInteger(room);
            this.BOOKCASE=new BigInteger(bookcase);
            this.SHELF=new BigInteger(shelf);
            this.BOOK=new BigInteger(book);
            this.PAGE=new BigInteger(page);
            this.LINE=new BigInteger(line);
            this.CHARACTER=new BigInteger(character);
        }
        
        @Override
        public String toString(){
            return "{"
                + "\"dimension\":"+this.DIMENSION+","
                + "\"area\":"+this.AREA+","
                + "\"avenue\":"+this.AVENUE+","
                + "\"building\":"+this.BUILDING+","
                + "\"floor\":"+this.FLOOR+","
                + "\"room\":"+this.ROOM+","
                + "\"bookcase\":"+this.BOOKCASE+","
                + "\"shelf\":"+this.SHELF+","
                + "\"book\":"+this.BOOK+","
                + "\"page\":"+this.PAGE+","
                + "\"line\":"+this.LINE+","
                + "\"character\":"+this.CHARACTER+","
                + "\"startPositionAbsolute\":\""+getPositionBigInteger(this)+"\","
                + "\"startPositionPage\":\""+getPositionPageBigInteger(this)+"\","
                + "\"startPositionBook\":\""+getPositionBookBigInteger(this)+"\","
                + "\"pageContent\":\""+getPageContent(this)+"\""
                + "}";
        }
    }
    
    public LibraryOfBabel(){
        this.AVENUES=new BigInteger("1000000",10); //1 MILLION D'AVENUES PAR ZONE
        this.BUILDINGS=new BigInteger("1000000",10); //1 MILLION DE BATIMENTS PAR AVENUE
        this.FLOORS=new BigInteger("1000",10); //1000 ETAGES PAR BATIMENT
        this.ROOMS=new BigInteger("1000",10); //1000 SALLES PAR ETAGE
        this.BOOKCASES=new BigInteger("1000000",10); //1 MILLION DE BIBLIOTHEQUES PAR SALLE
        this.SHELVES=new BigInteger("1000",10); //1000 ETAGERES PAR BIBLIOTHEQUE
        this.BOOKS=new BigInteger("1000",10); //1000 LIVRES PAR ETAGERE
        this.PAGES=new BigInteger("410",10); //410 PAGES PAR LIVRE
        this.LINES=new BigInteger("40",10); //40 LIGNES PAR PAGE
        this.CHARACTERS=new BigInteger("80",10); //80 CARACTERES PAR LIGNE
        this.CONSTANTE=new ChampernowneConstant(); //SUITE UNIVERS UTILISEE
        this.ENCODE=3; //NOMBRE DE DIMENSIONS
    }
    
    public void setParameters(String avenues,String buildings,String floors,String rooms,String bookcases,String shelves,String books,String pages,String lines,String characters){
        this.AVENUES=new BigInteger(avenues,this.CONSTANTE.getBase());
        this.BUILDINGS=new BigInteger(buildings,this.CONSTANTE.getBase());
        this.FLOORS=new BigInteger(floors,this.CONSTANTE.getBase());
        this.ROOMS=new BigInteger(rooms,this.CONSTANTE.getBase());
        this.BOOKCASES=new BigInteger(bookcases,this.CONSTANTE.getBase());
        this.SHELVES=new BigInteger(shelves,this.CONSTANTE.getBase());
        this.BOOKS=new BigInteger(books,this.CONSTANTE.getBase());
        this.PAGES=new BigInteger(pages,this.CONSTANTE.getBase());
        this.LINES=new BigInteger(lines,this.CONSTANTE.getBase());
        this.CHARACTERS=new BigInteger(characters,this.CONSTANTE.getBase());
    }
    
    public int getBase(){return this.CONSTANTE.getBase();}
    public short getEncode(){return this.ENCODE;}
    
    public void changeBase(int base){
        this.CONSTANTE=new ChampernowneConstant(base);
        this.setParameters(this.AVENUES.toString(base), this.BUILDINGS.toString(base), this.FLOORS.toString(base), this.ROOMS.toString(base), this.BOOKCASES.toString(base), this.SHELVES.toString(base), this.BOOKS.toString(base), this.PAGES.toString(base), this.LINES.toString(base), this.CHARACTERS.toString(base));
    }
    
    public void setEncode(short encode){this.ENCODE=encode;}
    
    public BigInteger getPositionBigInteger(BigInteger dimension, BigInteger area,BigInteger avenue,BigInteger building,BigInteger floor,BigInteger room,BigInteger bookcase,BigInteger shelf,BigInteger book,BigInteger page,BigInteger line,BigInteger character){
        BigInteger char_encode=new BigInteger(String.valueOf(this.ENCODE));
        BigInteger char_line=this.CHARACTERS.multiply(char_encode);
        BigInteger char_page=this.LINES.multiply(char_line);
        BigInteger char_book=this.PAGES.multiply(char_page);
        BigInteger char_shelf=this.BOOKS.multiply(char_book);
        BigInteger char_bookcase=this.SHELVES.multiply(char_shelf);
        BigInteger char_room=this.BOOKCASES.multiply(char_bookcase);
        BigInteger char_floor=this.ROOMS.multiply(char_room);
        BigInteger char_building=this.FLOORS.multiply(char_floor);
        BigInteger char_avenue=this.BUILDINGS.multiply(char_building);
        BigInteger char_area=this.AVENUES.multiply(char_avenue);
        
        return area.multiply(char_area)
            .add(avenue.subtract(BigInteger.ONE).multiply(char_avenue))
            .add(building.subtract(BigInteger.ONE).multiply(char_building))
            .add(floor.multiply(char_floor))
            .add(room.subtract(BigInteger.ONE).multiply(char_room))
            .add(bookcase.subtract(BigInteger.ONE).multiply(char_bookcase))
            .add(shelf.subtract(BigInteger.ONE).multiply(char_shelf))
            .add(book.subtract(BigInteger.ONE).multiply(char_book))
            .add(page.subtract(BigInteger.ONE).multiply(char_page))
            .add(line.subtract(BigInteger.ONE).multiply(char_line))
            .add(character.subtract(BigInteger.ONE).multiply(char_encode))
            .add(dimension);
    }
    
    public BigInteger getPositionBigInteger(BabelAdress adresse){
        return this.getPositionBigInteger(adresse.DIMENSION,
            adresse.AREA, 
            adresse.AVENUE, 
            adresse.BUILDING,
            adresse.FLOOR,
            adresse.ROOM,
            adresse.BOOKCASE, 
            adresse.SHELF, 
            adresse.BOOK, 
            adresse.PAGE, 
            adresse.LINE, 
            adresse.CHARACTER);
    }
    
    public BigInteger getPositionPageBigInteger(BabelAdress adresse){
        BigInteger char_encode=new BigInteger(String.valueOf(this.ENCODE));
        BigInteger char_line=this.CHARACTERS.multiply(char_encode);
        BigInteger char_page=this.LINES.multiply(char_line);
        
        BigInteger adress=this.getPositionBigInteger(adresse);
        return adress.subtract(adress.mod(char_page)).add(adresse.DIMENSION);
    }
    
    public BigInteger getPositionBookBigInteger(BabelAdress adresse){
        BigInteger char_encode=new BigInteger(String.valueOf(this.ENCODE));
        BigInteger char_line=this.CHARACTERS.multiply(char_encode);
        BigInteger char_page=this.LINES.multiply(char_line);
        BigInteger char_book=this.LINES.multiply(char_page);
        
        BigInteger adress=this.getPositionBigInteger(adresse);
        return adress.subtract(adress.mod(char_book)).add(adresse.DIMENSION);
    }
    
    public BabelAdress getPositionBabelAdress(BigInteger position){
        BigInteger char_encode=new BigInteger(String.valueOf(this.ENCODE));
        BigInteger char_line=this.CHARACTERS.multiply(char_encode);
        BigInteger char_page=this.LINES.multiply(char_line);
        BigInteger char_book=this.PAGES.multiply(char_page);
        BigInteger char_shelf=this.BOOKS.multiply(char_book);
        BigInteger char_bookcase=this.SHELVES.multiply(char_shelf);
        BigInteger char_room=this.BOOKCASES.multiply(char_bookcase);
        BigInteger char_floor=this.ROOMS.multiply(char_room);
        BigInteger char_building=this.FLOORS.multiply(char_floor);
        BigInteger char_avenue=this.BUILDINGS.multiply(char_building);
        BigInteger char_area=this.AVENUES.multiply(char_avenue);
        BigInteger dimension=position.mod(char_encode);
        
        BigInteger area=position.divide(char_area);
        position=position.mod(char_area);
        
        BigInteger avenue=position.divide(char_avenue).add(BigInteger.ONE);
        position=position.mod(char_avenue);
        
        BigInteger building=position.divide(char_building).add(BigInteger.ONE);
        position=position.mod(char_building);
        
        BigInteger floor=position.divide(char_floor);
        position=position.mod(char_floor);
        
        BigInteger room=position.divide(char_room).add(BigInteger.ONE);
        position=position.mod(char_room);
        
        BigInteger bookcase=position.divide(char_bookcase).add(BigInteger.ONE);
        position=position.mod(char_bookcase);
        
        BigInteger shelf=position.divide(char_shelf).add(BigInteger.ONE);
        position=position.mod(char_shelf);
        
        BigInteger book=position.divide(char_book).add(BigInteger.ONE);
        position=position.mod(char_book);
        
        BigInteger page=position.divide(char_page).add(BigInteger.ONE);
        position=position.mod(char_page);
        
        BigInteger line=position.divide(char_line).add(BigInteger.ONE);
        position=position.mod(char_line);
        
        BigInteger character=position.divide(char_encode).add(new BigInteger(String.valueOf(dimension)));
        
        return new BabelAdress(dimension,area,avenue,building,floor,room,bookcase,shelf,book,page,line,character);
    }
    
    public String getWord(BigInteger bigNumber){
        String chaine=bigNumber.toString(this.CONSTANTE.getBase());
        String result="";
        for(int i=0;i<chaine.length();i+=this.ENCODE)
            if(i+this.ENCODE>chaine.length())result+=(char)Long.parseLong(chaine.substring(i),this.CONSTANTE.getBase());
            else result+=(char)Long.parseLong(chaine.substring(i,i+this.ENCODE),this.CONSTANTE.getBase());
        return result;
    }
    
    public String getWord(String serie){
        String chaine=serie;
        String result="";
        int i;
        for(i=0;i<chaine.length();i+=this.ENCODE)
            if(i+this.ENCODE>chaine.length())result+=(char)Long.parseLong(chaine.substring(i),this.CONSTANTE.getBase());
            else result+=(char)Long.parseLong(chaine.substring(i,i+this.ENCODE),this.CONSTANTE.getBase());
        return result;
    }
    
    public String encodeWord(String chaine){
        String result="";
        String str_code;
        for(int i=0;i<chaine.length();i++){
            str_code=String.valueOf(chaine.codePointAt(i));
            while(str_code.length()<this.ENCODE)str_code='0'+str_code;
            result+=str_code;
        }
        return result;
    }
    
    public BabelAdress getPosition(String chaine){
        return this.getPositionBabelAdress(this.CONSTANTE.getPosition(chaine));
    }
    
    public String getPageContent(BabelAdress adress){
        ChampernowneConstant.ChampernowneConstantResponse response=this.CONSTANTE.getDecimals(this.getPositionPageBigInteger(adress),this.ENCODE*this.CHARACTERS.intValueExact()*this.LINES.intValueExact()-(this.ENCODE-1));
        return response.DECIMAL+response.DECIMALS;
    }   
}