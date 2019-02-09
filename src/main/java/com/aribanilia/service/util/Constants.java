package com.aribanilia.service.util;

public class Constants {

    public enum RESPONSE {
        APPROVED("00", "Approved"),
        INVALID_ACCOUNT_NOT_FOUND("13", "Account Not Found"),
        ;

        private String code, desc;

        RESPONSE(String code, String desc) {
            setCode(code);
            setDesc(desc);
        }

        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDesc() {
            return desc;
        }
        public void setDesc(String name) {
            this.desc = name;
        }
    }
}
