package Aerei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Compagnia {

	private String nomeCompagnia;
	private AeroportiMondo aeroportiCompagnia=new AeroportiMondo();
	private Map<String,Integer> aereiCompagnia= new TreeMap<String,Integer>();
	private Map<String,Volo> voliCompagnia = new TreeMap<String,Volo>();
	
	public Compagnia(){
	}
	
	public Compagnia(String nome, AeroportiMondo am){
		this.nomeCompagnia=nome;
		this.aeroportiCompagnia=am;
	}
	
	public String getNomeCompagnia() {
		return nomeCompagnia;
	}



	public void setNomeCompagnia(String nomeCompagnia) {
		this.nomeCompagnia = nomeCompagnia;
	}



	public AeroportiMondo getAeroportiCompagnia() {
		return aeroportiCompagnia;
	}



	public void setAeroportiCompagnia(AeroportiMondo aeroportiCompagnia) {
		this.aeroportiCompagnia = aeroportiCompagnia;
	}



	public Map<String, Integer> getAereiCompagnia() {
		return aereiCompagnia;
	}



	public void setAereiCompagnia(Map<String, Integer> aereiCompagnia) {
		this.aereiCompagnia = aereiCompagnia;
	}



	public Map<String, Volo> getVoliCompagnia() {
		return voliCompagnia;
	}



	public void setVoliCompagnia(Map<String, Volo> voliCompagnia) {
		this.voliCompagnia = voliCompagnia;
	}



	public void addAereo(String cod, int pass) throws InvalidCode {
		if(aereiCompagnia.containsKey(cod))
		{
			throw new InvalidCode();
		}
		aereiCompagnia.put(cod, pass);
	}
	
	public List<String> getAerei(){
		List<String> listaAerei= new ArrayList<String>();
		for(Map.Entry m : aereiCompagnia.entrySet())
		{
			listaAerei.add(m.getKey().toString());
		}
		
		
		return listaAerei;
	}
	
	public void addVolo(String cod, String aer, String part, String arr, String giorno) throws InvalidCode {
	
		if(voliCompagnia.containsKey(cod))
		{
			throw new InvalidCode();
		}
		
		Volo v=new Volo(cod,aer);
		v.setC(this);
		v.setPartenza(part);
		v.setArrivo(arr);
		v.setGiorno(giorno);
		v.setPostiLiberi(aereiCompagnia.get(aer));
		voliCompagnia.put(cod,v);
		aeroportiCompagnia.getAeroporto(part).getListaArrivi().put(cod, v);
		aeroportiCompagnia.getAeroporto(arr).getListaPartenze().put(cod, v);
	}
	
	public void cancelVolo(String cod) throws InvalidCode {
		if(!voliCompagnia.containsKey(cod))
		{
			throw new InvalidCode();
		}
		Volo v=voliCompagnia.get(cod);
		Aeroporto ap=aeroportiCompagnia.getAeroporto(v.getPartenza());
		Aeroporto aa=aeroportiCompagnia.getAeroporto(v.getArrivo());
		ap.getListaArrivi().remove(cod);
		aa.getListaPartenze().remove(cod);
		voliCompagnia.remove(cod);
	}
	
	public List<Volo> getVoli(){
		List <Volo> listaVoli = new ArrayList<Volo>();

		for(Map.Entry m : voliCompagnia.entrySet())
		{
			listaVoli.add(voliCompagnia.get(m.getKey()));
		}
		return listaVoli;
	}
	
	public boolean prenota(int num, String cod) throws InvalidCode {
		
		boolean b;

		if(!voliCompagnia.containsKey(cod))
		{
			throw new InvalidCode();
		}
		String aer=voliCompagnia.get(cod).getAer();
		int n=postiLiberi(cod);
		if(num>n)
		{
			b= false;
		}
		else			
		{
			int nposti=postiLiberi(cod) -num;
			voliCompagnia.get(cod).setPostiLiberi(nposti);
			b= true;
		}
		return b;
	}
	
	public int postiLiberi(String cod) throws InvalidCode {
		
		if(!voliCompagnia.containsKey(cod))
		{
			throw new InvalidCode();
		}
		
		return voliCompagnia.get(cod).getPostiLiberi();
	}			
	
	public void partitoVolo(String cod, int ritardo) throws InvalidCode {
		
		if(!voliCompagnia.containsKey(cod))
		{
			throw new InvalidCode();
		}
		
		voliCompagnia.get(cod).setRitardop(ritardo);
	}
	
	public void arrivatoVolo(String cod, int ritardo) throws InvalidCode {
		if(!voliCompagnia.containsKey(cod))
		{
			throw new InvalidCode();
		}
		
		voliCompagnia.get(cod).setRitardoa(ritardo);
	}
		
	public List<String> ritardiPartenza() {
		
		List<String> lista=new ArrayList<String>();
		for(Map.Entry v: voliCompagnia.entrySet())
		{
			int delay=voliCompagnia.get(v.getKey()).getRitardop();
			if(delay>15)
			{
				lista.add(voliCompagnia.get(v.getKey()).getCod());
			}
		}
		
		Collections.sort(lista);
		
		return lista;
	}
	
	public List<String> ritardiArrivo() {
		List<String> lista=new ArrayList<String>();
		for(Map.Entry v: voliCompagnia.entrySet())
		{
			int delay=voliCompagnia.get(v.getKey()).getRitardoa();
			if(delay>15)
			{
				lista.add(voliCompagnia.get(v.getKey()).getCod());
			}
		}
		
		Collections.sort(lista);
		
		return lista;
	}
	
	public List<String> voliCritici() {
		List<String> lista=new ArrayList<String>();
		for(Map.Entry v: voliCompagnia.entrySet())
		{
			int postiTotali=aereiCompagnia.get(voliCompagnia.get(v.getKey()).getAer());
			int postiLiberi = voliCompagnia.get(v.getKey()).getPostiLiberi();
			int percent=(int) (postiTotali*0.3);
			if(postiLiberi>percent)
			{
				lista.add(voliCompagnia.get(v.getKey()).getCod());
			}
		}
		
		Collections.sort(lista);
		
		return lista;
	}
}
