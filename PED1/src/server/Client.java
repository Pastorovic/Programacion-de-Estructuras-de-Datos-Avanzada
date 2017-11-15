package server;

/**
 * Clase cliente que almacena la posici�n del cliente en la lista de espera
 * y el tiempo que necesitar� ser atendido
 * Se compone de getter y setters adem�s de extender la clase Comparable
 * e implementar su m�todo compareTo
 * 
 * @author David Pastor
 *
 */
public class Client implements Comparable<Client>
{
	private int position;
	private int waitingTime;
	
	public Client( int position, int waitingTime ) 
	{
		this.position = position;
		this.waitingTime = waitingTime;
	}

	public int getPosition() 
	{
		return position;
	}

	public void setPosition( int position )
	{
		this.position = position;
	}

	public int getWaitingTime() 
	{
		return waitingTime;
	}

	public void setWaitingTime( int waitingTime ) 
	{
		this.waitingTime = waitingTime;
	}
	
	@Override
	public String toString()
	{
		return "( " + position + ", " + waitingTime + " )";
	}
	
	/**
	 * Comparador de la clase cliente.
	 * Compara dos objetos de la clase cliente por su tiempo
	 * necesario en servicio
	 * @param el cliente a comparar con this
	 * @return devuelve 1 si this tiene mayor tiempo que c, -1 si tiene
	 *         menor tiempo y 0 si son iguales
	 */
	public int compareTo( Client c )
	{
		if( this.waitingTime > c.waitingTime ) return 1;
		else if( this.waitingTime < c.waitingTime ) return -1;
		else return 0;
	}
}
