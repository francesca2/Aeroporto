package Aerei;

public class Volo {

	private String cod;
	private String aer;
	private String partenza;
	private String arrivo;
	private String giorno;
	private Compagnia c=new Compagnia();
	private int postiLiberi;
	private int ritardop;
	private int ritardoa;


	
	public Volo(String cod, String aer) {
		this.cod = cod;
		this.aer = aer;
	}



	public String getCod() {
		return cod;
	}



	public void setCod(String cod) {
		this.cod = cod;
	}



	public String getAer() {
		return aer;
	}



	public void setAer(String aer) {
		this.aer = aer;
	}



	public String getPartenza() {
		return partenza;
	}



	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}



	public String getArrivo() {
		return arrivo;
	}



	public void setArrivo(String arrivo) {
		this.arrivo = arrivo;
	}



	public String getGiorno() {
		return giorno;
	}



	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public Compagnia getC() {
		return c;
	}

	public void setC(Compagnia c) {
		this.c = c;
	}
	
	public int getPostiLiberi() {
		return postiLiberi;
	}



	public void setPostiLiberi(int postiLiberi) {
		this.postiLiberi = postiLiberi;
	}



	public int getRitardop() {
		return ritardop;
	}



	public void setRitardop(int ritardop) {
		this.ritardop = ritardop;
	}



	public int getRitardoa() {
		return ritardoa;
	}



	public void setRitardoa(int ritardoa) {
		this.ritardoa = ritardoa;
	}



	public String toString(){
		String s=c.getNomeCompagnia()+";"+ cod + ";" + aer + ";" + partenza +";" + arrivo +";" +giorno+ ";"+c.getAereiCompagnia().get(aer);
		return s; 
	}
}
