package pe.parnertdigital.mockitoguzman.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.parnertdigital.mockitoguzman.models.Examen;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepository;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepositoryOtro;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamenServiceImplTest {

    @Test
    void buscarExamenPorNombre() {
        ExamenRepository repository = mock(ExamenRepository.class);//supuestamente usando la interface
        ExamenService service = new ExamenServiceImpl(repository);
        List<Examen> datosSimulados =    Arrays.asList(
                new Examen(5L, "Matematicas"),
                new Examen(6L, "Lenguaje"),
                new Examen(7L, "Historia")
        );
        when(repository.buscarTodos()).thenReturn(datosSimulados);
        Optional<Examen> examen = service.buscarExamenPorNombre("Matematicas");

        assertTrue(examen.isPresent());
        assertEquals(5L, examen.orElseThrow(null).getId());//null le puse en java 8
        assertEquals("Matematicas", examen.get().getNombre());//otra forma, tambien lo puedes hacer como arriba
    }
}