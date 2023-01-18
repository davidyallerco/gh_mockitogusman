package pe.parnertdigital.mockitoguzman.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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
    void testManejoException() {
        when(repository.buscarTodos()).thenReturn(Datos.EXAMENES_ID_NULL);
        when(preguntaRespository.buscarPreguntasPorExamenId(isNull())).thenThrow(IllegalArgumentException.class);
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            service.buscarExamenPorNombreConPreguntas("Matematicas");
        });
        assertEquals(IllegalArgumentException.class, exception.getClass());
        verify(repository).buscarTodos();
        verify(preguntaRespository).buscarPreguntasPorExamenId(isNull());
    }


    //validaciones personalizadas

    @Test
    void testArgumentosMatcher() {
        when(repository.buscarTodos()).thenReturn(Datos.EXAMENESDATOSSIMULADOS);
        when(preguntaRespository.buscarPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTASDATOSSIMULADOS);
        //llamamos al service
        service.buscarExamenPorNombreConPreguntas("Matematicas");

        //verificamos
        verify(repository).buscarTodos();
        //argThat es propia de ArgumenMatcher(forma estatica), aunque tambien lo tiene mockito
        //formas de usar ArgumentMatchers.argThat(), argThat() , Mockito.argThat()
        verify(preguntaRespository).buscarPreguntasPorExamenId(argThat(arg -> arg != null && arg.equals(5L)));//validar que el numero que se pasa se 5
        //si pones 6L sale error porque 6 no pertenece a Matematicas
        //tambien que sea distinto de null sale ok, si cambias los DATOS NULL no pasara la prueba
    }

    //igual al de arriba
    @Test
    void testArgumentosMatcher2() {
        when(repository.buscarTodos()).thenReturn(Datos.EXAMENES_ID_NEGATIVOS_DATOSSIMULADOS);
        when(preguntaRespository.buscarPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTASDATOSSIMULADOS);
        service.buscarExamenPorNombreConPreguntas("Matematicas");
        verify(repository).buscarTodos();
        verify(preguntaRespository).buscarPreguntasPorExamenId(argThat(new MiArgsMatchers()));
    }

    @Test
    void testArgumentosMatcher3() {
        when(repository.buscarTodos()).thenReturn(Datos.EXAMENES_ID_NEGATIVOS_DATOSSIMULADOS);
        when(preguntaRespository.buscarPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTASDATOSSIMULADOS);
        service.buscarExamenPorNombreConPreguntas("Matematicas");
        verify(repository).buscarTodos();
        verify(preguntaRespository).buscarPreguntasPorExamenId(argThat((argument)->argument != null && argument > 0));
        //el problema de lambda es que solo validad mas muestra un mensaje personalizado
    }

    //validaciones personalizadas con una clase
    public static class MiArgsMatchers implements ArgumentMatcher<Long>{

        private Long argument;//sera usado en metodo toString, argumento es un numero
        @Override
        public boolean matches(Long argument) {
            this.argument = argument;
            //validamos que sea distinto de null y mayor a cero
            return argument != null && argument > 0;
        }



        //se puede personalizar el mensaje de error

        @Override
        public String toString() {
            return "es par un mensaje personalizado de error " +
                    " que imprime mockito en caso de que falle el test " +
                     argument +" debe ser un entero positivo";
        }


    }//fin de clase

    //capturar el argumento

    @Test
    void testArgumentCaptor() {
        when(repository.buscarTodos()).thenReturn(Datos.EXAMENESDATOSSIMULADOS);
        //when(preguntaRespository.buscarPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTASDATOSSIMULADOS);
        service.buscarExamenPorNombreConPreguntas("Matematicas");

        //se necesita instanciar una clase argumentCaptor
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(preguntaRespository).buscarPreguntasPorExamenId(captor.capture());

        assertEquals(5L, captor.getValue());
    }

}