package id.co.practice.webflux.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceUtil {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        MessageSourceUtil.messageSource = messageSource;
    }

    public static String getMessage(Locale locale, String key) {
        return messageSource.getMessage(key, null, locale);
    }

    public static String getErrorMessage(Locale locale, String key) {
        return messageSource.getMessage("error.validation.".concat(key), null, locale);
    }

    public static String getErrorExceptionMessage(Locale locale, String key) {
        return messageSource.getMessage("error.exception.".concat(key), null, locale);
    }
}
