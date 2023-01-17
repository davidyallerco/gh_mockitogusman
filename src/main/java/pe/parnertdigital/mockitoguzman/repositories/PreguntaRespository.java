package pe.parnertdigital.mockitoguzman.repositories;

import java.util.List;

public interface PreguntaRespository {
    List<String> buscarPreguntasPorExamenId(Long id);

}
