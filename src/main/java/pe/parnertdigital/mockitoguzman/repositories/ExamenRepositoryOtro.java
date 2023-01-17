package pe.parnertdigital.mockitoguzman.repositories;

import pe.parnertdigital.mockitoguzman.models.Examen;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamenRepositoryOtro  implements ExamenRepository{
    @Override
    public List<Examen> buscarTodos() {
        //demorando
        try {
            System.out.println("ExamenRepositoryOtro");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
