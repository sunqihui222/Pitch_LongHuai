package com.shtoone.liqing.mvp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */
public class PaveTemptureData {


    /**
     * data : [{"tmpdata":"157.7","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 11:31:01"},{"tmpdata":"160.1","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 11:30:09"},{"tmpdata":"161.3","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 11:24:38"},{"tmpdata":"167.0","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 11:22:08"},{"tmpdata":"168.7","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 11:19:33"},{"tmpdata":"162.9","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 11:17:26"},{"tmpdata":"148.4","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 11:07:06"},{"tmpdata":"156.4","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 11:03:50"},{"tmpdata":"158.5","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 10:19:19"},{"tmpdata":"163.7","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 09:53:03"},{"tmpdata":"155.6","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 09:48:52"},{"tmpdata":"157.1","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 09:43:48"},{"tmpdata":"158.5","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 09:41:28"},{"tmpdata":"160.9","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 09:37:25"},{"tmpdata":"156.1","tmpno":"yzgstp06_01","tmpshijian":"2017-05-31 09:34:16"}]
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
         * tmpdata : 157.7
         * tmpno : yzgstp06_01
         * tmpshijian : 2017-05-31 11:31:01
         */

        private String tmpdata;
        private String tmpno;
        private String tmpshijian;

        public String getTmpdata() {
            return tmpdata;
        }

        public void setTmpdata(String tmpdata) {
            this.tmpdata = tmpdata;
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "tmpdata='" + tmpdata + '\'' +
                    ", tmpno='" + tmpno + '\'' +
                    ", tmpshijian='" + tmpshijian + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PaveTemptureData{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
