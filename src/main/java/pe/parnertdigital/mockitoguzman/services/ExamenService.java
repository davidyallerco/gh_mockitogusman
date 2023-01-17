package pe.parnertdigital.mockitoguzman.services;

import pe.parnertdigital.mockitoguzman.models.Examen;

import java.util.Optional;

public interface ExamenService {
    Optional<Examen> buscarExamenPorNombre(String nombre);
    Examen buscarExamenPorNombreConPreguntas(String nombre);
}
