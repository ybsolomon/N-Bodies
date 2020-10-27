public class LinkedList<T> implements List<T> {
	private Node head;
	private int size;

	private class Node<T> {
		T data;
		Node<T> next;

		public Node(T item) {
			data = item;
			next = null;
		}
	}

	public LinkedList() {
		head = null;
		size = 0;
	}

	public T get(int pos) throws Exception {
		if (pos < 0 || pos >= size) {
			throw new Exception("position out of bounds");
		}
		Node<T> curr = head;
		for (int i = 0; i < pos; i++){
			curr = curr.next;
		}
		return curr.data;
	}

	public boolean add(T item) {
		if (head == null) {
			head = new Node(item);
			++size;
			return true;
		}

		Node<T> curr = head;
		for (int i = 0; i < size-1; i++){
			curr = curr.next;
		}
		Node<T> node = new Node(item);
		curr.next = node;
		size++;

		return true;
	}

	public void add(int pos, T item) throws Exception {
		if (pos < 0 || pos >= size) {
			throw new Exception("position out of bounds");
		}

		if (pos == 0) {
			Node<T> node = new Node(item);
			node.next = head;
			head = node;
			++size;
		} else {
			Node<T> curr = head;
			for (int i=0; i < pos-1; i++) {
				curr = curr.next;
				Node node = new Node(item);
				node.next = curr.next;
				curr.next = node;
				
			}
			++size;
		}
	}

	public T remove(int pos) throws Exception {
		if (pos < 0 || pos >= size) {
			throw new Exception("position out of bounds");
		}

		if (pos == 0) {
			Node<T> node = head;
			head = head.next;
			--size;
			return node.data;
		} else {
			Node<T> curr = head;
			for (int i=0; i < pos-1; i++) {
				curr = curr.next;
			}

			Node<T> node = curr.next;
			curr.next = node.next;
			--size;

			return node.data;
		}
	}

	public int size(){
		return size;
	}
}