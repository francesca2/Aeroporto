package test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestR1_Aeroporti.class);
        suite.addTestSuite(TestR2_Aerei.class);
        suite.addTestSuite(TestR3_Voli.class);
        suite.addTestSuite(TestR4_Prenotazioni.class);
        suite.addTestSuite(TestR5_Criticita.class);
        //$JUnit-END$
        return suite;
    }
}
