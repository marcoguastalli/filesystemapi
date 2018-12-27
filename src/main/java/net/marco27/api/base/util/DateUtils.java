package net.marco27.api.base.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateUtils {

    default String getCurrentTimestamp() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
    }

}
