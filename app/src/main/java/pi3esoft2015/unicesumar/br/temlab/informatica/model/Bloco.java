package pi3esoft2015.unicesumar.br.temlab.informatica.model;

import java.util.ArrayList;
import java.util.List;

public class Bloco {

    private long id;
    private final String name;
    private final List<Laboratorio> laboratorios;

    public Bloco(final String name) {
        this.name = name;
        this.laboratorios = new ArrayList<>();
    }

    public void addLaboratorio(final Laboratorio laboratorio) {
        this.laboratorios.add(laboratorio);
    }

    public String getName() {
        return name;
    }

    public List<Laboratorio> getLaboratorios() {
        return laboratorios;
    }
}
