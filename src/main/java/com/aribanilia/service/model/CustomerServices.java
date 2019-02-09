package com.aribanilia.service.model;

import com.aribanilia.service.dto.ResponseService;
import com.aribanilia.service.dto.customer.RestCustomerAddRequest;
import com.aribanilia.service.dto.customer.RestCustomerDeleteRequest;
import com.aribanilia.service.dto.customer.RestCustomerInquiryRequest;
import com.aribanilia.service.dto.customer.RestCustomerUpdateRequest;
import com.aribanilia.service.entity.TblCustomer;
import com.aribanilia.service.exception.RestAccountNotFoundException;
import com.aribanilia.service.util.Constants;
import com.aribanilia.service.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices {

    @Autowired private CustomerDao customerDao;

    private final static Logger logger = LoggerFactory.getLogger(CustomerServices.class);

    public ResponseService addCustomer(RestCustomerAddRequest req) throws Exception {
        logger.info("Start Services addCustomer : " + req.getName() + "-" + req.getBirthDate());
        ResponseService responseService = new ResponseService();
        try {
            TblCustomer customer = new TblCustomer();
            customer.setName(req.getName().toUpperCase());
            customer.setBirthDate(req.getBirthDate());
            customerDao.save(customer);
            ResponseUtil.setResponse(responseService, Constants.RESPONSE.APPROVED, customer);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.info("End Services addCustomer : " + req.getName() + "-" + req.getBirthDate());
        return responseService;
    }

    public ResponseService updateCustomer(RestCustomerUpdateRequest req) throws Exception {
        logger.info("Start Services updateCustomer : " + req.getId() + "-" + req.getName() + "-" + req.getBirthDate());
        ResponseService responseService = new ResponseService();
        try {
            TblCustomer customer = customerDao.findById(Long.parseLong(req.getId())).orElseThrow(() -> new RestAccountNotFoundException("Customer : " + req.getId() + " not found"));
            customer.setName(req.getName().toUpperCase());
            customer.setBirthDate(req.getBirthDate());
            customerDao.save(customer);
            ResponseUtil.setResponse(responseService, Constants.RESPONSE.APPROVED, customer);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.info("End Services updateCustomer : " + req.getId() + "-" + req.getName() + "-" + req.getBirthDate());
        return responseService;
    }

    public ResponseService deleteCustomer(RestCustomerDeleteRequest req) throws Exception {
        logger.info("Start Services deleteCustomer : " + req.getId());
        ResponseService responseService = new ResponseService();
        try {
            TblCustomer customer = customerDao.findById(Long.parseLong(req.getId())).orElseThrow(() -> new RestAccountNotFoundException("Customer : " + req.getId() + " not found"));
            customerDao.delete(customer);
            ResponseUtil.setResponse(responseService, Constants.RESPONSE.APPROVED, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.info("End Services deleteCustomer : " + req.getId());
        return responseService;
    }

    public ResponseService inquiryCustomer(RestCustomerInquiryRequest req) throws Exception {
        logger.info("Start Services inquiryCustomer : " + req.getName() + "-" + req.getBirthDate());
        ResponseService responseService = new ResponseService();
        try {
            List<TblCustomer> listCust;
            if (req.getBirthDate() != null && !"".equals(req.getBirthDate())) {
                String name = req.getName().toUpperCase() + "%";
                listCust = customerDao.findByUsernameAndBirthDate(name, req.getBirthDate());
            } else {
                String name = req.getName().toUpperCase() + "%";
                listCust = customerDao.findByUsername(name);
            }
            if (listCust.size() == 0) {
                ResponseUtil.setResponse(responseService, Constants.RESPONSE.INVALID_ACCOUNT_NOT_FOUND, null);
                return responseService;
            }
            ResponseUtil.setResponse(responseService, Constants.RESPONSE.APPROVED, listCust);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.info("End Services inquiryCustomer : " + req.getName() + "-" + req.getBirthDate());
        return responseService;
    }

}
