package test;

import Aerei.AeroportiMondo;
import Aerei.Compagnia;
import Aerei.InvalidCode;

import junit.framework.TestCase;

public class TestR2_Aerei extends TestCase {

    AeroportiMondo am;
    Compagnia al;
    Compagnia lu;
    
    public void setUp(){
         am = new AeroportiMondo();
         al = new Compagnia("Alitalia", am);
         lu = new Compagnia("Lufthansa", am);
    }
	
    public void testAereoDuplicato() {
        try {
            al.addAereo("734", 120);
            al.addAereo("734", 70);
            fail("Il codice non dovrebbe essere valido");
        }catch(InvalidCode ic){
            // OK
        }
    }
    	
	public void testAerei() {
		try {
			lu.addAereo("734", 150);
			lu.addAereo("543", 100);
			lu.addAereo("652", 150);
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
		assertEquals(3,lu.getAerei().size());
	}
	
	public void testAereiOrdinati() {
		try {
			lu.addAereo("734", 150);
			lu.addAereo("543", 100);
			lu.addAereo("652", 150);
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
		assertEquals(lu.getAerei().get(0),"543" );
		assertEquals(lu.getAerei().get(1),"652" );
		assertEquals(lu.getAerei().get(2),"734" );
	}
	
}
