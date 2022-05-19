package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        MoteurMock moteurTest = new MoteurMock();
        Voiture voitureTest = new Voiture(moteurTest);

        assertEquals("DEMARRAGE avec 0 cylindre", voitureTest.demarrer());

    }
}
