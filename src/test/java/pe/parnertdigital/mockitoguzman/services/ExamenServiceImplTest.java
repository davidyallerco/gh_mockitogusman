package pe.parnertdigital.mockitoguzman.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.parnertdigital.mockitoguzman.models.Examen;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamenServiceImplTest {

    @Test
    void buscarExamenPorNombre() {
        ExamenRepository repository = mock(ExamenRepository.class);//pasamos el nombre de la clase o interface a simular
        ExamenService service = new ExamenServiceImpl(repository);
        List<Examen> datosSimulados =    Arrays.asList(
                new Examen(5L, "Matematicas"),
                new Examen(6L, "Lenguaje"),
                new Examen(7L, "Historia")
        );//incluso lo eliminamos la clase examenRespositoryImpl
        //cuando se invoque el metodo del repository entonces devolver los datos simulados
        when(repository.buscarTodos()).thenReturn(datosSimulados);
        Examen examen = service.buscarExamenPorNombre("Matematicas");
        assertNotNull(examen);
        assertEquals(5L, examen.getId());
        assertEquals("Matematicas", examen.getNombre());
    }
}