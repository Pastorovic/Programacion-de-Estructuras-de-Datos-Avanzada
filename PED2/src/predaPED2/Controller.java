package predaPED2;

import java.math.BigInteger;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileDescriptor;

/**
 * Controla las demás clases y contiene el método main del programa
 * @author David Pastor
 *
 */
public class Controller
{	
	public static void main( String[] args )
	{
		try
		{
			getArguments( args );
		}
		catch( IOException | IllegalStateException| NumberFormatException e )
		{
			System.out.println( e );
		}
	}
	
	private static void getArguments( String[] args ) throws IOException
    {
		try
		{
			int[][] parameters;
			BigInteger[][] table;
			if( args.length == 0 )
				System.out.println( "--No se han detectado parámetros--" );
			if( args.length == 1 && args[0].equals( "-h" ) ) System.out.println( help() );
			else
			{	
				if( args.length == 1 )
				{
					if( args[0].equals( "-t" ) )
						throw new IOException( "--Parámetros mal introducidos, introduzca -h para ayuda--" );
					else
					{
						parameters = Reader.getBinomialList( args[0] );
						table = getTable( parameters );
						resultWithOutTrace( table, parameters );
					}
				}
				else if( args.length == 2 )
				{
					if( args[0].equals( "-h") ) throw new IOException( "--Parámetros mal introducidos, introduzca -h para ayuda--" );
					
					if( args[0].equals( "-t" ) )
					{
						parameters = Reader.getBinomialList( args[1] );
						table = getTable( parameters );
						resultWithTrace( table, parameters );
					}
					else
					{
						parameters = Reader.getBinomialList( args[0] );
						table = getTable( parameters );
						printInFile( args[1] );
						resultWithOutTrace( table, parameters );
						System.setOut( new PrintStream( new FileOutputStream( FileDescriptor.out ) ) );
						System.out.println( "Salida de arhivo" );
					}
				}
				else if( args.length == 3 )
				{
					parameters = Reader.getBinomialList( args[1] );
					table = getTable( parameters );
					if( args[0].equals( "-t" ) )
					{
						printInFile( args[2] );
						resultWithTrace( table, parameters );
						System.setOut( new PrintStream( new FileOutputStream( FileDescriptor.out ) ) );
						System.out.println( "Salida de arhivo" );
					}
					else throw new IOException( "--Parámetros mal introducidos, introduzca -h para ayuda--" );
				}
			}
		}
		catch( IOException | IllegalStateException| NumberFormatException e )
		{
			System.out.println( e );
		}
    }
	
	private static BigInteger[][] getTable( int[][] parameters )
	{
		int n = 0;
		int k = 0;
		for( int i = 0; i < parameters.length; i++ )
		{
			if( parameters[i][0] > n ) n = parameters[i][0];
			if( parameters[i][1] > k ) k = parameters[i][1];
		}
		return Server.pascal( n, k );
	}
	
	private static void resultWithOutTrace( BigInteger[][] table, int[][] parameters )  throws IOException
	{
		int n, k;
    	for( int i = 0; i < parameters.length; i++ )
    	{
    		n = parameters[i][0];
    		k = parameters[i][1];
    		System.out.println( table[n][k] );
    	}
	}

	private static void resultWithTrace( BigInteger[][] table, int[][] parameters ) throws IOException
	{
		resultWithOutTrace( table, parameters );
		int n, k;
    	for( int i = 0; i < parameters.length; i++ )
    	{
    		n = parameters[i][0];
    		k = parameters[i][1];
    		System.out.println( Server.trace( n, k, table ) );
    	}
	}
	
	private static String help()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append( "SINTAXIS:\n" );
		sb.append( "coefbin [-t] [-h]     [fichero_entrada] [fichero_salida]\n" );
		sb.append( "-t                     Traza la construcción del Triángulo de Pascal\n" );
		sb.append( "-h                     Muestra esta ayuda\n" );
		sb.append( "fichero_entrada        Nombre del fichero de entrada\n" );
		sb.append( "fichero_salida         Nombre del fichero de salida\n" );
		
		return sb.toString();
		
	}
	
	private static void printInFile( String fileName ) throws IOException
	{
		File file = new File( fileName );
		if( file.exists() && file.isFile() ) throw new IOException( " --El archivo de salida ya existe--" );
		PrintStream out = null;
		try
		{
			out = new PrintStream( new FileOutputStream( fileName ) );
		}
		catch ( FileNotFoundException e )
		{
			System.out.println( e + " --No se ha encontrado la ruta del archivo--" );
		}
		System.setOut( out );
	 }
}

