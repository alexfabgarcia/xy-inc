package br.com.zup.xyinc.config;

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
        poiRepository.initSampleData();
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
