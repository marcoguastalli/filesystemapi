package net.marco27.api.filesystemapi.validation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import net.marco27.api.filesystemapi.domain.PathFileToPrint;
import net.marco27.api.filesystemapi.validation.model.ValidationResult;

public class ValidationServiceTest {

    private static final String PATH_TO_PRINT = "/home/root/Pictures";
    private static final String FILE_TO_PRINT = "/home/root/Documents/pictures.json";

    private static final String PATH_TO_PRINT_INPUT = "home/root/Pictures";
    private static final String FILE_TO_PRINT_INPUT = "home/root/Documents/pictures.json";

    private static final ValidationService validationService = new ValidationServiceImpl();

    @Test
    public void testValidateInput() {
        final ValidationResult expected = new ValidationResult(Boolean.TRUE, StringUtils.EMPTY, HttpStatus.OK);
        final PathFileToPrint result = new PathFileToPrint.Builder(PATH_TO_PRINT_INPUT, FILE_TO_PRINT_INPUT).build();
        assertEquals(expected.toString(), validationService.validateInput(result).toString());
    }

}
