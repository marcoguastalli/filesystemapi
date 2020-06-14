package net.marco27.api.base.validation.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import net.marco27.api.base.validation.model.BaseValidationModel;
import net.marco27.api.base.validation.model.BaseValidationResult;

public class BaseValidationServiceTest {

    private static final BaseValidationService baseValidationService = new BaseValidationServiceImpl();

    @Test
    public void testValidate() {
        final BaseValidationResult result = baseValidationService.validate(new BaseValidationModel(StringUtils.SPACE));
        assertTrue(result.isValid());
    }
}
