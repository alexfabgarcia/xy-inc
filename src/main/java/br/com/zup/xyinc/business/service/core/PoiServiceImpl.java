package br.com.zup.xyinc.business.service.core;

import br.com.zup.xyinc.business.service.PoiService;
import br.com.zup.xyinc.business.service.validation.ValidationHelper;
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

    private final ValidationHelper validationHelper;

    @Autowired
    public PoiServiceImpl(PoiRepository repository, PoiMapper mapper, ValidationHelper validationHelper) {
        this.repository = repository;
        this.mapper = mapper;
        this.validationHelper = validationHelper;
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
    public PoiDto save(PoiDto poiDto) {
        Poi poi = mapper.toEntity(poiDto);
        validationHelper.validate(poi);
        return mapper.toDto(repository.save(poi));
    }
}
