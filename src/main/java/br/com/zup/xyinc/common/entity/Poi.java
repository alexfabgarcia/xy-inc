package br.com.zup.xyinc.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Entitade que representa um ponto de interesse (POI).
 */
@Getter
@Setter
@NoArgsConstructor
@Document
public class Poi {

    @Id
    private String id;

    @NotNull(message = "xyinc.poi.name.mandatory")
    private String name;

    @NotNull(message = "xyinc.poi.position.mandatory")
    private Point position;

}
