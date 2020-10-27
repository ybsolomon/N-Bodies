public interface List<T> {

	T get(int pos) throws Exception;
	boolean add(T item);
	void add(int pos, T item) throws Exception;
	T remove(int pos) throws Exception;
	int size();

}