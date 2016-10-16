package test;

import Aerei.AeroportiMondo;
import Aerei.Aeroporto;
import Aerei.InvalidCode;

import junit.framework.TestCase;

public class TestR1_Aeroporti extends TestCase {
	
	public void testAeroporto() {
		AeroportiMondo am = new AeroportiMondo();
		Aeroporto a1 = new Aeroporto("Torino");
		try{
		am.addAeroporto(a1);
		Aeroporto a = am.getAeroporto("Torino");
		assertEquals("Torino", a.getCode());
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
	}
	
	public void testAeroportiMondoNull() {
		AeroportiMondo am = new AeroportiMondo();
		Aeroporto a1 = new Aeroporto("Torino");
		try{
		am.addAeroporto(a1);
		Aeroporto a = am.getAeroporto("Torino");
		assertNotNull(a);		
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
		Aeroporto a = am.getAeroporto("Paris");
		assertNull(a);
		try{
			am.addAeroporto(a1);
			fail("Il codice non dovrebbe essere valido");
		}catch(InvalidCode ic){
		}
	}
	public void testAeroportiMondoRemove() {
		AeroportiMondo am = new AeroportiMondo();
		Aeroporto a1 = new Aeroporto("Torino");
		try{
		am.addAeroporto(a1);		
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
        Aeroporto a = am.getAeroporto("Torino");
        assertNotNull(a);
        am.removeAeroporto("Torino");
        a = am.getAeroporto("Torino");
        assertNull(a);
	}
}
