package server;

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
     * Devuelve un ArrayList de clientes a partir de un archivo de
     * entrada, si este es correcto
     * @return El array de clientes
     */
    public static ArrayList<Client> getClientList( String file ) throws FileNotFoundException, IOException, IllegalStateException, NumberFormatException
    {
    	File rFile = new File( file );
        ArrayList<Client> clientList = new ArrayList<Client>();
        try
        {
	        if( rFile.exists() )
	        {
	            try
	            {
	                FileReader fileReader = new FileReader( rFile );
	                BufferedReader bufferedReader = new BufferedReader( fileReader );
	                String firstLine = bufferedReader.readLine();
	                int numOfClients = checkFirstLine( firstLine );
	                String waitingTime = bufferedReader.readLine();
	                clientList = checkSecondLine( waitingTime, numOfClients );
	                moreLines( bufferedReader );
	                bufferedReader.close();            
	            }
	            catch( IOException e )
	            {
	                throw( e );
	            }
	        }
	        else throw new FileNotFoundException();
        }
        catch( FileNotFoundException ef )
        {
        	throw new FileNotFoundException( "--Fichero no enontrado--" );
        }
        return clientList;
    }
    
    /**
     * Comprueba si la primera linea del archivo contiene un entero
     * que indica el número de clientes a la espera y lo devuelve si
     * es correcto
     * @param numOfClients El String leído con el buffer del archivo
     * @return el número de clientes
     * @throws IOException Indica que o no se ha detectado nada o que no se
     *                     ha introducido un entero
     */
    private static int checkFirstLine( String numOfClients ) throws IOException, NumberFormatException
    {
    	if( numOfClients == null || numOfClients.isEmpty() ) throw new NumberFormatException( "--No se han encontrado datos de lectura--" );
    	int numOfClient = 0;
    	try
    	{
    		numOfClient = Integer.parseInt( numOfClients );
    	}
    	catch( NumberFormatException e )
    	{
    		throw new NumberFormatException( "--El número de clientes debe ser un entero natural--" );
    	}
    	return numOfClient;
    }
    
    /**
     * Comprueba que la segunda linea del archivo contiene los tiempos de espera de los clientes
     * acorde al número de clientes, si es correcto devuelve un ArrayList de tipo cliente con la 
     * posición del cliente y su tiempo de espera
     * @param waitingTime String leido con el buffer del archivo
     * @param numClients número de clientes
     * @return la lista de los clientes 
     * @throws IOException Indica o que no se ha detectado nada o que los tiempos de espera no 
     * 					   no son acordes con el número de clientes
     */
    private static ArrayList<Client> checkSecondLine( String waitingTime, int numClients ) throws IOException, NumberFormatException
    {
    	ArrayList<Client> server = new ArrayList<Client>();
    	try
    	{
	    	if( waitingTime == null || waitingTime.equals( "" ) ) throw new IllegalStateException( "--No se dispone de ningún dato para el tiempo de espera de clientes--" );
	    	String[] aux = waitingTime.split( " " );
    		if( numClients != aux.length ) throw new NumberFormatException( "--Número de tiempos de espera no acorde con número de clientes--" );
	    	
	    	for( int i = 0; i < aux.length; i++ )
	    	{
	    		try
	        	{
	    			int time = Integer.parseInt( aux[i] );
	    			if( time <= 0 ) throw new NumberFormatException();
	        		server.add( new Client( i+1, time ) );
	        	}
	        	catch( NumberFormatException  e  )
	        	{
	        		throw new NumberFormatException( "--Los tiempos de espera deben de ser enteros naturales mayores o iguales que 0--" );
	        	}
	    	}
    	}
    	catch( NumberFormatException | IllegalStateException ie )
    	{
    		throw ( ie );
    	}
    	
    	return server;
    }
    
    /**
     * Comprueba que no se encuentre nada más en el archivo
     * @param br el Buffer crado a partir del archivo
     * @throws IOException Indica que se han introducido datos de más
     */
    private static void moreLines( BufferedReader br ) throws IOException
    {
		if( br.readLine() != null )
			throw new IllegalStateException( "--Se han introducido datos de más en el archivo--" );    		
    }
}
