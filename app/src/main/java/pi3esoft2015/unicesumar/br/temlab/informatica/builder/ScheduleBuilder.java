package pi3esoft2015.unicesumar.br.temlab.informatica.builder;

import pi3esoft2015.unicesumar.br.temlab.informatica.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScheduleBuilder {
    private List<Bloco> blocos;
    private List<Laboratorio> laboratorios;
    private List<Curso> cursos;
    private List<Professor> professores;
    private List<Turma> turmas;
    private List<Tipo> tipos;

    public ScheduleBuilder() {
        this.blocos = new ArrayList<>();
        this.laboratorios = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.turmas = new ArrayList<>();
        this.tipos = new ArrayList<>();
    }

    public ScheduleBuilder build(final List<String> results) throws Exception {
        Bloco bloco = null;
        Laboratorio laboratorio = null;

        for (int i = 0; i < results.size(); i++) {
            if (isBloco(results.get(i))) {
                bloco = new Bloco(results.get(i).replace("BLOCO", ""));
                blocos.add(bloco);

            } else if (isLab(results.get(i))) {
                assert bloco != null;

                final String name = results.get(i).replace("LAB", "").replace("LARC", "");
                laboratorio = new Laboratorio(bloco, name);
                bloco.addLaboratorio(laboratorio);
                laboratorios.add(laboratorio);

                if (isLivre(results.get(i + 1)) && isLivre(results.get(i + 2))) {
                    laboratorio.setCursoPrimeiroHorario(Curso.LIVRE);
                    laboratorio.setCursoSegundoHorario(Curso.LIVRE);
                } else if (isLivre(results.get(i + 1))) {
                    laboratorio.setCursoPrimeiroHorario(Curso.LIVRE);

                    final Curso curso = getCurso(results, i + 2, i + 3, i + 4);
                    laboratorio.setCursoSegundoHorario(curso);

                } else if (results.get(i + 4).contains("DISP")) {
                    final Curso curso = getCurso(results, i + 1, i + 2, i + 3);
                    laboratorio.setCursoPrimeiroHorario(curso);

                    laboratorio.setCursoSegundoHorario(Curso.LIVRE);
                } else {
                    final Curso cursoPrimeiro = getCurso(results, i + 1, i + 2, i + 3);
                    laboratorio.setCursoPrimeiroHorario(cursoPrimeiro);

                    final Curso cursoSegundo = getCurso(results, i + 4, i + 5, i + 6);
                    laboratorio.setCursoSegundoHorario(cursoSegundo);
                }
            }
        }

        return this;
    }

    public static boolean isBloco(final String result) {
        return result.contains("BLOCO");
    }

    public static boolean isLab(final String result) {
        return result.contains("LAB") || result.contains("Esc") || result.contains("LARC");
    }

    private boolean isLivre(final String result) {
        return result.contains("DISP");
    }

    public Curso getCurso(final List<String> results, final int... position) {
        assert null != position;
        assert position.length == 3;

        final Professor professor = new Professor(results.get(position[0]));
        professores.add(professor);
        final Turma turma = new Turma(results.get(position[1]));
        turmas.add(turma);
        final Tipo tipo = new Tipo(results.get(position[2]));
        tipos.add(tipo);

        final Curso curso = new Curso(professor, turma, tipo);
        cursos.add(curso);
        return curso;
    }

    public ArrayList<String> search(String key){
        ArrayList<String> newList = new ArrayList<>();
        ArrayList<String> first = new ArrayList<>();
        ArrayList<String> second = new ArrayList<>();

        for (Bloco b : blocos) {
            for (Laboratorio l : b.getLaboratorios()) {
                final Curso cursoPrimeiroHorario = l.getCursoPrimeiroHorario();
                if (cursoPrimeiroHorario.getTurma() != null && cursoPrimeiroHorario.getTurma().getName().equalsIgnoreCase(key)) {
                    first.add("Bloco "+ b.getName() + " Lab. " + l.getName());
                }

                final Curso cursoSegundoHorario = l.getCursoSegundoHorario();
                if (cursoSegundoHorario.getTurma() != null && cursoSegundoHorario.getTurma().getName().equalsIgnoreCase(key)) {
                    second.add("Bloco "+ b.getName() + " Lab. " + l.getName());
                }
            }
        }

        newList.add("Primeiro Horário");
        if (first.isEmpty()) {
            newList.add("Sem aulas em laboratório.");
        }else{
            for (String i : first){
                newList.add(i);
            }
        }
        newList.add("Segundo Horário");
        if (second.isEmpty()) {
            newList.add("Sem aulas em laboratório.");
        }else{
            for (String i : second){
                newList.add(i);
            }
        }

        return newList;
    }
}