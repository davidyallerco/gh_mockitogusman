package pe.parnertdigital.mockitoguzman.services;

import pe.parnertdigital.mockitoguzman.models.Examen;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepository;

import java.util.Optional;

public class ExamenServiceImpl implements ExamenService{

    //inyeccion de dependencias es por constructor
    private ExamenRepository examenRepository;

    //pasamos esta dependencia en el constructor
    public ExamenServiceImpl(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }




    @Override
    public Examen buscarExamenPorNombre(String nombre) {
       Optional<Examen> examenOptional = examenRepository.buscarTodos()
               .stream()
               .filter(e -> e.getNombre().contains(nombre))
               .findFirst();
       //examen que queremos devolver
       Examen examen = null;
       if (examenOptional.isPresent()){
           //examen = examenOptional.get(); //opcion 1
           examen = examenOptional.orElseThrow(null); //opcion 2 mejorada,David yo le puse null
       }
       return examen;
    }
}
