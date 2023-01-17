package pe.parnertdigital.mockitoguzman.services;

import pe.parnertdigital.mockitoguzman.models.Examen;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepository;
import pe.parnertdigital.mockitoguzman.repositories.PreguntaRespository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements ExamenService{


    private ExamenRepository examenRepository;
    private PreguntaRespository preguntaRespository;

    public ExamenServiceImpl(ExamenRepository examenRepository, PreguntaRespository preguntaRespository) {
        this.examenRepository = examenRepository;
        this.preguntaRespository = preguntaRespository;
    }

    @Override
    public Optional<Examen> buscarExamenPorNombre(String nombre) {
       return examenRepository.buscarTodos()
               .stream()
               .filter(e -> e.getNombre().contains(nombre))
               .findFirst();
    }

    @Override
    public Examen buscarExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> examenOptional = buscarExamenPorNombre(nombre);
        Examen examen = null;
        if (examenOptional.isPresent()){
            examen = examenOptional.get();
            List<String> preguntas = preguntaRespository.buscarPreguntasPorExamenId(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }

    @Override
    public Examen guardar(Examen examen) {
        if (!examen.getPreguntas().isEmpty()){
            preguntaRespository.guardarVarias(examen.getPreguntas());
        }
        return examenRepository.guardar(examen);
    }
}
