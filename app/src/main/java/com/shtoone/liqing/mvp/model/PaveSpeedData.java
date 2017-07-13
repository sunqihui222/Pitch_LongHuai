package com.shtoone.liqing.mvp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
public class PaveSpeedData {


    /**
     * data : [{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 13:38:02:865","tmpsudu":"0.313"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 13:34:55:050","tmpsudu":"0.336"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 13:23:17:492","tmpsudu":"0.112"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 13:09:49:826","tmpsudu":"0.281"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 11:16:28:134","tmpsudu":"0.335"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 10:18:38:086","tmpsudu":"0.301"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 10:06:10:198","tmpsudu":"0.333"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 10:02:52:209","tmpsudu":"0.343"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 09:32:50:304","tmpsudu":"0.339"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 09:26:48:742","tmpsudu":"0.336"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 09:18:37:212","tmpsudu":"0.273"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 09:12:33:194","tmpsudu":"0.263"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 08:45:15:770","tmpsudu":"0.338"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 08:32:05:435","tmpsudu":"0.283"},{"banhezhanminchen":"云湛高速6标1号摊铺机","tmpno":"yzgstp06_01","tmpshijian":"2017-06-01 07:17:12:437","tmpsudu":"0.123"}]
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
         * banhezhanminchen : 云湛高速6标1号摊铺机
         * tmpno : yzgstp06_01
         * tmpshijian : 2017-06-01 13:38:02:865
         * tmpsudu : 0.313
         */

        private String banhezhanminchen;
        private String tmpno;
        private String tmpshijian;
        private String tmpsudu;

        public String getBanhezhanminchen() {
            return banhezhanminchen;
        }

        public void setBanhezhanminchen(String banhezhanminchen) {
            this.banhezhanminchen = banhezhanminchen;
        }

        public String getTmpno() {
            return tmpno;
        }

        public void setTmpno(String tmpno) {
            this.tmpno = tmpno;
        }

        public String getTmpshijian() {
            return tmpshijian;
        }

        public void setTmpshijian(String tmpshijian) {
            this.tmpshijian = tmpshijian;
        }

        public String getTmpsudu() {
            return tmpsudu;
        }

        public void setTmpsudu(String tmpsudu) {
            this.tmpsudu = tmpsudu;
        }
    }
}
