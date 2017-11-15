package heap;

import java.util.ArrayList;


/**
 * Implementa la clase de montículo de mínimos
 *
 * @author David Pastor
 */
public class HeapMin<T extends Comparable<T>> implements HeapIN<T>
{
	private ArrayList<T> vec;
	private int numElem;
	private int MAX;

	public HeapMin( int max )
	{
		this.vec = new ArrayList<T>( max );
		this.numElem = 0;
		this.MAX = max;
	}

	/**
	 * Crear montículo lineal
	 * Crea un montículo a partir de los elementos de
	 * un vecto mediante el procedimiento hundir
	 * @param v El vector mediante el cual se crea el montículo
	 */
	public HeapMin( ArrayList<T> v )
	{
		if( v != null )
		{
			this.MAX = v.size();
			this.vec = v;
			this.numElem = v.size();
			for( int i = v.size()/2; i > -1; i-- )
				sink( i, this.vec );
		}
	}

	/**
	 * Montículo vacío
	 * @return true si el montículo está vacío
	 */
	public boolean isEmpty()
	{
		boolean empty = false;
		if( this.numElem == 0 ) empty = true;
		return empty;
	}

	/**
	 * Hundir
	 * Reubica el elemento n-ésimo, en caso de que sea
	 * mayor/menor que alguno de sus hijos
	 * @param n El elemento del vector a hundir
	 */
	private void sink( int n, ArrayList<T> v )
	{
		int leftChild, rightChild, root;
		if( this.numElem > 1 )
		{
			do
			{
				leftChild = n*2+1;
				rightChild = n*2+2;
				root = n;

				if( rightChild < this.numElem && v.get( rightChild ).compareTo( v.get( n ) ) < 0 )
					n = rightChild;
				if( leftChild < this.numElem && v.get( leftChild ).compareTo( v.get( n ) ) < 0 )
					n = leftChild;

				change( root, n, v );
			}
			while( root != n );
		}
	}

	/**
	 * Flotar
	 * Reubica el elemento n-ésimo, en caso de que sea
	 * mayor/menor que alguno de sus hijos
	 * Se utiliza para introducir elementos
	 * @param n El elemento del vector a flotar
	 */
	private void hover( int n, ArrayList<T> v )
	{
		while( n > 0 && v.get( n/2 ).compareTo( v.get( n ) ) < 0)
		{
			change( n, n/2, v );
			n /= 2;
		}
	}

	private void change( int son, int father, ArrayList<T> v )
	{
		T auxF = v.get( father );
		v.set( father, v.get( son ) );
		v.set( son, auxF );
	}

	/**
	 * Primero
	 * Devuelve el elemento mayor/menor
	 * @return El elemento mayor/menor
	 */
	public T getTop()
	{
		T e = null;
		if( this.numElem != 0 )
			e = vec.get( 0 );
		return e;
	}

	/**
	 * ObtenerCima
	 * Devuelve la cima y la borra,
	 * restaura las propiedades de mont�culo
	 * @return  La cima del mont�culo
	 */
	public T top()
	{
		T top = null;
		if( this.numElem != 0 )
		{
			top = this.vec.get( 0 );
			this.vec.set( 0, this.vec.get( this.numElem-1 ) );
			this.vec.remove( this.numElem-1 );
			this.numElem -= 1;
			sink( 0, this.vec );

		}
		return top;
	}

	/**
	 * Inserta un elemento en el mont�culo
	 * se inserta al fondo y se flota hasta su posici�n
	 * @param e El elemento a insertar
	 * @return el montículo con el nuevo elemento
	 */
	public HeapIN<T> insert( T e )
	{
		if( e != null )
		{
			if( this.isEmpty() )
			{
				this.numElem += 1;
				vec.add( 0, e );
			}
			else if( this.MAX > this.numElem )
			{
				this.numElem += 1;
				this.vec.add(numElem-1, e );
				hover( numElem-1, this.vec );
			}
		}
		return this;
	}

	/**
	 * Devuelve el n�mero de elementos que contiene el mont�culo
	 * @return el n�mero de elementos del mont�culo
	 */
	public int getNumElem()
	{
		return numElem;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( "HeapMin [ " );
		int it = this.numElem;
		for( int i = 0; i < numElem; i++ )
		{
			sb.append( vec.get( i ) );
			if( i+1 != it ) sb.append( ", " );
		}
		sb.append( " ]" );
		return sb.toString();
	}
}
