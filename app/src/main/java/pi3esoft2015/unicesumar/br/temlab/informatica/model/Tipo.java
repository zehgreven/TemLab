package pi3esoft2015.unicesumar.br.temlab.informatica.model;

public class Tipo {

    public final static Tipo LIVRE = new Tipo("LIVRE");

    private long id;
    private final String name;

    public Tipo(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
