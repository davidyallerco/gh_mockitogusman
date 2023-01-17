package pe.parnertdigital.mockitoguzman.services;

import pe.parnertdigital.mockitoguzman.models.Examen;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepository;

import java.util.Optional;

public class ExamenServiceImpl implements ExamenService{


    private ExamenRepository examenRepository;

    public ExamenServiceImpl(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    @Override
    public Optional<Examen> buscarExamenPorNombre(String nombre) {
       return examenRepository.buscarTodos()
               .stream()
               .filter(e -> e.getNombre().contains(nombre))
               .findFirst();
    }
}
