package net.graph;



import java.util.Stack;
import java.util.StringTokenizer;


// Evaluator class interface: evaluate infix expressions
//
// CONSTRUCTION: with a String
//
// ******************PUBLIC OPERATIONS***********************
// float getValue( )      --> Return value of infix expression
// ******************ERRORS**********************************
// Some error checking is performed


public class Evaluator
{
    private static String result="";
    private static final int EOL     = 0;
    private static final int VALUE   = 1;
    private static final int OPAREN  = 2;
    private static final int CPAREN  = 3;
    private static final int EXP     = 4;
    private static final int MULT    = 5;
    private static final int DIV     = 6;
    private static final int PLUS    = 7;
    private static final int MINUS   = 8;
    private static final int SIN     = 9;
    private static final int COS     = 10;
    private static final int TAN     = 11;
    private static final int CTAN    = 12;
    private static final int PI    = 13;
    private static final int E    = 14;
    private static final int LN   = 15;
    private static final int LG   = 16;
    private static final int LB   = 17;
    private static final int FLOOR   = 18;
    private static final int FRAC   = 19;
    private static final int ABS   = 20;
    private static final int SQRT   = 21;
    
    
    private static class Precedence
    {
        public int inputSymbol;
        public int topOfStack;

        public Precedence( int inSymbol, int topSymbol )
        {
            inputSymbol = inSymbol;
            topOfStack  = topSymbol;
        }
    }

        // PrecTable matches order of Token enumeration
    private static Precedence [ ] precTable = new Precedence[ ]
    {
    	new Precedence(   0, -1 ),  // EOL
        new Precedence(   0,  0 ),  // VALUE
        new Precedence( 100,  0 ),  // OPAREN
        new Precedence(   1, 98 ),  // CPAREN
        new Precedence(   20,  16 ),  // EXP
        new Precedence(   12,  15 ),  // MULT
        new Precedence(   12,  14 ),  // DIV
        new Precedence(   5,  6 ),  // PLUS
        new Precedence(   5,  6 ),  // MINUS
        new Precedence(   60,  97 ), // SIN
        new Precedence(   60,  97 ), // COS
        new Precedence(   60,  97 ), // TAN
        new Precedence(   60,  97 ), // CTAN
        new Precedence(   15,  17 ), // PI
        new Precedence(   15,  17 ), // E
        new Precedence(   60,  97 ), // LN
        new Precedence(   60,  97 ), // LG
        new Precedence(   60,  97 ), // LB
        new Precedence(   60,  97 ), // FLOOR
        new Precedence(   60,  97 ), // FRAC
        new Precedence(   65,  97 ), // ABS
        new Precedence(   20,  16 ), // SQRT
        
    };

    private static class Token
    {
        public Token( )
        {
            this( EOL );
        }
        
        public Token( int t )
        {
            this( t, 0 );
        }
        
        public Token( int t, float v )
        {
            type = t;
            value = v;
        }
        
        public int getType( )
        {
            return type;
        }
        
        public float getValue( )
        {
            return value;
        }
        
        private int type = EOL;
        private float value = 0;
    }    

    private static class EvalTokenizer
    {
        public EvalTokenizer( StringTokenizer is )
        {
            str = is;
        }
        
        /**
         * Find the next token, skipping blanks, and return it.
         * For VALUE token, place the processed value in currentValue.
         * Print error message if input is unrecognized.
         */
        public Token getToken( )
        {
            float theValue;           

            if( !str.hasMoreTokens( ) )
                return new Token( );

            String s = str.nextToken( );
            if( s.equals( " " ) ) return getToken( );
            if( s.equals( "^" ) ) return new Token( EXP );
            if( s.equals( "/" ) ) return new Token( DIV );
            if( s.equals( "*" ) ) return new Token( MULT );
            if( s.equals( "(" ) ) return new Token( OPAREN );
            if( s.equals( ")" ) ) return new Token( CPAREN );
            if( s.equals( "+" ) ) return new Token( PLUS );
            if( s.equals( "-" ) ) return new Token( MINUS );
            if( s.equals( "sin" ) ) return new Token( SIN );
            if( s.equals( "cos" ) ) return new Token( COS );
            if( s.equals( "tan" ) ) return new Token( TAN );
            if( s.equals( "ctan" ) ) return new Token( CTAN );
            if( s.equals( "\u03C0" ) ) return new Token( PI );
            if( s.equals( "e" ) ) return new Token( E );
            if( s.equals( "ln" ) ) return new Token( LN );
            if( s.equals( "lg" ) ) return new Token( LG );
            if( s.equals( "lb" ) ) return new Token( LB );
            if( s.equals( "floor" ) ) return new Token( FLOOR );
            if( s.equals( "frac" ) ) return new Token( FRAC );
            if( s.equals( "abs" ) ) return new Token( ABS );
            if( s.equals( "sqrt" ) ) return new Token( SQRT );
            
            

            try
              { theValue = Float.parseFloat( s ); }
            catch( NumberFormatException e )
            {
                result= "Parse error" ;
                return new Token( );
            }
            
            return new Token( VALUE, theValue );
        }
        
        private StringTokenizer str;
        
    }
    
    /**
     * Construct an evaluator object.
     * @param s the string containing the expression.
     */
    public Evaluator( String s )
    {
        opStack = new Stack<Integer>( );
        postfixStack = new Stack<Float>( );

        str = new StringTokenizer( s, "+-*/^() ", true );

        opStack.push( EOL );
    }

        // The only publicly visible routine

    /** 
     * Public routine that performs the evaluation.
     * Examine the  postfix machine to see if a single result is
     * left and if so, return it; otherwise print error.
     * @return the result.
     */
    public float getValue( )
    {
        EvalTokenizer tok = new EvalTokenizer( str );
        Token lastToken;
        
        do
        {
            lastToken = tok.getToken( );
            processToken( lastToken );
        } while( lastToken.getType( ) != EOL );

        if( postfixStack.isEmpty( ) )
        {
            result= "Error!" ;
            return 0;
        }

        float theResult = postfixStack.pop( );
        if( !postfixStack.isEmpty( ) )
            result= "Warning: missing operators!" ;

        return (float) (Math.round(theResult * 10000.0) / 10000.0);
    }


    private Stack<Integer> opStack;       // Operator stack for conversion
    private Stack<Float>    postfixStack;  // Stack for postfix machine
    private StringTokenizer str; // StringTokenizer stream


    /**
     * After a token is read, use operator precedence parsing
     * algorithm to process it; missing opening parentheses
     * are detected here.
     */
    private void processToken( Token lastToken )
    {
        int topOp;
        int lastType = lastToken.getType( );
      
   
        switch( lastType )
        {
          case VALUE:
              float f= lastToken.getValue( );
              //System.out.println("Push"+f);
              
            postfixStack.push(f);
            return;

          case CPAREN:
            while( ( topOp = opStack.peek( ) ) != OPAREN && topOp != EOL )
                binaryOp( topOp );
            if( topOp == OPAREN )
                opStack.pop( );  // Get rid of opening parentheseis
            else
                result= "Missing open parenthesis" ;
            break;

          default:    // General operator case
            while( precTable[ lastType ].inputSymbol <=
                   precTable[ topOp = opStack.peek( ) ].topOfStack )
                binaryOp( topOp );
            if( lastType != EOL )
                opStack.push( lastType );
            break;
        }
    }

    /*
     * topAndPop the postfix machine stack; return the result.
     * If the stack is empty, print an error message.
     */
    

    /**
     * Internal routine to compute x^n.
     */
    private static float pow( float x, float n )
    {
        if( x == 0 )
        {
            if( n == 0 )
                result= "0^0 is undefined" ;
            return 0;
        }
        return (float) Math.pow(x,n);
    }

    /**
     * Process an operator by taking two items off the postfix
     * stack, applying the operator, and pushing the result.
     * Print error if missing closing parenthesis or division by 0.
     */
    private float abs (double nr)
    {
        return (float) (nr>0 ? nr:-nr);
    }
    private float postfixPop( )
    {
        if ( postfixStack.isEmpty( ) )
        {
            //result= "Missing operand" ;
            return Float.NaN;
        }
        return postfixStack.pop( );
    }
    private void binaryOp( int topOp )
    {
        if( topOp == OPAREN )
        {
            result= "Unbalanced parentheses";
            opStack.pop( );
            return;
        }
        
        float rhs;
        float lhs;
        
        
        if( topOp == EXP )
        {
            rhs = postfixPop( );
            lhs = postfixPop( );
            postfixStack.push( pow( lhs, rhs ) );
        }
        else if( topOp == PLUS )
        {
            rhs = postfixPop( );
            lhs = postfixPop( );
            postfixStack.push( lhs + rhs );
        }
        else if( topOp == MINUS )
        {
            rhs = postfixPop( );
            lhs = postfixPop( );
            postfixStack.push( lhs - rhs );
        }
        else if( topOp == SIN )
        {
            rhs = postfixPop( );
            postfixStack.push((float) Math.sin(rhs ) );
        }
        else if( topOp == SQRT )
        {
            rhs = postfixPop( );
            postfixStack.push((float) Math.sqrt(rhs ) );
        }
        else if( topOp == COS )
        {
            rhs = postfixPop( );
            postfixStack.push((float) Math.cos(rhs) );
        }
        else if( topOp == TAN )
        {
            rhs = postfixPop( );
            postfixStack.push((float) tangent(rhs ) );
        }
        else if( topOp == CTAN )
        {
            rhs = postfixPop( );
            postfixStack.push((float) cotangent(rhs));
        }
        else if( topOp == PI )
        {
            postfixStack.push((float) Math.PI);
        }
        else if( topOp == E )
        {
            postfixStack.push((float) Math.E);
        }
        else if( topOp == LN )
        {
            rhs = postfixPop( );
            postfixStack.push((float) (logaritm(Math.log(rhs))));
        }
        else if( topOp == LG )
        {
            rhs = postfixPop( );
            postfixStack.push((float) (logaritm(Math.log10(rhs))));
        }
        else if( topOp == LB )
        {
            rhs = postfixPop( );
            postfixStack.push((float) (logaritm(Math.log(rhs)/Math.log(2))));
        }
        else if( topOp == FLOOR )
        {
            rhs = postfixPop( );
            postfixStack.push((float) (Math.floor(rhs)));
        }
        else if( topOp == FRAC )
        {
            rhs = postfixPop( );
            postfixStack.push((float)(rhs-Math.floor(rhs)));
        }
        else if( topOp == ABS )
        {
            rhs = postfixPop( );
            postfixStack.push(abs(rhs));
        }
        else if( topOp == MULT )
        {
            rhs = postfixPop( );
            lhs = postfixPop( );
            postfixStack.push( lhs * rhs );
        }
        else if( topOp == DIV )
        {
            rhs = postfixPop( );
            lhs = postfixPop( );
            if( rhs != 0 )
                postfixStack.push( lhs / rhs );
            else
            {
                result =  "Division by zero" ;
                postfixStack.push( lhs );
            }
        }
        postfixStack.remove(Float.NaN);
        opStack.pop( );
    }

    /**
     * Simple main to exercise Evaluator class.
     */
    public static boolean isInteger(String str) 
    {
        return str.matches("^-?[0-9]+(\\.[0-9]+)?$");
    }
    public static boolean isWrong (double x)
    {
        return x<-1E6 || x>1E6;
    }
    public static double logaritm (double x)
    {
        if(x<-1E13 || x>1E13)
        {
            result = "Undefined!";
            return 1;
        }
        return x;
    }
    
    public static double tangent(double x)
    {
        if( isWrong(Math.tan(x)))
        {
            result = "Undefined!";
            return 1;
        }
        return Math.tan(x);
    }
    public static double cotangent(double x)
    {
        if( 1/Math.tan(x)<-1E13 || 1/Math.tan(x)>1E13)
        {
            result = "Undefined!";
            return 1;
        }
        return 1/Math.tan(x);
    }
       
    
    
    public static String change (String text)
    {
        if(!text.contains("-"))
        {
            return text;
        }
        else
        {
            text=text.replaceAll("-", " - ");
            text=text.replaceAll("\\s+", " ");
            String [] words = text.split(" ");
            for(int i=0;i<words.length;i++)
            {
                if(     (words[i].compareTo("-")==0 && (i==0 || i==1))  || 
                        (words[i].compareTo("-")==0 && words[i-1].contains("(") )
                        )
                {
                    
                    
                    words= add(words,i," 0 ");i++;
                }
            }
            text="";
            for(int i=0;i<words.length;i++)
            {
                text+=words[i]+" ";
            }
            return text;
        }
    }
    public static final String[] add(String[] myArray, int pos, String n)
    {
        String [] aux =  new String [myArray.length+1];
        for(int i=0;i<pos;i++)
        {
            aux[i]=myArray[i];
        }
        aux[pos]=n;
        for(int i=pos+1;i<aux.length;i++)
        {
            aux[i]=myArray[i-1];
        }
        return aux;
    }
	
    
    public static String evaluate (String str)    
    {
        String res;
        result="";
        //str = str.replaceAll("\u03C0", " "++" ");
        //str = str.replaceAll("e", " "+ Math.E+" ");
        str = " "+ str;
        Evaluator ev = new Evaluator( change(str) );
        double d =  ev.getValue( ) ;
        if(d==(int)d)
        {
            res = (int)d +"";
        }
        else
        {
            res = d +"";
        }
        if(result.compareTo("")!=0)
        {
            return result;
        }
        return res;
    }
   
   

}

