package pi3esoft2015.unicesumar.br.temlab.informatica.model;

public class Turma {

    private long id;
    private final String name;

    public Turma(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
