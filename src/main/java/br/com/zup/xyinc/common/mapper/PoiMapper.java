package br.com.zup.xyinc.common.mapper;

import br.com.zup.xyinc.common.dto.PoiDto;
import br.com.zup.xyinc.common.entity.Poi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * {@link Mapper} responsável por converter a entidade {@link Poi} em um DTO {@link PoiDto}, e vice e versa.
 */
@Mapper(componentModel = "spring")
public interface PoiMapper extends MapperEntityDto<Poi, PoiDto> {

    @Override
    @Mappings({
            @Mapping(target = "x", source = "position.x"),
            @Mapping(target = "y", source = "position.y")
    })
    PoiDto toDto(Poi entidade);

    @Override
    @Mappings({
            @Mapping(target = "position", expression = "java(new Point(dto.getX(), dto.getY()))")
    })
    Poi toEntity(PoiDto dto);

}
