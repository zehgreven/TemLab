package pi3esoft2015.unicesumar.br.temlab.informatica.model;

public class Professor {

    private long id;
    private final String name;

    public Professor(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
