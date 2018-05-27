package br.com.zup.xyinc.business.service;

import br.com.zup.xyinc.common.entity.Poi;

import java.util.List;

/**
 * Service utilizado na aplicação de regras de negócio de pontos de interesse (POIs).
 */
public interface PoiService {

    /**
     * Método responsável pela listagem dos pontos de interesse existentes.
     * @return Lista de pontos de interesse.
     */
    List<Poi> list();

    /**
     * Método responságel pela listagem de pontos de interesse a partir de um ponto de referência e de um raio
     * (distância).
     * @param x Coordenada x do ponto de referência.
     * @param y Coordenada y do ponto de referência.
     * @param radius O raio para busca a partir do ponto de refência.
     * @return Lista de pontos de interesse próximos ao ponto de referência.
     */
    List<Poi> listNear(int x, int y, int radius);

    /**
     * Método utilizado na criação e edição de um ponto de interesse.
     * @param poi O ponto de interesse para persistência.
     * @return O ponto de interesse persistido.
     */
    Poi save(Poi poi);

}
