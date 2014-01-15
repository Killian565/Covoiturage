package covoiturage;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;


public class Testing {

	private static String[] ks;

	public static void main(String[] args) {
	
		
		Hashtable<Boolean,String> h,g;
		h=new Hashtable<Boolean, String>();
		h.put(true, "n1");
		h.put(true, "n2");
		h.put(false, "n3");
		h.put(false, "n4");
		h.put(true, "n5");
		h.put(false, "n6");
		g=h;
		ks = null;
		int i=h.size();
		for (int j=0;j<i;j++){
			ks[j]=h.get(true);
			h.remove(true);
			System.out.print(ks[j]);
		}
		
		
		
		
	}

}
