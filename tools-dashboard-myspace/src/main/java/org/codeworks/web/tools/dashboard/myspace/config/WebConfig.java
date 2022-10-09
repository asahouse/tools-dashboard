package org.codeworks.web.tools.dashboard.myspace.config;

import org.apache.commons.lang3.StringUtils;
import org.codeworks.web.tools.dashboard.myspace.model.enumeration.Assert;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        Converter<String,Assert> assertConverter = new Converter<String, Assert>() {
            @Override
            public Assert convert(String s) {
                if(StringUtils.isEmpty(s)){
                    return null;
                }
                return Assert.parse(Byte.valueOf(s));
            }
        };
        registry.addConverter(assertConverter);

        super.addFormatters(registry);
    }
}
