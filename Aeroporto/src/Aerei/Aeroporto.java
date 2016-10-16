package Aerei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Aeroporto {
	
	private String code;
	private Map<String,Volo> listaPartenze=new TreeMap<String,Volo>();
	private Map<String,Volo> listaArrivi=new TreeMap<String,Volo>();

	public Aeroporto(String code){
		this.code=code;
		
	}

	public String getCode(){
		return code;
	}
	
	public Map<String, Volo> getListaPartenze() {
		return listaPartenze;
	}

	public void setListaPartenze(Map<String, Volo> listaPartenze) {
		this.listaPartenze = listaPartenze;
	}

	public Map<String, Volo> getListaArrivi() {
		return listaArrivi;
	}

	public void setListaArrivi(Map<String, Volo> listaArrivi) {
		this.listaArrivi = listaArrivi;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Volo> getArrivi(){
		List<Volo> lista= new ArrayList<Volo>();

		for(Map.Entry m : listaArrivi.entrySet())
		{
			lista.add(listaArrivi.get(m.getKey()));
		}
		
		Collections.sort(lista,new VoloComparator());
		return lista;
	}
	
	public List<Volo> getPartenze(){
		List<Volo> lista=new ArrayList(listaPartenze.values());
		Collections.sort(lista,new VoloComparator());
		return lista;
	}
}
