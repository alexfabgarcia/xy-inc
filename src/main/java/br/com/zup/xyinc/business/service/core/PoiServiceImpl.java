package br.com.zup.xyinc.business.service.core;

import br.com.zup.xyinc.business.service.PoiService;
import br.com.zup.xyinc.common.entity.Poi;
import br.com.zup.xyinc.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementação padrão de {@link PoiService}.
 */
@Service
public class PoiServiceImpl implements PoiService {

    private final PoiRepository poiRepository;

    @Autowired
    public PoiServiceImpl(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Poi> list() {
        return poiRepository.findAll();
    }

    @Override
    public List<Poi> listNear(int x, int y, int radius) {
        return poiRepository.findByPositionWithin(new Circle(x, y, radius));
    }

    @Override
    @Transactional
    public Poi save(Poi poi) {
        return poiRepository.save(poi);
    }
}
