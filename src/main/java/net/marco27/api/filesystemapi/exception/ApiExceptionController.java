package net.marco27.api.filesystemapi.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiExceptionController implements ErrorController {
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Invalid request!";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
