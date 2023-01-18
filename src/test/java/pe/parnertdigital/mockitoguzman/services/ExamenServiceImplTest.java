package pe.parnertdigital.mockitoguzman.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import pe.parnertdigital.mockitoguzman.models.Examen;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepository;
import pe.parnertdigital.mockitoguzman.repositories.ExamenRepositoryOtro;
import pe.parnertdigital.mockitoguzman.repositories.PreguntaRespository;

import java.util.*;

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
        //Given (dado un entorno de prueba)
        Examen nuevoExamen = Datos.EXAMEN;
        nuevoExamen.setPreguntas(Datos.PREGUNTASDATOSSIMULADOS);

        //id incremental
        when(repository.guardar(any(Examen.class))).then(new Answer<Examen>(){

            Long secuencia = 8L; //id que incremete desde el 8
            @Override
            public Examen answer(InvocationOnMock invocation) throws Throwable {
                Examen examen = invocation.getArgument(0);
                examen.setId(secuencia++);
                return examen;
            }
        });
        //When (cuando ejecutamos el metodo que queremos probar)
        Examen examen = service.guardar(nuevoExamen);

        //then (entonces validamos)
        assertNotNull(examen.getId());
        assertEquals(8L, examen.getId());
        assertEquals("Fisica", examen.getNombre());

        verify(repository).guardar(any(Examen.class));
        verify(preguntaRespository).guardarVarias(anyList());
    }


    @Test
    @Disabled
    void testManejoException() {
        when(repository.buscarTodos()).thenReturn(Datos.EXAMENES_ID_NULL);
        when(preguntaRespository.buscarPreguntasPorExamenId(null)).thenThrow(IllegalArgumentException.class);
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            service.buscarExamenPorNombreConPreguntas("Matematicas");
        });
        assertEquals(IllegalArgumentException.class, exception.getClass());
        verify(repository).buscarTodos();
        verify(preguntaRespository).buscarPreguntasPorExamenId(null);
    }


}