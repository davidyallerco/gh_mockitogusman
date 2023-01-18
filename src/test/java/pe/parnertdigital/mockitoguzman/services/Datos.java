package pe.parnertdigital.mockitoguzman.services;

import pe.parnertdigital.mockitoguzman.models.Examen;

import java.util.Arrays;
import java.util.List;

public class Datos {

   public final static List<Examen> DATOSSIMULADOS =    Arrays.asList(
            new Examen(5L, "Matematicas"),
            new Examen(6L, "Lenguaje"),
            new Examen(7L, "Historia")
    );
    public final static List<Examen> EXAMENES_ID_NULL =    Arrays.asList(
            new Examen(null, "Matematicas"),
            new Examen(null, "Lenguaje"),
            new Examen(null, "Historia")
    );
    public final static List<Examen> EXAMENESDATOSSIMULADOS =    Arrays.asList(
            new Examen(5L, "Matematicas"),
            new Examen(6L, "Lenguaje"),
            new Examen(7L, "Historia")
    );


   public final static List<String>  PREGUNTASDATOSSIMULADOS = Arrays.asList("aritmetica","integrales",
           "derivadas","trigonometria","geometria");

   public final static Examen EXAMEN = new Examen(null, "Fisica");//el id debe ser automatica

    public final static List<Examen> EXAMENES_ID_NEGATIVOS_DATOSSIMULADOS =    Arrays.asList(
            new Examen(-5L, "Matematicas"),
            new Examen(-6L, "Lenguaje"),
            new Examen(null, "Historia")
    );


}
