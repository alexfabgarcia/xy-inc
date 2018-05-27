package br.com.zup.xyinc.common.builder;

import br.com.zup.xyinc.common.entity.Poi;
import org.apache.commons.lang3.builder.Builder;
import org.springframework.data.geo.Point;

/**
 * Builder que facilita a construção de POIs.
 */
public class PoiBuilder implements Builder<Poi> {

    private String name;

    private Double x;

    private Double y;

    public PoiBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PoiBuilder withCoordinateX(Double x) {
        this.x = x;
        return this;
    }

    public PoiBuilder withCoordinateY(Double y) {
        this.y = y;
        return this;
    }

    @Override
    public Poi build() {
        Poi poi = new Poi();
        poi.setName(this.name);
        if (x != null & y != null) {
            poi.setPosition(new Point(x, y));
        }
        return poi;
    }
}
