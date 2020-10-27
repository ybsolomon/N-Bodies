public class ArrayList<T> implements List<T> {

	private T[] arr;
	private int size;

	public ArrayList() {
		arr = (T[]) new Object[10];
		size = 0;
	}

	private void grow_array() {
		T[] new_arr = (T[]) new Object[arr.length*2];
		for (int i = 0; i < arr.length; i++) {
			new_arr[i] = arr[0];
		}
		arr = new_arr;
	}

	public T get(int pos) throws Exception {
		if (pos < 0 || pos >= size) {
			throw new Exception("position out of bounds");
		}
		return arr[pos];
	}

	public boolean add(T item) {
		if (size == arr.length) {
			grow_array();
		}
		arr[size++] = item;
		return true;
	}

	public void add(int pos, T item) throws Exception {
		if (pos < 0 || pos >= size) {
			throw new Exception("position out of bounds");
		}
		if (size == arr.length) {
			grow_array();
		}

		for (int i = size; i > pos; i--) {
			arr[i+1] = arr[i];
		}

		arr[pos] = item;
		size++;
	}

	public T remove(int pos) throws Exception {
		if (pos < 0 || pos >= size) {
			throw new Exception("position out of bounds");
		}
		T temp = arr[pos];

		for (int i = pos; i < size-1; i++) {
			arr[i] = arr[i+1];
		}
		size--;

		return temp;
	}

	public int size(){
		return size;
	}
}