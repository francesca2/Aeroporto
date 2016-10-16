package Aerei;

import java.util.Map;
import java.util.TreeMap;

public class AeroportiMondo {

	private Map<String,Aeroporto> elencoAeroporti= new TreeMap<String,Aeroporto>();
	
	
	
	public void addAeroporto(Aeroporto arpt) throws InvalidCode{
		if(elencoAeroporti.containsKey(arpt.getCode()))
		{
			throw new InvalidCode();
		}
		
		elencoAeroporti.put(arpt.getCode(),arpt);
		
	}
	
	public  void removeAeroporto(String cod) {
		elencoAeroporti.remove(cod);
	}
	
	public  Aeroporto getAeroporto(String cod) {
		Aeroporto a=null;
		if(elencoAeroporti.containsKey(cod)){
			a=elencoAeroporti.get(cod);

		}
		return a;
	}
}
