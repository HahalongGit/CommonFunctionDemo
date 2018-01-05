package com.kbb.kbbtest.bean;

/**
 * Created by 龙龙同学 on 2017/7/28.
 *
 * @ClassName: MobileAddress
 * @Description:
 * @Date 2017/7/28
 */

public class MobileAddress {

    /**
     * result : {"mobilenumber":"1302167","mobilearea":"山东 青岛市","mobiletype":"联通如意通卡","areacode":"0532","postcode":"266000"}
     * error_code : 0
     * reason : Succes
     */

    private ResultEntity result;
    private String error_code;
    private String reason;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultEntity getResult() {
        return result;
    }

    public String getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public static class ResultEntity {
        /**
         * mobilenumber : 1302167
         * mobilearea : 山东 青岛市
         * mobiletype : 联通如意通卡
         * areacode : 0532
         * postcode : 266000
         */

        private String mobilenumber;
        private String mobilearea;
        private String mobiletype;
        private String areacode;
        private String postcode;

        public void setMobilenumber(String mobilenumber) {
            this.mobilenumber = mobilenumber;
        }

        public void setMobilearea(String mobilearea) {
            this.mobilearea = mobilearea;
        }

        public void setMobiletype(String mobiletype) {
            this.mobiletype = mobiletype;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getMobilenumber() {
            return mobilenumber;
        }

        public String getMobilearea() {
            return mobilearea;
        }

        public String getMobiletype() {
            return mobiletype;
        }

        public String getAreacode() {
            return areacode;
        }

        public String getPostcode() {
            return postcode;
        }
    }
}
