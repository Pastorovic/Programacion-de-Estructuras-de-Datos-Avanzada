package predaPED2;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.ArrayList;
/**
 * Se encarga de leer un archivo y verificar que la entrada es correcta
 * 
 * @author David Pastor Sanz
 */
public abstract class Reader
{   
    /**
     * Devuelve un array de números binomiales, si estos han sido 
     * correctamnete leídos del archivo de entrada. Comprueba si n es mayor
     * que k y que ambos sean números mayores que 0
     * @return El array de números binomiales.
     */
    public static int[][] getBinomialList( String file ) throws FileNotFoundException, IOException, IllegalStateException, NumberFormatException
    {
    	File rFile = new File( file );
        int[][] table;
        try
        {
	        if( rFile.exists() )
	        {
	            try
	            {
	                FileReader fileReader = new FileReader( rFile );
	                BufferedReader bufferedReader = new BufferedReader( fileReader );
	                ArrayList<String[]> numbers = new ArrayList<String[]>();
	                String next;
	                while( bufferedReader.ready() )
	                {
	                	next = bufferedReader.readLine();
	                	numbers = checkLine( next, numbers );
	                }
	                bufferedReader.close(); 
	                table = new int[numbers.size()][2];
	                for( int i = 0; i < numbers.size(); i++ )
	                	table = addLine( numbers.get( i ), table, i );
	            }
	            catch( NumberFormatException e )
	            {
	                throw( e );
	            }
	        }
	        else throw new FileNotFoundException();
        }
        catch( FileNotFoundException ef )
        {
        	throw new FileNotFoundException( "--Fichero no encontrado--" );
        }
        return table;
    }
    
    private static ArrayList<String[]> checkLine( String line, ArrayList<String[]> table ) throws IllegalStateException
    {
    	String[] split = line.split( " " );
    	if( split.length != 2 ) throw new IllegalStateException( "-Cada número combinatorio deben ser dos números enteros naturales-" );
    	table.add( split );
    	return table;
    }
    
    private static int[][] addLine( String[] next, int[][] table, int index )  throws IOException, NumberFormatException
    {
    	int n, k;
    	try
    	{
			n = Integer.parseInt( next[0] );
			k = Integer.parseInt( next[1] );
			if( n < k )
				throw new IllegalStateException( "-El primer dato del binomio debe ser mayor que el segundo-" );
			if( n < 0 || k < 0 )
				throw new IllegalStateException( "-Los datos deben de ser enteros naturales mayores o iguales que 0-" );
			table[index][0] = n;
			table[index][1] = k;
    	}
    	catch( NumberFormatException  e  )
    	{
    		throw new NumberFormatException( "-Los datos deben de ser enteros naturales mayores o iguales que 0-" );
    	}
    	return table;
    }
}

