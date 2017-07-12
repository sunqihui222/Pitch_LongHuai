package com.shtoone.liqing.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class DuctilityDetailBean {


    /**
     * data : [{"IS_TESTTIME":"2015-10-28 19:26","SHeader2":"沥青路面中面层（配合比用）","SHeader3":"SBS改性沥青","SHeader4":"黑色","avgvalue1":"363.67","biaozhunzhi1":"200.00","biaozhunzhi2":"10000.00","f_GUID":"4FB1BBBC2D154BB6A83C26421861726C","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ-009","isQualified":"合格","yandu11":"360.00","yandu12":"365.00","yandu13":"366.00"}]
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

    public boolean getSuccess() {
        return success;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * IS_TESTTIME : 2015-10-28 19:26
         * SHeader2 : 沥青路面中面层（配合比用）
         * SHeader3 : SBS改性沥青
         * SHeader4 : 黑色
         * avgvalue1 : 363.67
         * biaozhunzhi1 : 200.00
         * biaozhunzhi2 : 10000.00
         * f_GUID : 4FB1BBBC2D154BB6A83C26421861726C
         * f_SBBH : yzgslq03_01
         * header3 : 龙永高速35合同段
         * header5 : LY-GXLQ-009
         * isQualified : 合格
         * yandu11 : 360.00
         * yandu12 : 365.00
         * yandu13 : 366.00
         */

        private String IS_TESTTIME;
        private String SHeader2;
        private String SHeader3;
        private String SHeader4;
        private String avgvalue1;
        private String biaozhunzhi1;
        private String biaozhunzhi2;
        private String f_GUID;
        private String f_SBBH;
        private String header3;
        private String header5;
        private String isQualified;
        private String yandu11;
        private String yandu12;
        private String yandu13;

        public void setIS_TESTTIME(String IS_TESTTIME) {
            this.IS_TESTTIME = IS_TESTTIME;
        }

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

        public void setBiaozhunzhi1(String biaozhunzhi1) {
            this.biaozhunzhi1 = biaozhunzhi1;
        }

        public void setBiaozhunzhi2(String biaozhunzhi2) {
            this.biaozhunzhi2 = biaozhunzhi2;
        }

        public void setF_GUID(String f_GUID) {
            this.f_GUID = f_GUID;
        }

        public void setF_SBBH(String f_SBBH) {
            this.f_SBBH = f_SBBH;
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

        public void setYandu11(String yandu11) {
            this.yandu11 = yandu11;
        }

        public void setYandu12(String yandu12) {
            this.yandu12 = yandu12;
        }

        public void setYandu13(String yandu13) {
            this.yandu13 = yandu13;
        }

        public String getIS_TESTTIME() {
            return IS_TESTTIME;
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

        public String getBiaozhunzhi1() {
            return biaozhunzhi1;
        }

        public String getBiaozhunzhi2() {
            return biaozhunzhi2;
        }

        public String getF_GUID() {
            return f_GUID;
        }

        public String getF_SBBH() {
            return f_SBBH;
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

        public String getYandu11() {
            return yandu11;
        }

        public String getYandu12() {
            return yandu12;
        }

        public String getYandu13() {
            return yandu13;
        }
    }
}
