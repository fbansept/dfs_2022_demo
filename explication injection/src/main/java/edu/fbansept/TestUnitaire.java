package edu.fbansept;

public class TestUnitaire {

    public static void main(String[] args) throws Exception {
        testClasseVoiture();

        System.out.println("Tout c'est bien passé");
    }

    public static void testClasseVoiture() throws Exception {

        IMoteur moteurTest = new MoteurMock();
        Voiture voitureTest = new Voiture(moteurTest);

        if(!voitureTest.demarrer().equals("DEMARRAGE avec 0 cylindres")) {
            throw new Exception("testClasseVoiture echoué");
        }
    }
}
