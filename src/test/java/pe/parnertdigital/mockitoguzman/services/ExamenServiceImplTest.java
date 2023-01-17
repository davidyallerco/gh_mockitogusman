package pe.parnertdigital.mockitoguzman.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.parnertdigital.mockitoguzman.models.Examen;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepository;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepositoryOtro;
import pe.parnertdigital.mockitoguzman.repositories.PreguntaRespository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamenServiceImplTest {

     ExamenRepository repository;
     ExamenService service;

     PreguntaRespository preguntaRespository;
    @BeforeEach
    void setUp() {
         repository = mock(ExamenRepository.class);
         preguntaRespository = mock(PreguntaRespository.class);
         service = new ExamenServiceImpl(repository, preguntaRespository);
    }

    @Test
    void buscarExamenPorNombreTest() {
        when(repository.buscarTodos()).thenReturn(Datos.DATOSSIMULADOS);
        Optional<Examen> examen = service.buscarExamenPorNombre("Matematicas");

        assertTrue(examen.isPresent());
        assertEquals(5L, examen.orElseThrow(null).getId());
        assertEquals("Matematicas", examen.get().getNombre());
    }

    @Test
    void buscarExamenPorNombreListaVaciaTest() {

        List<Examen> datosSimulados = Collections.emptyList();

        when(repository.buscarTodos()).thenReturn(Datos.DATOSSIMULADOS);
        Optional<Examen> examen = service.buscarExamenPorNombre("Matematicas");

        assertFalse(examen.isPresent());
    }

    
}