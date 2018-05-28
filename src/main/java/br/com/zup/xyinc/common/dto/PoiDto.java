package br.com.zup.xyinc.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Data Transfer Object para POIs.
 */
@Getter
@Setter
public class PoiDto {

    @NotEmpty
    private String name;

    @NotNull
    @PositiveOrZero
    private Integer x;

    @NotNull
    @PositiveOrZero
    private Integer y;

}
