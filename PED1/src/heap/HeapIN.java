package heap;

public interface HeapIN<T extends Comparable<T>>
{
	/**
	 * Mont�culo vac�o
	 * @return true si el mont�culo est� vac�o
	 */
	public boolean isEmpty();
	
	/**
	 * Primero
	 * Devuelve el elemento mayor/menor
	 * @return El elemento mayor/menor
	 */
	public T getTop();
	
	/**
	 * ObtenerCima
	 * Devuelve la cima y la borra,
	 * restaura las propiedades de mont�culo
	 * @return  La cima del mont�culo
	 */
	public T top();
	
	/**
	 * Inserta un elemento en el mont�culo
	 * se inserta al fondo y se flota hasta su posici�n
	 * @param e El elemento a insertar
	 * @return el mont�culo con el nuevo elemento
	 */
	public HeapIN<T> insert( T e );
	
	/**
	 * Devuelve el n�mero de elementos que contiene
	 * el mont�culo
	 * @return n�mero de elementos del mont�culo
	 */
	public int getNumElem();
}
