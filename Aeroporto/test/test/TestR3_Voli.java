package test;

import java.util.List;

import Aerei.AeroportiMondo;
import Aerei.Aeroporto;
import Aerei.Compagnia;
import Aerei.InvalidCode;
import Aerei.Volo;

import junit.framework.TestCase;


public class TestR3_Voli extends TestCase {

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
	
	public void testVolitoString() {
		try{
			al.addAereo("734", 120);
			al.addVolo("AZ321", "734", "Torino", "Paris", "Martedì");
			al.addVolo("AZ321", "345", "Torino", "Paris", "Giovedì");
			fail("Il codice non dovrebbe essere valido");
		}catch(InvalidCode ic){
		}
		try {
			lu.addAereo("734", 150);
			lu.addVolo("AZ321", "734", "Torino", "Paris", "Martedì");
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
		for(Volo v : lu.getVoli())
			assertEquals("Lufthansa;AZ321;734;Torino;Paris;Martedì;150",v.toString());
	}
	
	public void testVoliOrdinati() {
		try{
			al.addAereo("734", 120);
			al.addVolo("AZ322", "734", "Torino", "Paris", "Martedì");
			al.addVolo("AZ321", "734", "Paris","Torino" , "Lunedì");
			al.addVolo("AZ324", "734", "Torino", "Paris", "Giovedì");
			al.addVolo("AZ323", "734", "Paris","Torino" , "Mercoledì");
		}catch(InvalidCode ic){
		}

			assertEquals(al.getVoli().get(0).toString(), "Alitalia;AZ321;734;Paris;Torino;Lunedì;120");
			assertEquals(al.getVoli().get(1).toString(), "Alitalia;AZ322;734;Torino;Paris;Martedì;120");
			assertEquals(al.getVoli().get(2).toString(), "Alitalia;AZ323;734;Paris;Torino;Mercoledì;120");
			assertEquals(al.getVoli().get(3).toString(), "Alitalia;AZ324;734;Torino;Paris;Giovedì;120");
	}

	public void testVoliVuoti() {

		try{
			al.addAereo("734", 120);
			al.addVolo("AZ321", "734", "Torino", "Paris", "Martedì");
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
		try {
			al.cancelVolo("AZ321");
		}catch(InvalidCode ic){
			fail("Il codice dovrebbe essere valido");
		}
		assertTrue(al.getVoli().isEmpty());
	}
	
	   public void testArriviPartenzetoString() {
	        try{
	            lu.addAereo("734", 150);
	            lu.addVolo("LH359", "734", "Torino", "Paris", "Martedì");
	        }catch(InvalidCode ic){
	            fail("Il codice dovrebbe essere valido");
	        }
	        for(Volo v : am.getAeroporto("Torino").getPartenze())
	            assertEquals("Lufthansa;LH359;734;Torino;Paris;Martedì;150",v.toString());
	        for(Volo v : am.getAeroporto("Torino").getArrivi())
	            assertEquals("Lufthansa;LH359;734;Torino;Paris;Martedì;150",v.toString());
	    }
	    
	    public void testArriviOrdinati() {
	        try{
	            lu.addAereo("734", 150);
	            lu.addVolo("LH359", "734", "Torino", "Paris", "Martedì");
	            lu.addVolo("LH357", "734", "Torino", "Paris", "Lunedì");
	        }catch(InvalidCode ic){
	            fail("Il codice dovrebbe essere valido");
	        }
	        List<Volo> p = am.getAeroporto("Paris").getArrivi();
	        assertEquals(p.get(0).toString(),"Lufthansa;LH357;734;Torino;Paris;Lunedì;150" );
	        assertEquals(p.get(1).toString(),"Lufthansa;LH359;734;Torino;Paris;Martedì;150" );
	    }

	       public void testPartenzeOrdinate() {
	            try{
	                lu.addAereo("734", 150);
	                lu.addVolo("LH359", "734", "Torino", "Paris", "Martedì");
	                lu.addVolo("LH357", "734", "Torino", "Paris", "Lunedì");
	            }catch(InvalidCode ic){
	                fail("Il codice dovrebbe essere valido");
	            }
	            List<Volo> p = am.getAeroporto("Torino").getPartenze();
	            assertEquals(p.get(0).toString(),"Lufthansa;LH357;734;Torino;Paris;Lunedì;150" );
	            assertEquals(p.get(1).toString(),"Lufthansa;LH359;734;Torino;Paris;Martedì;150" );
	        }

	    public void testArriviPartenzeVuoti() {
	        AeroportiMondo am = new AeroportiMondo();
	        Compagnia lu = new Compagnia("Lufthansa", am);
	        Aeroporto a1 = new Aeroporto("Torino");
	        Aeroporto a2 = new Aeroporto("Paris");
	        try{
	            am.addAeroporto(a1);
	            am.addAeroporto(a2);
	            lu.addAereo("734", 150);
	            lu.addVolo("LH359", "734", "Torino", "Paris", "Martedì");
	            lu.cancelVolo("LH359");
	        }catch(InvalidCode ic){
	            fail("Il codice dovrebbe essere valido");
	        }
	        assertTrue(am.getAeroporto("Torino").getPartenze().isEmpty());
	        assertTrue(am.getAeroporto("Torino").getArrivi().isEmpty());
	    }
}
