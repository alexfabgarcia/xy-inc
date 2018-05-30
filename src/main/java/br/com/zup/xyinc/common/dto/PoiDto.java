package br.com.zup.xyinc.common.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Data Transfer Object para POIs.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PoiDto {

    @NotEmpty(message = "xyinc.poi.name.mandatory")
    private String name;

    @NotNull(message = "xyinc.poi.x.mandatory")
    @PositiveOrZero(message = "xyinc.poi.x.non.negative")
    private Integer x;

    @NotNull(message = "xyinc.poi.y.mandatory")
    @PositiveOrZero(message = "xyinc.poi.y.non.negative")
    private Integer y;

}
