package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Assembleur assembleur = new Assembleur();

        System.out.println(assembleur.getVoiture().demarrer());
    }
}
