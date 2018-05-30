package br.com.zup.xyinc.repository;

import br.com.zup.xyinc.common.builder.PoiBuilder;
import br.com.zup.xyinc.common.entity.Poi;
import org.springframework.data.geo.Shape;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository utilizado para maniputalção de {@link Poi}s.
 */
public interface PoiRepository extends Repository<Poi, Long> {

    /**
     * Retorna uma lista contendo todos os pontos de interesse.
     * @return Lista contendo todos pontos de interesse.
     */
    List<Poi> findAll();

    /**
     * Returna uma lista de pontos de interesse contidos dentro de uma região representada por um objeto {@link Shape}.
     * @param shape A forma contenedora dos pontos de interesses.
     * @return Lista de pontos de interesse contidos na região.
     */
    List<Poi> findByPositionWithin(Shape shape);

    /**
     * Persiste um realiza o merge de um determinado ponto de interesse.
     * @param poi O ponto de interesse.
     * @return O ponto de interesse persisido.
     */
    Poi save(Poi poi);

    /**
     * Método utilizado a fim de inicalizar dados de exemplo.
     */
    default void initSampleData() {
        PoiBuilder poiBuilder = new PoiBuilder();
        save(poiBuilder.withName("Lanchonete").withCoordinateX(27D).withCoordinateY(12D).build());
        save(poiBuilder.withName("Posto").withCoordinateX(31D).withCoordinateY(18D).build());
        save(poiBuilder.withName("Joalheria").withCoordinateX(15D).withCoordinateY(12D).build());
        save(poiBuilder.withName("Floricultura").withCoordinateX(19D).withCoordinateY(21D).build());
        save(poiBuilder.withName("Pub").withCoordinateX(12D).withCoordinateY(8D).build());
        save(poiBuilder.withName("Supermercado").withCoordinateX(23D).withCoordinateY(6D).build());
        save(poiBuilder.withName("Churrascaria").withCoordinateX(28D).withCoordinateY(2D).build());
    }

}
