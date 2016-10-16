package Aerei;

import java.util.Comparator;

public class VoloComparator implements Comparator<Volo> {

	@Override
	public int compare(Volo v1, Volo v2) {
		
		int n=v1.getCod().compareTo(v2.getCod());

		return n;
	}

}
