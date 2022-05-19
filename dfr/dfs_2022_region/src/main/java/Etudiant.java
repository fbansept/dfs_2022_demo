public class Etudiant extends Personne {

    private int noteGlobal;

    public Etudiant(int age, int noteGlobal) {
        super(age);
        this.noteGlobal = noteGlobal;
    }

    public Etudiant(String nom, String prenom, int age, int noteGlobal) {
        super(nom, prenom, age);
        this.noteGlobal = noteGlobal;
    }

    @Override
    public String getNomComplet() {

        return "Etd."+ super.getNomComplet();

    }

    public int getNoteGlobal() {
        return noteGlobal;
    }

    public void setNoteGlobal(int noteGlobal) {
        this.noteGlobal = noteGlobal;
    }
}
