package br.com.zup.xyinc.config;

import br.com.zup.xyinc.common.builder.PoiBuilder;
import br.com.zup.xyinc.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * Classe de configuração da aplicação.
 */
@Configuration
public class XyincConfig {

    @Autowired
    private PoiRepository poiRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        PoiBuilder poiBuilder = new PoiBuilder();
        poiRepository.save(poiBuilder.withName("Lanchonete").withCoordinateX(27D).withCoordinateY(12D).build());
        poiRepository.save(poiBuilder.withName("Posto").withCoordinateX(31D).withCoordinateY(18D).build());
        poiRepository.save(poiBuilder.withName("Joalheria").withCoordinateX(15D).withCoordinateY(12D).build());
        poiRepository.save(poiBuilder.withName("Floricultura").withCoordinateX(19D).withCoordinateY(21D).build());
        poiRepository.save(poiBuilder.withName("Pub").withCoordinateX(12D).withCoordinateY(8D).build());
        poiRepository.save(poiBuilder.withName("Supermercado").withCoordinateX(23D).withCoordinateY(6D).build());
        poiRepository.save(poiBuilder.withName("Churrascaria").withCoordinateX(28D).withCoordinateY(2D).build());
        System.out.println("Aplicação iniciada!");
    }
}
