package com.shree.intergration.common.config;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class BaseWebBindingInitializer implements WebBindingInitializer {

    @Override
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        datetimeFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(datetimeFormat, true));
        binder.registerCustomEditor(LocalDateTime.class, new CustomDateEditor(datetimeFormat, true));
    }
}
