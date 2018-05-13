import java.util.ArrayList;
/**
 * Class that includes a recursive quicksort method
 * @author Tommy V. Tran
 *
 */
public class Toolbox {

	public static int binarySearch(ArrayList<Comparable> a, int l, int r, Comparable x) {
		if (r >= l) {
			int mid = l + (r-l)/2;

			//If the element is present at the middle itself
			if (a.get(mid).equals(x)) {
				return mid;
			}

			//If element is smaller than mid, then
			//it can only be present in left subarraylist
			if(a.get(mid).compareTo(x) > 0) {
				return binarySearch(a,l ,mid-1, x);
			} else {
				return binarySearch(a, mid +1 , r, x );

			}
		}

		//We reach here when element is not present in arraylist
		return -1;
	}


	/**
	 * Organizes a list in descending order using recursive quick sort algorithm
	 *
	 * @param a
	 *            reference to an array of Comparables to be sorted
	 * @param first
	 *            starting index of range of values to be sorted
	 * @param last
	 *            ending index of range of values to be sorted
	 */
	public static void quickSort(ArrayList<Comparable> a, int first, int last) {
		// Add your code here
		int g = first, h = last;

		int medianIndex = (first + last) / 2;

		Comparable median = a.get(medianIndex);

		do {

			// find a value on the left side of median that doesn't belong there
			while (a.get(g).compareTo(median) > 0) {
				g++;
			}
			// find a value on the right side of median that doesn't belong there
			while (a.get(h).compareTo(median) < 0) {
				h--;
			}
			// swap those two values
			if (g <= h) {
				swap(a, g, h);
				g++; // advance units to continue checking
				h--;
			}

		} while (g < h);

		if (h > first) {
			quickSort(a, first, h); // recursively quickSort left and right sublists
		}

		if (g < last) {
			quickSort(a, g, last);
		}
	}
	/**
	 * Interchanges two elements in an ArrayList
	 *
	 * @param list
	 *            reference to an array of integers
	 * @param a
	 *            index of integer to be swapped
	 * @param b
	 *            index of integer to be swapped
	 */
	private static void swap(ArrayList<Comparable> list, int a, int b) {

		Comparable temp = list.get(a);
		list.set(a, list.get(b));
		list.set(b, temp);
	}
}