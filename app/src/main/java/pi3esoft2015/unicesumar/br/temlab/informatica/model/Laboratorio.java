package pi3esoft2015.unicesumar.br.temlab.informatica.model;

public class Laboratorio {

    private long id;
    private final Bloco bloco;
    private final String name;
    private Curso cursoPrimeiroHorario;
    private Curso cursoSegundoHorario;

    public Laboratorio(final Bloco bloco, final String name) {
        this.bloco = bloco;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setCursoPrimeiroHorario(final Curso curso) {
        this.cursoPrimeiroHorario = curso;
    }

    public Curso getCursoPrimeiroHorario() {
        return cursoPrimeiroHorario;
    }

    public void setCursoSegundoHorario(final Curso curso) {
        this.cursoSegundoHorario = curso;
    }

    public Curso getCursoSegundoHorario() {
        return cursoSegundoHorario;
    }
}
