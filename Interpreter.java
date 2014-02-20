import java.util.*;
import java.io.*;

public class Interpreter {

    // Global variables
    private static Scanner          in;
    private static byte []          array;
    private static char[]           code;
    private static int              dp, cp;
    private static Stack <Integer>  stack;
    private static int              SIZE_ARRAY = 30000;

    public static void main ( String [] args ) throws Exception {
        array   = new byte [ SIZE_ARRAY ];
        Arrays.fill ( array, (byte) 0); 
        dp      = 0;
        stack   = new Stack<Integer>(); 

        in  = new Scanner ( System.in );
        System.out.println ( "Introduce el nombre del fichero donde esta el codigo Brainfuck" );

        // Hola Mundo
        code            = 
            "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.".toCharArray();
            
        System.out.println( "Empieza el programa: \n_________________________________________\n\n" );

        for ( cp=0; cp<code.length; cp++ ) {
              switch ( code [ cp ] ) 
              {
                  case '<' :  before();
                      break;
                  case '>' :  next();
                      break;
                  case '+' :  add();
                      break;
                  case '-' :  sub();
                      break;
                  case '.' :  print();
                      break;
                  case ',' :  read();
                      break;
                  case '[' :  open();
                      break;
                  case ']' :  close(); 
                      break;   
              }
        }
        System.out.println ( "\n\n_________________________________________\nFin del programa" );
    }

    private static void before() {
        if ( dp > 0 )
            dp--;
    }

    private static void next() {
        if ( dp < SIZE_ARRAY )
            dp++;
    }

    private static void add() {
        array [ dp ] = (byte) ( array [ dp ] + 1 );
    }

    private static void sub() {
        array [ dp ] = (byte) ( array [ dp ] - 1 );
    }

    private static void print() throws Exception {
        System.out.print( "" + (char) (array[dp]) );
    }

    private static void read () {
        array [ dp ] =  (byte) in.next().charAt(0) ;
    }

    private static void open () {
            for ( int numOpens = 1, numClosed = 0; numOpens == numClosed; cp++ ) {
                if ( code [ cp ] == '[' )
                    numOpens ++;
                if ( code [ cp ] == ']' )
                    numClosed ++;
            }
            cp++;
        }
        else {
            stack.push ( cp );
        }
    }

    private static void close () {
        if ( !stack.isEmpty() ) { 
            int aux = stack.pop();
            cp = aux-1;
        }
    }
}
