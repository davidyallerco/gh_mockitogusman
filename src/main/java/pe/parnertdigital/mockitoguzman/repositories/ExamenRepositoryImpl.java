package pe.parnertdigital.mockitoguzman.repositories;

import pe.parnertdigital.mockitoguzman.models.Examen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExamenRepositoryImpl implements ExamenRepository{
    @Override
    public List<Examen> buscarTodos() {
       /* return Arrays.asList(
                new Examen(5L, "Matematicas"),
                new Examen(6L, "Lenguaje"),
                new Examen(7L, "Historia")
        );
        */
        //si queremos probar una lista vacia, tendremos que modificar, con mockito evitariamos esto
        return Collections.emptyList();
    }
}
