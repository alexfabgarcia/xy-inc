package br.com.zup.xyinc.repository;

import br.com.zup.xyinc.common.entity.Poi;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository utilizado para maniputalção de {@link Poi}s.
 */
public interface PoiRepository extends Repository<Poi, Long> {

    List<Poi> findAll();

    Poi save(Poi poi);

}
