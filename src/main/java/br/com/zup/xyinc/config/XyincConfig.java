package br.com.zup.xyinc.config;

import br.com.zup.xyinc.common.builder.PoiBuilder;
import br.com.zup.xyinc.repository.PoiRepository;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.util.Assert;

import javax.validation.MessageInterpolator;
import java.util.Collections;

/**
 * Classe de configuração da aplicação.
 */
@Configuration
public class XyincConfig implements InitializingBean {

    @Autowired
    private PoiRepository poiRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadPointsOfInterestAfterInitialization() {
        PoiBuilder poiBuilder = new PoiBuilder();
        poiRepository.save(poiBuilder.withName("Lanchonete").withCoordinateX(27D).withCoordinateY(12D).build());
        poiRepository.save(poiBuilder.withName("Posto").withCoordinateX(31D).withCoordinateY(18D).build());
        poiRepository.save(poiBuilder.withName("Joalheria").withCoordinateX(15D).withCoordinateY(12D).build());
        poiRepository.save(poiBuilder.withName("Floricultura").withCoordinateX(19D).withCoordinateY(21D).build());
        poiRepository.save(poiBuilder.withName("Pub").withCoordinateX(12D).withCoordinateY(8D).build());
        poiRepository.save(poiBuilder.withName("Supermercado").withCoordinateX(23D).withCoordinateY(6D).build());
        poiRepository.save(poiBuilder.withName("Churrascaria").withCoordinateX(28D).withCoordinateY(2D).build());
    }

    @Bean
    public MessageInterpolator messageInterpolator() {
        return new ResourceBundleMessageInterpolator(new AggregateResourceBundleLocator(
                Collections.singletonList("xyinc_messages")));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(poiRepository, "poiRepository deve ser fornecido");
    }
}
