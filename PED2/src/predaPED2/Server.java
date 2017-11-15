package predaPED2;

import java.math.BigInteger;

/**
 * Este clase se encarga de realizar el algoritmo que calculará el 
 * valor del número combinatorio
 * @author David Pastor
 *
 */
public abstract class Server
{
	/**
	 * Devuelve la tabla que representa el triángulo de pascal del número
	 * cominatorio
	 * @param n Primer valor del número combinatorio
	 * @param k Segundo valor del número combinatorio
	 * @return Tabla que representa el triángulo de pascal del número combinatorio
	 */
	public static BigInteger[][] pascal( int n, int k )
	{
		BigInteger[][] table = new BigInteger[n+1][k+1];
		for( int i = 0; i <= n; i++ ) table[i][0] = new BigInteger( "1" );
		for( int i = 1; i <= n; i++ ) table[i][1] = new BigInteger( Integer.toString( i ) );
		for( int i = 2; i <= k; i++ ) table[i][i] = new BigInteger( "1" );
		for( int i = 3; i <= n; i++ )
			for( int j = 2; j <= k; j++ )
				if( j <= k && table[i-1][j-1] != null && table[i-1][j] != null )
					table[i][j] = new BigInteger( "0" ).add( table[i-1][j-1] ).add( table[i-1][j] );
		return table;
	}
	
	/**
	 * Devuelve el resultado, a partir de la tabla, de un
	 * número combinatorio
	 * @param n Primer valor del número combinatorio
	 * @param k Segundo valor del número combinatorio
	 * @param table La tabla de donde se calcula
	 * @return El valor del número combinatorio ( n, k )
	 */
	public static int getResult( int n, int k, BigInteger[][] table )
	{
		return table[n][k].intValue();
	}
	
	/**
	 * Devuelve la traza de un número combinatorio,
	 * esta es el triángulo de Pascal
	 * @param n Primer valor del número combinatorio
	 * @param k Segundo valor del número combinatorio
	 * @param table La tabla donde se calcula
	 * @return El triángulo de Pascal del número ( n, k )
	 */
	public static String trace( int n, int k, BigInteger[][] table )
	{
		String t = String.format( "La tabla del coeficiente binomial de %d %d es:\n\r 1\n\r", n, k );
		for( int i = 1; i <= n; i++ )
		{
			t +=  " 1";
			for( int j = 1; j <= k; j++ )
				if( table[i][j] != null )
					t += String.format( "%10d ", table[i][j] );
			t +=  "\n\r";
		}
		return t;
	}
}
