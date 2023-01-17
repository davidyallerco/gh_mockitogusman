package pe.parnertdigital.mockitoguzman.repositories;

import pe.parnertdigital.mockitoguzman.models.Examen;

import java.util.Arrays;
import java.util.List;

public class ExamenRepositoryImpl implements ExamenRepository{
    @Override
    public List<Examen> buscarTodos() {
        return Arrays.asList(
                new Examen(5L, "Matematicas"),
                new Examen(6L, "Lenguaje"),
                new Examen(7L, "Historia")
        );
    }
}
