package com.shtoone.liqing.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class AsphaltPenetrationDetailBean {


    /**
     * data : [{"IS_TESTTIME":"2016-05-08 17:53","SHeader2":"中面层","SHeader3":"SBS改性沥青","SHeader4":"清洁，无杂质","avgvalue1":"49.27","banhezhanminchen":"云湛高速3标1号沥青站","biaoZhun1":"40.00","biaoZhun2":"55.00","f_CJBH":"","f_GUID":"003D13C084D94BA2834EE3F2123D5B32","f_SBBH":"yzgslq03_01","header3":"湖南龙永高速公路","header5":"20160002","isQualified":"合格","zhenRuDuGuoChengZhi":"50.40,48.50,48.90"}]
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
         * IS_TESTTIME : 2016-05-08 17:53
         * SHeader2 : 中面层
         * SHeader3 : SBS改性沥青
         * SHeader4 : 清洁，无杂质
         * avgvalue1 : 49.27
         * banhezhanminchen : 云湛高速3标1号沥青站
         * biaoZhun1 : 40.00
         * biaoZhun2 : 55.00
         * f_CJBH :
         * f_GUID : 003D13C084D94BA2834EE3F2123D5B32
         * f_SBBH : yzgslq03_01
         * header3 : 湖南龙永高速公路
         * header5 : 20160002
         * isQualified : 合格
         * zhenRuDuGuoChengZhi : 50.40,48.50,48.90
         */

        private String IS_TESTTIME;
        private String SHeader2;
        private String SHeader3;
        private String SHeader4;
        private String avgvalue1;
        private String banhezhanminchen;
        private String biaoZhun1;
        private String biaoZhun2;
        private String f_CJBH;
        private String f_GUID;
        private String f_SBBH;
        private String header3;
        private String header5;
        private String isQualified;
        private String zhenRuDuGuoChengZhi;

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

        public void setBanhezhanminchen(String banhezhanminchen) {
            this.banhezhanminchen = banhezhanminchen;
        }

        public void setBiaoZhun1(String biaoZhun1) {
            this.biaoZhun1 = biaoZhun1;
        }

        public void setBiaoZhun2(String biaoZhun2) {
            this.biaoZhun2 = biaoZhun2;
        }

        public void setF_CJBH(String f_CJBH) {
            this.f_CJBH = f_CJBH;
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

        public void setZhenRuDuGuoChengZhi(String zhenRuDuGuoChengZhi) {
            this.zhenRuDuGuoChengZhi = zhenRuDuGuoChengZhi;
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

        public String getBanhezhanminchen() {
            return banhezhanminchen;
        }

        public String getBiaoZhun1() {
            return biaoZhun1;
        }

        public String getBiaoZhun2() {
            return biaoZhun2;
        }

        public String getF_CJBH() {
            return f_CJBH;
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

        public String getZhenRuDuGuoChengZhi() {
            return zhenRuDuGuoChengZhi;
        }
    }
}
