package com.shtoone.liqing.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class SofteningPointDetailBean {


    /**
     * data : [{"SHeader2":"上面层","SHeader3":"SBS(I-D)改性沥青","SHeader4":"黑色，可检","avgvalue1":"81.80","biaoZhun1":"75.00","header3":"湖南省龙永高速公路","header5":"20160720","isQualified":"合格","is_testtime":"2016-07-20 10:22","ruanhuadian1":"81.70","ruanhuadian2":"81.90"}]
     * success : true
     */

    private boolean success;
    private List<DataEntity> data;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * SHeader2 : 上面层
         * SHeader3 : SBS(I-D)改性沥青
         * SHeader4 : 黑色，可检
         * avgvalue1 : 81.80
         * biaoZhun1 : 75.00
         * header3 : 湖南省龙永高速公路
         * header5 : 20160720
         * isQualified : 合格
         * is_testtime : 2016-07-20 10:22
         * ruanhuadian1 : 81.70
         * ruanhuadian2 : 81.90
         */

        private String SHeader2;
        private String SHeader3;
        private String SHeader4;
        private String avgvalue1;
        private String biaoZhun1;
        private String header3;
        private String header5;
        private String isQualified;
        private String is_testtime;
        private String ruanhuadian1;
        private String ruanhuadian2;

        public void setSHeader2(String SHeader2) {
            this.SHeader2 = SHeader2;
        }

        public void setSHeader3(String SHeader3) {
            this.SHeader3 = SHeader3;
        }

        public void setSHeader4(String SHeader4) {
            this.SHeader4 = SHeader4;
        }

        public void setAvgvalue1(String avgvalue1) {
            this.avgvalue1 = avgvalue1;
        }

        public void setBiaoZhun1(String biaoZhun1) {
            this.biaoZhun1 = biaoZhun1;
        }

        public void setHeader3(String header3) {
            this.header3 = header3;
        }

        public void setHeader5(String header5) {
            this.header5 = header5;
        }

        public void setIsQualified(String isQualified) {
            this.isQualified = isQualified;
        }

        public void setIs_testtime(String is_testtime) {
            this.is_testtime = is_testtime;
        }

        public void setRuanhuadian1(String ruanhuadian1) {
            this.ruanhuadian1 = ruanhuadian1;
        }

        public void setRuanhuadian2(String ruanhuadian2) {
            this.ruanhuadian2 = ruanhuadian2;
        }

        public String getSHeader2() {
            return SHeader2;
        }

        public String getSHeader3() {
            return SHeader3;
        }

        public String getSHeader4() {
            return SHeader4;
        }

        public String getAvgvalue1() {
            return avgvalue1;
        }

        public String getBiaoZhun1() {
            return biaoZhun1;
        }

        public String getHeader3() {
            return header3;
        }

        public String getHeader5() {
            return header5;
        }

        public String getIsQualified() {
            return isQualified;
        }

        public String getIs_testtime() {
            return is_testtime;
        }

        public String getRuanhuadian1() {
            return ruanhuadian1;
        }

        public String getRuanhuadian2() {
            return ruanhuadian2;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "SHeader2='" + SHeader2 + '\'' +
                    ", SHeader3='" + SHeader3 + '\'' +
                    ", SHeader4='" + SHeader4 + '\'' +
                    ", avgvalue1='" + avgvalue1 + '\'' +
                    ", biaoZhun1='" + biaoZhun1 + '\'' +
                    ", header3='" + header3 + '\'' +
                    ", header5='" + header5 + '\'' +
                    ", isQualified='" + isQualified + '\'' +
                    ", is_testtime='" + is_testtime + '\'' +
                    ", ruanhuadian1='" + ruanhuadian1 + '\'' +
                    ", ruanhuadian2='" + ruanhuadian2 + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SofteningPointDetailBean{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
