package server;

import java.util.ArrayList;
import heap.HeapIN;
import heap.HeapMin;

/**
 * Esta clase se encarga de realizar el esquema voraz
 * Encuentra la solución más optima de entre todos los candidatos
 * 
 * @author David Pastor
 *
 */
public abstract class Server
{
	/**
	 * De un conjunto de clientes, con sus respectivos tiempos de servicios,
	 * se obtiene el menor tiempo de estancia en el servicio para todos.
	 * Primero se introdicen los candidatos en un montículo de mínimos, después mientras
	 * el montículo siga teniendo candidatos se realizaá un ordenamiento por montículos
	 * y se irán obteniendo los candidatos en orden de menor a mayor y se irán introduciendo
	 * en la solución
	 * @param clients el conjunto de clientes 
	 * @return Un array con el tiempo total de servicio, orden de los clientes y 
	 *         la traza.
	 */
	public static String[] timeMinimalize( ArrayList<Client> clients )
	{
		String orderClients = new String();
		String[] sol = new String[3];
		HeapIN<Client> h = new HeapMin<Client>( clients );
		int time = 0;
		int totalTime = 0;
		String aux = "";
		String aux2 = "";
		String trace = "";
		
		while( !h.isEmpty() )
		{
			Client c = h.top();
			orderClients += c.getPosition() + " ";
			time += c.getWaitingTime();
			
			if( aux == "" )
			{
				aux += c.getWaitingTime();
				aux2 += aux;
			}
			else
			{
				aux += "+" + c.getWaitingTime();
				aux2 += "+(" + aux + ")";
			}
			trace = drawTrace( trace, aux, time, c.getPosition(), true );
			totalTime += time;
		}
		trace = drawTrace( trace, aux2, totalTime, 0, false );
		sol[0] = totalTime + "";
		sol[1] = orderClients;
		sol[2] = trace;
		return sol;
	}
	
	private static String drawTrace( String t, String n, int time, int client, boolean cont )
	{
		if( t.isEmpty() )
		{
			t += "---------------------------------------------\r\n";
			t += " Cliente || Tiempo de Espera de Cada Cliente \r\n";
			t += "---------------------------------------------\r\n";
			t += String.format( " %4d    || %s = %d\r\n", client, n, time );
		}
		else if( cont )
			t += String.format( " %4d    || %s = %d\r\n", client, n, time );
		else if( !cont )
			t += "---------------------------------------------\r\n" + " Traza:  "
		      + n + "=" + time + "\r\n---------------------------------------------\r\n";
		
		return t;
	}
}
