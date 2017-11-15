package server;

import java.util.ArrayList;
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
			ArrayList<Client> cl = new ArrayList<Client>();
			if( args.length == 0 )
				System.out.println( "No se han detectado parámetros" );
			if( args.length == 1 )
			{
				if ( args[0].equals( "-h" ) ) System.out.println( help() );
		        else System.out.println( resultWithOutTrace( cl, args[0] ) );
			}
			else if( args.length == 2 )
			{
				if( args[0].equals( "-t" ) ) System.out.println( resultWithTrace( cl, args[1] ) );
				else if( args[0].equals( "-h") ) throw new IOException( "--Parámetros mal introducidos, introduzca -h para ayuda--" );
				else
				{
					printInFile( args[1] );
					System.out.println( resultWithOutTrace( cl, args[0] ) );
					System.setOut( new PrintStream( new FileOutputStream( FileDescriptor.out ) ) );
					System.out.println( "Salida de arhivo" );
				}
			}
			else if( args.length == 3 )
			{
				if( args[0].equals( "-t" ) )
				{
					printInFile( args[2] );
					System.out.println( resultWithTrace( cl, args[1] ) );
					System.setOut( new PrintStream( new FileOutputStream( FileDescriptor.out ) ) );
					System.out.println( "Salida de arhivo" );
				}
				else throw new IOException( "--Parámetros mal introducidos, introduzca -h para ayuda--" );
			}
		}
		catch( IOException | IllegalStateException| NumberFormatException e )
		{
			System.out.println( e );
		}
    }
	
	private static String resultWithTrace( ArrayList<Client> cl, String arg )  throws IOException
	{
		cl = Reader.getClientList( arg );
    	String[] sol = Server.timeMinimalize( cl );
    	return sol[0] + "\r\n" + sol[1] + "\r\n" + sol[2];
	}

	private static String resultWithOutTrace( ArrayList<Client> cl, String arg ) throws IOException
	{
		cl = Reader.getClientList( arg );
    	String[] sol = Server.timeMinimalize( cl );
    	return sol[0] + "\n" + sol[1];
	}
	
	private static String help()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append( "SINTAXIS:\n" );
		sb.append( "servicio [-t] [-h] [fichero_entrada] [fichero_salida]\n" );
		sb.append( "-t                     Traza la selección de clientes\n" );
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
