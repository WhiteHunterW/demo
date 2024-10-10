package com.example.biz.provider;

import javax.validation.Configuration;
import javax.validation.ValidatorFactory;
import javax.validation.spi.BootstrapState;
import javax.validation.spi.ConfigurationState;
import javax.validation.spi.ValidationProvider;

/**
 * @author wenzeng
 * @date 2024/9/6
 */
public class ParamProvider implements ValidationProvider {

    @Override
    public Configuration createSpecializedConfiguration(BootstrapState state) {
        return null;
    }

    @Override
    public Configuration<?> createGenericConfiguration(BootstrapState state) {
        return null;
    }

    @Override
    public ValidatorFactory buildValidatorFactory(ConfigurationState configurationState) {
        return null;
    }
}
