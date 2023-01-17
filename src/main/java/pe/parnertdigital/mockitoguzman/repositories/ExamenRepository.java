package pe.parnertdigital.mockitoguzman.repositories;

import pe.parnertdigital.mockitoguzman.models.Examen;

import java.util.List;

public interface ExamenRepository {
    List<Examen> buscarTodos();
}
