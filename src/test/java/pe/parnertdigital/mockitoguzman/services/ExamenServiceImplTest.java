package pe.parnertdigital.mockitoguzman.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {

     @Mock
     ExamenRepository repository;

    @Mock
    PreguntaRespository preguntaRespository;

     @InjectMocks
     ExamenServiceImpl service;




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

        when(repository.buscarTodos()).thenReturn(datosSimulados);
        Optional<Examen> examen = service.buscarExamenPorNombre("Matematicas");

        assertFalse(examen.isPresent());
    }

    @Test
    void testPreguntasExamen() {
        when(repository.buscarTodos()).thenReturn(Datos.DATOSSIMULADOS);
        when(preguntaRespository.buscarPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTASDATOSSIMULADOS);
        Examen examen = service.buscarExamenPorNombreConPreguntas("Matematicas");
        assertEquals(5, examen.getPreguntas().size());//cantidad
        assertTrue(examen.getPreguntas().contains("aritmetica"));
    }

    //verificar si  se ejecutaron los metodos en un determinado metodo
    @Test
    void testPreguntasExamenVerificar() {
        when(repository.buscarTodos()).thenReturn(Datos.DATOSSIMULADOS);
        when(preguntaRespository.buscarPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTASDATOSSIMULADOS);
        Examen examen = service.buscarExamenPorNombreConPreguntas("Matematicas");
        assertEquals(5, examen.getPreguntas().size());//cantidad
        assertTrue(examen.getPreguntas().contains("aritmetica"));
        verify(repository).buscarTodos();
        verify(preguntaRespository).buscarPreguntasPorExamenId(5L);
    }

    @Test
    void testNoExisteExamenVerify() {
        when(repository.buscarTodos()).thenReturn(Datos.DATOSSIMULADOS);
        when(preguntaRespository.buscarPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTASDATOSSIMULADOS);
        Examen examen = service.buscarExamenPorNombreConPreguntas("Matematicas");
        //assertNull(examen);
        verify(repository).buscarTodos();
        verify(preguntaRespository).buscarPreguntasPorExamenId(5L);
    }


    @Test
    void testGuardarExamen() {
        when(repository.guardar(any(Examen.class))).thenReturn(Datos.EXAMEN);
        Examen examen = service.guardar(Datos.EXAMEN);
        assertNotNull(examen.getId());
        assertEquals(8L, examen.getId());
        assertEquals("Fisica", examen.getNombre());

        verify(repository).guardar(any(Examen.class));
        verify(preguntaRespository).guardarVarias(anyList());
    }
}