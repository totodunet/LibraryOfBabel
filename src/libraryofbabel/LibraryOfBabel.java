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
        
        public BabelAdress(BigInteger area,BigInteger avenue,BigInteger building,BigInteger floor,BigInteger room,BigInteger bookcase,BigInteger shelf,BigInteger book,BigInteger page,BigInteger line,BigInteger character){
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
        
        @Override
        public String toString(){
            return "Adresse :\nZone : "+getWord(this.AREA)+"\nAvenue n°"+this.AVENUE+"\nBâtiment n°"+this.BUILDING+"\nEtage "+this.FLOOR+"\nSalle n°"+this.ROOM+"\nBibliothèque "+this.BOOKCASE+"\n"+this.SHELF+"ème étagère\nLivre "+this.BOOK+"\nPage n°"+this.PAGE+"\nLigne n°"+this.LINE+"\nCaractère "+this.CHARACTER;
        }
        
    }
    
    public LibraryOfBabel(){
        this.AVENUES=new BigInteger("1000000",10); //1 MILLION AVENUES PAR ZONE
        this.BUILDINGS=new BigInteger("1000000",10); //1 MILLION DE BATIMENTS PAR AVENUE
        this.FLOORS=new BigInteger("1000",10); //1000 ETAGES PAR BATIMENT
        this.ROOMS=new BigInteger("1000",10); //1000 SALLES PAR ETAGE
        this.BOOKCASES=new BigInteger("1000000",10); //1 MILLION DE BIBLIOTHEQUES PAR SALLE
        this.SHELVES=new BigInteger("1000",10); //1000 ETAGERES PAR BIBLIOTHEQUE
        this.BOOKS=new BigInteger("1000",10); //1000 LIVRES PAR ETAGERE
        this.PAGES=new BigInteger("410",10);
        this.LINES=new BigInteger("40",10);
        this.CHARACTERS=new BigInteger("80",10);
        this.CONSTANTE=new ChampernowneConstant();
        this.ENCODE=3;
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
    
    public BigInteger getPositionBigInteger(BigInteger area,BigInteger avenue,BigInteger building,BigInteger floor,BigInteger room,BigInteger bookcase,BigInteger shelf,BigInteger book,BigInteger page,BigInteger line,BigInteger character){
        return area.multiply(this.AVENUES)
            .add(avenue.subtract(BigInteger.ONE).multiply(this.AVENUES))
            .add(building.subtract(BigInteger.ONE).multiply(this.BUILDINGS))
            .add(floor.multiply(this.ROOMS))
            .add(room.subtract(BigInteger.ONE).multiply(this.BOOKCASES))
            .add(bookcase.subtract(BigInteger.ONE).multiply(this.SHELVES))
            .add(shelf.subtract(BigInteger.ONE).multiply(this.BOOKS))
            .add(book.subtract(BigInteger.ONE).multiply(this.PAGES))
            .add(page.subtract(BigInteger.ONE).multiply(this.LINES))
            .add(line.subtract(BigInteger.ONE).multiply(this.CHARACTERS))
            .add(character).multiply(new BigInteger(String.valueOf(this.ENCODE)));
    }
    
    public BigInteger getPositionBigInteger(BabelAdress adresse){
        BigInteger char_encode=this.CHARACTERS.multiply(new BigInteger(String.valueOf(this.ENCODE)));
        
        BigInteger char_area=this.AVENUES.multiply(this.BUILDINGS).multiply(this.FLOORS).multiply(this.ROOMS).multiply(this.BOOKCASES).multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_avenue=this.BUILDINGS.multiply(this.FLOORS).multiply(this.ROOMS).multiply(this.BOOKCASES).multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_building=this.FLOORS.multiply(this.ROOMS).multiply(this.BOOKCASES).multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_floor=this.ROOMS.multiply(this.BOOKCASES).multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_room=this.BOOKCASES.multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_bookcase=this.SHELVES.multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_shelf=this.BOOKS.multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_book=this.PAGES.multiply(this.LINES).multiply(char_encode);
        BigInteger char_page=this.LINES.multiply(char_encode);
        
        return adresse.AREA.multiply(char_area)
            .add(adresse.AVENUE.subtract(BigInteger.ONE).multiply(char_avenue))
            .add(adresse.BUILDING.subtract(BigInteger.ONE).multiply(char_building))
            .add(adresse.FLOOR.multiply(char_floor))
            .add(adresse.ROOM.subtract(BigInteger.ONE).multiply(char_room))
            .add(adresse.BOOKCASE.subtract(BigInteger.ONE).multiply(char_bookcase))
            .add(adresse.SHELF.subtract(BigInteger.ONE).multiply(char_shelf))
            .add(adresse.BOOK.subtract(BigInteger.ONE).multiply(char_book))
            .add(adresse.PAGE.subtract(BigInteger.ONE).multiply(char_page))
            .add(adresse.LINE.subtract(BigInteger.ONE).multiply(char_encode))
            .add(char_encode);
    }
    
    public BigInteger getPositionPageBigInteger(BabelAdress adresse){
        return this.getPositionBigInteger(adresse).divide(this.PAGES).add(BigInteger.ONE);
    }
    
    public BabelAdress getPositionBabelAdress(BigInteger position){
        BigInteger char_encode=new BigInteger(String.valueOf(this.ENCODE));
        
        BigInteger char_area=this.AVENUES.multiply(this.BUILDINGS).multiply(this.FLOORS).multiply(this.ROOMS).multiply(this.BOOKCASES).multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_avenue=this.BUILDINGS.multiply(this.FLOORS).multiply(this.ROOMS).multiply(this.BOOKCASES).multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_building=this.FLOORS.multiply(this.ROOMS).multiply(this.BOOKCASES).multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_floor=this.ROOMS.multiply(this.BOOKCASES).multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_room=this.BOOKCASES.multiply(this.SHELVES).multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_bookcase=this.SHELVES.multiply(this.BOOKS).multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_shelf=this.BOOKS.multiply(this.PAGES).multiply(this.LINES).multiply(char_encode);
        BigInteger char_book=this.PAGES.multiply(this.LINES).multiply(char_encode);
        BigInteger char_page=this.LINES.multiply(char_encode);
        BigInteger char_line=this.CHARACTERS.multiply(char_encode);
        
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
        
        BigInteger character=position.divide(char_encode);
        
        return new BabelAdress(area,avenue,building,floor,room,bookcase,shelf,book,page,line,character);
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
        return this.getPositionBabelAdress(this.CONSTANTE.getPosition(this.encodeWord(chaine)));
    }
    
    public String getPageContent(BabelAdress adress){
        ChampernowneConstant.ChampernowneConstantResponse response=this.CONSTANTE.getDecimals(this.getPositionPageBigInteger(adress),this.ENCODE*this.CHARACTERS.intValueExact()*this.LINES.intValueExact()-1);
        return response.DECIMALS;
    }
    
}
