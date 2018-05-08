import java.util.ArrayList;

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
}

