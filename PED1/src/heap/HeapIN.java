package heap;

public interface HeapIN<T extends Comparable<T>>
{
	/**
	 * Montículo vacío
	 * @return true si el montículo está vacío
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
	 * restaura las propiedades de montículo
	 * @return  La cima del montículo
	 */
	public T top();
	
	/**
	 * Inserta un elemento en el montículo
	 * se inserta al fondo y se flota hasta su posición
	 * @param e El elemento a insertar
	 * @return el montículo con el nuevo elemento
	 */
	public HeapIN<T> insert( T e );
	
	/**
	 * Devuelve el número de elementos que contiene
	 * el montículo
	 * @return número de elementos del montículo
	 */
	public int getNumElem();
}
