package com.shtoone.liqing.mvp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */
public class LaboratoryFragmentData {


    /**
     * data : [{"banhezhanminchen":"云湛高速阳化段LM3标(中交二公局)","bsid":"8","countmxe":"65","countrhd":"97","countyd":"98","countzrd":"0"},{"banhezhanminchen":"云湛高速阳化段LM4标(广州公路工程公司)","bsid":"9","countmxe":"0","countrhd":"0","countyd":"0","countzrd":"0"},{"banhezhanminchen":"云湛高速阳化段LM5标(中铁十四局)","bsid":"10","countmxe":"0","countrhd":"0","countyd":"0","countzrd":"0"},{"banhezhanminchen":"云湛高速阳化段LM6标(中铁十一局)","bsid":"11","countmxe":"24","countrhd":"0","countyd":"0","countzrd":"0"}]
     * success : true
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * banhezhanminchen : 云湛高速阳化段LM3标(中交二公局)
         * bsid : 8
         * countmxe : 65
         * countrhd : 97
         * countyd : 98
         * countzrd : 0
         */

        private String banhezhanminchen;
        private String bsid;
        private String countmxe;
        private String countrhd;
        private String countyd;
        private String countzrd;

        public String getBanhezhanminchen() {
            return banhezhanminchen;
        }

        public void setBanhezhanminchen(String banhezhanminchen) {
            this.banhezhanminchen = banhezhanminchen;
        }

        public String getBsid() {
            return bsid;
        }

        public void setBsid(String bsid) {
            this.bsid = bsid;
        }

        public String getCountmxe() {
            return countmxe;
        }

        public void setCountmxe(String countmxe) {
            this.countmxe = countmxe;
        }

        public String getCountrhd() {
            return countrhd;
        }

        public void setCountrhd(String countrhd) {
            this.countrhd = countrhd;
        }

        public String getCountyd() {
            return countyd;
        }

        public void setCountyd(String countyd) {
            this.countyd = countyd;
        }

        public String getCountzrd() {
            return countzrd;
        }

        public void setCountzrd(String countzrd) {
            this.countzrd = countzrd;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "banhezhanminchen='" + banhezhanminchen + '\'' +
                    ", bsid='" + bsid + '\'' +
                    ", countmxe='" + countmxe + '\'' +
                    ", countrhd='" + countrhd + '\'' +
                    ", countyd='" + countyd + '\'' +
                    ", countzrd='" + countzrd + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LaboratoryFragmentData{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
