package com.aribanilia.service.util;

import com.aribanilia.service.dto.ResponseService;
import org.slf4j.Logger;
import org.springframework.validation.BindingResult;

public class ValidationHelper {

    public static boolean fieldValidation(BindingResult result, ResponseService restService, Logger logger){
        if (result.hasErrors()) {
            restService.setResponseCode(Constants.RESPONSE.WRONG_FORMAT.getCode());
            restService.setResponseDesc("Format Data field  " + result.getFieldError().getField()  + " tidak benar ");
            logger.error("Format Data field  " + result.getFieldError().getField()  + " tidak benar ");
            return false;
        }
        return true;
    }
}
