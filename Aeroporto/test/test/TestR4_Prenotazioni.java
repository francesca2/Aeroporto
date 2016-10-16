package test;


import Aerei.AeroportiMondo;
import Aerei.Aeroporto;
import Aerei.Compagnia;
import Aerei.InvalidCode;

import junit.framework.TestCase;


public class TestR4_Prenotazioni extends TestCase {

    AeroportiMondo am;
    Compagnia al;
    Compagnia lu;
    
    
    public void setUp(){
         am = new AeroportiMondo();
         al = new Compagnia("Alitalia", am);
         lu = new Compagnia("Lufthansa", am);
         Aeroporto a1 = new Aeroporto("Torino");
         Aeroporto a2 = new Aeroporto("Paris");
         try {
            am.addAeroporto(a1);
            am.addAeroporto(a2);
        } catch (InvalidCode e) {
            fail("Non e' possibile aggiungere gli aeroporti");
        }
    }
    
	
	public void testPrenotazioni() {
		try{
			lu.addAereo("734", 150);
			lu.addVolo("LH359", "734", "Torino", "Paris", "Martedì");	
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
		
		try {
			assertTrue(lu.prenota(10, "LH359"));
			assertEquals(140, lu.postiLiberi("LH359"));
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}		
	}
	
	public void testPrenotazioniEccedenti() {
		try{
			lu.addAereo("734", 150);
			lu.addVolo("LH359", "734", "Torino", "Paris", "Martedì");	
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
		
		try {
			assertFalse(lu.prenota(151, "LH359"));
			assertEquals(150, lu.postiLiberi("LH359"));
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
	}
}
