package pi3esoft2015.unicesumar.br.temlab.informatica.model;

public class Curso {

    public final static Curso LIVRE = new Curso(Tipo.LIVRE);

    private long id;
    private final Professor professor;
    private final Turma turma;
    private final Tipo tipo;

    public Curso(final Tipo tipo) {
        this(null, null, tipo);
    }

    public Curso(final Professor professor, final Turma turma, final Tipo tipo) {
        this.professor = professor;
        this.turma = turma;
        this.tipo = tipo;
    }

    public boolean isLivre() {
        return Tipo.LIVRE.equals(tipo);
    }

    public Professor getProfessor() {
        return professor;
    }

    public Turma getTurma() {
        return turma;
    }

    public Tipo getTipo() {
        return tipo;
    }
}
