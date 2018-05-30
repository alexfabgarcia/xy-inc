package br.com.zup.xyinc.common.mapper;

import java.util.List;

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

    /**
     * Realiza a conversão de uma lista de entidades em uma lista de DTOs.
     * @param entidades A lista de entidades.
     * @return A lista de DTOs.
     */
    List<D> toDtoList(List<E> entidades);

}
