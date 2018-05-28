package br.com.zup.xyinc.business.service.core;

import br.com.zup.xyinc.business.service.PoiService;
import br.com.zup.xyinc.common.dto.PoiDto;
import br.com.zup.xyinc.common.entity.Poi;
import br.com.zup.xyinc.common.mapper.PoiMapper;
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

    private final PoiRepository repository;

    private final PoiMapper mapper;

    @Autowired
    public PoiServiceImpl(PoiRepository repository, PoiMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PoiDto> list() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public List<PoiDto> listNear(int x, int y, int radius) {
        return mapper.toDtoList(repository.findByPositionWithin(new Circle(x, y, radius)));
    }

    @Override
    @Transactional
    public PoiDto save(PoiDto poi) {
        return mapper.toDto(repository.save(mapper.toEntity(poi)));
    }
}
