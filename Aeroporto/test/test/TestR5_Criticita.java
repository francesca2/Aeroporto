package test;

import junit.framework.TestCase;
import Aerei.AeroportiMondo;
import Aerei.Aeroporto;
import Aerei.Compagnia;
import Aerei.InvalidCode;

public class TestR5_Criticita extends TestCase{
      
    AeroportiMondo am;
    Compagnia al;
    Compagnia lu;
    
    
    public void setUp(){
         am = new AeroportiMondo();
         al = new Compagnia("Alitalia", am);
         lu = new Compagnia("Lufthansa", am);
         Aeroporto a1 = new Aeroporto("Torino");
         Aeroporto a2 = new Aeroporto("Paris");
         Aeroporto a3 = new Aeroporto("London");
         try {
            am.addAeroporto(a1);
            am.addAeroporto(a2);
            am.addAeroporto(a3);
            lu.addAereo("734", 150);
            lu.addAereo("543", 100);
            lu.addAereo("652", 120);
            lu.addVolo("LH359", "543", "Paris", "Torino", "Giovedì");
            lu.addVolo("LH274", "652", "Paris", "London", "Venerdì");
            lu.addVolo("LH567", "734", "Torino", "London", "Venerdì");
            lu.addVolo("LH358", "734", "Torino", "Paris", "Martedì");
        } catch (InvalidCode e) {
            fail("Non e' possibile aggiungere gli aeroporti");
        }
    }

	public void testRitardiPartenza() {
		try {
			lu.partitoVolo("LH358", 10);
			lu.partitoVolo("LH359", 15);
			lu.partitoVolo("LH567", 16);
			lu.partitoVolo("LH274", 20);
		}catch(InvalidCode ic){
		}
		assertEquals(lu.ritardiPartenza().size(), 2);
		assertEquals(lu.ritardiPartenza().get(0),"LH274");
		assertEquals(lu.ritardiPartenza().get(1),"LH567");
	}
	
	public void testRitardiArrivo() {
		try {
			lu.arrivatoVolo("LH358", 20);
			lu.arrivatoVolo("LH359", 16);
			lu.arrivatoVolo("LH567", 15);
			lu.arrivatoVolo("LH274", 10);
		}catch(InvalidCode ic){
		}
		assertEquals(lu.ritardiArrivo().size(), 2);
		assertEquals(lu.ritardiArrivo().get(0),"LH358");
		assertEquals(lu.ritardiArrivo().get(1),"LH359");
	}
	
	public void testRitardiCorretti() {
		try {
			lu.partitoVolo("LH274", 10);
			lu.partitoVolo("LH274", 20);
			lu.arrivatoVolo("LH358", 10);
			lu.arrivatoVolo("LH358", 500);
		}catch(InvalidCode ic){
		}
		assertEquals(lu.ritardiPartenza().size(), 1);
		assertEquals(lu.ritardiArrivo().size(), 1);
		assertEquals(lu.ritardiPartenza().get(0),"LH274");
		assertEquals(lu.ritardiArrivo().get(0),"LH358");
	}
	
	public void testVoliCritici() {
		try {
			assertTrue(lu.prenota(20, "LH358"));
			assertTrue(lu.prenota(40, "LH359"));
			assertTrue(lu.prenota(10, "LH567"));
			assertTrue(lu.prenota(100, "LH274"));
			lu.partitoVolo("LH358", 10);
			lu.partitoVolo("LH567", 16);
			lu.partitoVolo("LH274", 20);
		}catch(InvalidCode ic){
		}

		assertEquals(lu.voliCritici().size(), 2);
		assertEquals(lu.voliCritici().get(0),"LH358");
		assertEquals(lu.voliCritici().get(1),"LH567");
	}

}
