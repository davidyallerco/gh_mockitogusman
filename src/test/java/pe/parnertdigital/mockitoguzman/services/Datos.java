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
}