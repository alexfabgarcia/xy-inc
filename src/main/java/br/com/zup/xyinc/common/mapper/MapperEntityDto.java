package br.com.zup.xyinc.common.mapper;

/**
 * Interface base para mapeamento entre entidades e DTOs.
 * @param <E> Tipo da entidade.
 * @param <D> Tipo do DTO.
 */
public interface MapperEntityDto<E, D> {

    /**
     * Converte a entidade fonte no DTO destino.
     * @param entidade Entidade fonte.
     * @return DTO de destino.
     */
    D toDto(E entidade);

    /**
     * Converte o DTO na entidade destino.
     * @param dto DTO fonte.
     * @return Entidade destino.
     */
    E toEntity(D dto);

}
