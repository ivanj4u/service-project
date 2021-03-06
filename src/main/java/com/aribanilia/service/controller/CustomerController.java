package com.aribanilia.service.controller;

import com.aribanilia.service.dto.ResponseService;
import com.aribanilia.service.dto.customer.RestCustomerAddRequest;
import com.aribanilia.service.dto.customer.RestCustomerDeleteRequest;
import com.aribanilia.service.dto.customer.RestCustomerInquiryRequest;
import com.aribanilia.service.dto.customer.RestCustomerUpdateRequest;
import com.aribanilia.service.entity.TblCustomer;
import com.aribanilia.service.model.CustomerServices;
import com.aribanilia.service.util.ValidationHelper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired private CustomerServices customerServices;

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @ApiOperation(value = "Add Customer", response = TblCustomer.class)
    @PreAuthorize("#oauth2.hasScope('INSERT')")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseService add(@RequestBody @Valid RestCustomerAddRequest req, BindingResult result) throws Exception {
        logger.info("Incoming Get customer/add/" + req.getName() + "-" + req.getBirthDate());
        ResponseService responseService = new ResponseService();

        if (!ValidationHelper.fieldValidation(result, responseService, logger)) {
            return responseService;
        }
        try {
            responseService = customerServices.addCustomer(req);
        } catch (Exception e) {
            throw e;
        }
        logger.info("OutGoing Get customer/add/" + req.getName() + "-" + req.getBirthDate());
        return responseService;
    }

    @ApiOperation(value = "Update Customer", response = TblCustomer.class)
    @PreAuthorize("#oauth2.hasScope('UPDATE')")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseService update(@RequestBody @Valid RestCustomerUpdateRequest req, BindingResult result) throws Exception {
        logger.info("Incoming Get customer/update/" + req.getName() + "-" + req.getBirthDate());
        ResponseService responseService = new ResponseService();

        if (!ValidationHelper.fieldValidation(result, responseService, logger)) {
            return responseService;
        }
        try {
            responseService = customerServices.updateCustomer(req);
        } catch (Exception e) {
            throw e;
        }
        logger.info("OutGoing Get customer/update/" + req.getName() + "-" + req.getBirthDate());
        return responseService;
    }

    @ApiOperation(value = "Delete Customer", response = Boolean.class)
    @PreAuthorize("#oauth2.hasScope('DELETE')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseService delete(@RequestBody @Valid RestCustomerDeleteRequest req, BindingResult result) throws Exception {
        logger.info("Incoming Get customer/delete/" + req.getId());
        ResponseService responseService = new ResponseService();

        if (!ValidationHelper.fieldValidation(result, responseService, logger)) {
            return responseService;
        }
        try {
            responseService = customerServices.deleteCustomer(req);
        } catch (Exception e) {
            throw e;
        }
        logger.info("OutGoing Get customer/inquiry/" + req.getId());
        return responseService;
    }

    @ApiOperation(value = "Inquiry Customer", response = TblCustomer.class)
    @PreAuthorize("#oauth2.hasScope('READ')")
    @RequestMapping(value = "/inquiry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseService inquiry(@RequestBody @Valid RestCustomerInquiryRequest req, BindingResult result) throws Exception {
        logger.info("Incoming Get customer/inquiry/" + req.getName() + "-" + req.getBirthDate());
        ResponseService responseService = new ResponseService();

        if (!ValidationHelper.fieldValidation(result, responseService, logger)) {
            return responseService;
        }
        try {
            responseService = customerServices.inquiryCustomer(req);
        } catch (Exception e) {
            throw e;
        }
        logger.info("OutGoing Get customer/inquiry/" + req.getName() + "-" + req.getBirthDate());
        return responseService;
    }

}
