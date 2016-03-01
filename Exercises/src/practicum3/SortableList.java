package practicum3;

import java.util.ArrayList;
import java.util.Collections;

public class SortableList<T extends Comparable<T>> extends ArrayList<T> {

	public void sort() {
		Collections.sort(this);
	}
	
	public void addFirst(T element) {
		add(0, element);
	}
	
}
