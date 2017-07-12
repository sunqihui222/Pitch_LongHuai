package com.shtoone.liqing.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

public class DeviceLocationData {
    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData(){
        return data;
    }

    public void setData(List<DataBean> data){
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String gpsid;
        private String donjin;
        private String dongjinbeiwei;
        private String shijian;
        private String banhezhanminchen;
        private String beiwei;

        public String getGpsid() {
            return gpsid;
        }

        public void setGpsid(String gpsid) {
            this.gpsid = gpsid;
        }

        public String getDonjin() {
            return donjin;
        }

        public void setDonjin(String donjin) {
            this.donjin = donjin;
        }

        public String getDongjinbeiwei() {
            return dongjinbeiwei;
        }

        public void setDongjinbeiwei(String dongjinbeiwei) {
            this.dongjinbeiwei = dongjinbeiwei;
        }

        public String getShijian() {
            return shijian;
        }

        public void setShijian(String shijian) {
            this.shijian = shijian;
        }

        public String getBanhezhanminchen() {
            return banhezhanminchen;
        }

        public void setBanhezhanminchen(String banhezhanminchen) {
            this.banhezhanminchen = banhezhanminchen;
        }

        public String getBeiwei() {
            return beiwei;
        }

        public void setBeiwei(String beiwei) {
            this.beiwei = beiwei;
        }
    }

}
