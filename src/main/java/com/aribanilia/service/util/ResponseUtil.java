package com.aribanilia.service.util;

import com.aribanilia.service.dto.ResponseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponseUtil {

    public static ResponseService setResponse(ResponseService responseService, Constants.RESPONSE response, Object obj) throws Exception {
        responseService.setResponseCode(response.getCode());
        responseService.setResponseDesc(response.getDesc());
        if (obj != null && !"".equals(obj.toString())) {
            Gson gson = new GsonBuilder().create();
            String data = gson.toJson(obj);
            responseService.setData(data);
        }
        return responseService;
    }
}
