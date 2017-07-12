package com.shtoone.liqing.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class MarshallStabilityBean implements Serializable{


    /**
     * data : [{"SHeader2":"上面层","avgvalue1":"41.20","avgvalue2":"12.68","biaoZhun1":"20.00","biaoZhun2":"50.00","biaoZhun3":"8.00","f_GUID":"DBC44E8866064AC2976B46D306AEF2FC","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160724","isQualified":"合格","is_testtime":"2016-07-24 07:59"},{"SHeader2":"上面层","avgvalue1":"41.20","avgvalue2":"12.68","biaoZhun1":"20.00","biaoZhun2":"50.00","biaoZhun3":"8.00","f_GUID":"DBC44E8866064AC2976B46D306AEF2FC","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160724","isQualified":"合格","is_testtime":"2016-07-24 07:59"},{"SHeader2":"中面层","avgvalue1":"33.32","avgvalue2":"11.58","biaoZhun1":"20.00","biaoZhun2":"40.00","biaoZhun3":"8.00","f_GUID":"1EC55A2FE1CC414A9EBBA655438FFB90","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160723","isQualified":"合格","is_testtime":"2016-07-23 08:52"},{"SHeader2":"中面层","avgvalue1":"33.32","avgvalue2":"11.58","biaoZhun1":"20.00","biaoZhun2":"40.00","biaoZhun3":"8.00","f_GUID":"1EC55A2FE1CC414A9EBBA655438FFB90","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160723","isQualified":"合格","is_testtime":"2016-07-23 08:52"},{"SHeader2":"上面层","avgvalue1":"42.83","avgvalue2":"12.75","biaoZhun1":"20.00","biaoZhun2":"50.00","biaoZhun3":"8.00","f_GUID":"BDB3EE96F9304721BE87B2E774398B92","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160720","isQualified":"合格","is_testtime":"2016-07-20 09:07"},{"SHeader2":"上面层","avgvalue1":"42.83","avgvalue2":"12.75","biaoZhun1":"20.00","biaoZhun2":"50.00","biaoZhun3":"8.00","f_GUID":"BDB3EE96F9304721BE87B2E774398B92","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160720","isQualified":"合格","is_testtime":"2016-07-20 09:07"},{"SHeader2":"上面层","avgvalue1":"39.12","avgvalue2":"12.93","biaoZhun1":"20.00","biaoZhun2":"50.00","biaoZhun3":"8.00","f_GUID":"54455D79C73A4766B34B825C156E7C20","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160719","isQualified":"合格","is_testtime":"2016-07-19 08:27"},{"SHeader2":"上面层","avgvalue1":"39.12","avgvalue2":"12.93","biaoZhun1":"20.00","biaoZhun2":"50.00","biaoZhun3":"8.00","f_GUID":"54455D79C73A4766B34B825C156E7C20","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160719","isQualified":"合格","is_testtime":"2016-07-19 08:27"},{"SHeader2":"中面层","avgvalue1":"32.89","avgvalue2":"11.90","biaoZhun1":"20.00","biaoZhun2":"40.00","biaoZhun3":"8.00","f_GUID":"49D2FC74B7824EC6936C4C9A62BA4486","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160718","isQualified":"合格","is_testtime":"2016-07-18 08:17"},{"SHeader2":"中面层","avgvalue1":"32.89","avgvalue2":"11.90","biaoZhun1":"20.00","biaoZhun2":"40.00","biaoZhun3":"8.00","f_GUID":"49D2FC74B7824EC6936C4C9A62BA4486","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160718","isQualified":"合格","is_testtime":"2016-07-18 08:17"},{"SHeader2":"下面层","avgvalue1":"31.58","avgvalue2":"11.27","biaoZhun1":"20.00","biaoZhun2":"40.00","biaoZhun3":"8.00","f_GUID":"389E0497FE30462D9739AC6164255D55","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160717","isQualified":"合格","is_testtime":"2016-07-17 09:37"},{"SHeader2":"下面层","avgvalue1":"31.58","avgvalue2":"11.27","biaoZhun1":"20.00","biaoZhun2":"40.00","biaoZhun3":"8.00","f_GUID":"389E0497FE30462D9739AC6164255D55","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160717","isQualified":"合格","is_testtime":"2016-07-17 09:37"},{"SHeader2":"上面层","avgvalue1":"40.55","avgvalue2":"13.85","biaoZhun1":"20.00","biaoZhun2":"50.00","biaoZhun3":"8.00","f_GUID":"8CF949A984424232890AC680E6AE5E7D","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160716","isQualified":"合格","is_testtime":"2016-07-16 08:17"},{"SHeader2":"上面层","avgvalue1":"40.55","avgvalue2":"13.85","biaoZhun1":"20.00","biaoZhun2":"50.00","biaoZhun3":"8.00","f_GUID":"8CF949A984424232890AC680E6AE5E7D","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160716","isQualified":"合格","is_testtime":"2016-07-16 08:17"},{"SHeader2":"中面层","avgvalue1":"31.87","avgvalue2":"13.28","biaoZhun1":"20.00","biaoZhun2":"40.00","biaoZhun3":"8.00","f_GUID":"E86D1ED148034906A009ACE89A13E84A","f_SBBH":"lylqmxejl01_01","header3":"湖南省龙永高速公路","header5":"20160715","isQualified":"合格","is_testtime":"2016-07-15 08:40"}]
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
         * SHeader2 : 上面层
         * avgvalue1 : 41.20
         * avgvalue2 : 12.68
         * biaoZhun1 : 20.00
         * biaoZhun2 : 50.00
         * biaoZhun3 : 8.00
         * f_GUID : DBC44E8866064AC2976B46D306AEF2FC
         * f_SBBH : lylqmxejl01_01
         * header3 : 湖南省龙永高速公路
         * header5 : 20160724
         * isQualified : 合格
         * is_testtime : 2016-07-24 07:59
         */

        private String SHeader2;
        private String avgvalue1;
        private String avgvalue2;
        private String biaoZhun1;
        private String biaoZhun2;
        private String biaoZhun3;
        private String f_GUID;
        private String f_SBBH;
        private String header3;
        private String header5;
        private String isQualified;
        private String is_testtime;

        public String getSHeader2() {
            return SHeader2;
        }

        public void setSHeader2(String SHeader2) {
            this.SHeader2 = SHeader2;
        }

        public String getAvgvalue1() {
            return avgvalue1;
        }

        public void setAvgvalue1(String avgvalue1) {
            this.avgvalue1 = avgvalue1;
        }

        public String getAvgvalue2() {
            return avgvalue2;
        }

        public void setAvgvalue2(String avgvalue2) {
            this.avgvalue2 = avgvalue2;
        }

        public String getBiaoZhun1() {
            return biaoZhun1;
        }

        public void setBiaoZhun1(String biaoZhun1) {
            this.biaoZhun1 = biaoZhun1;
        }

        public String getBiaoZhun2() {
            return biaoZhun2;
        }

        public void setBiaoZhun2(String biaoZhun2) {
            this.biaoZhun2 = biaoZhun2;
        }

        public String getBiaoZhun3() {
            return biaoZhun3;
        }

        public void setBiaoZhun3(String biaoZhun3) {
            this.biaoZhun3 = biaoZhun3;
        }

        public String getF_GUID() {
            return f_GUID;
        }

        public void setF_GUID(String f_GUID) {
            this.f_GUID = f_GUID;
        }

        public String getF_SBBH() {
            return f_SBBH;
        }

        public void setF_SBBH(String f_SBBH) {
            this.f_SBBH = f_SBBH;
        }

        public String getHeader3() {
            return header3;
        }

        public void setHeader3(String header3) {
            this.header3 = header3;
        }

        public String getHeader5() {
            return header5;
        }

        public void setHeader5(String header5) {
            this.header5 = header5;
        }

        public String getIsQualified() {
            return isQualified;
        }

        public void setIsQualified(String isQualified) {
            this.isQualified = isQualified;
        }

        public String getIs_testtime() {
            return is_testtime;
        }

        public void setIs_testtime(String is_testtime) {
            this.is_testtime = is_testtime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "SHeader2='" + SHeader2 + '\'' +
                    ", avgvalue1='" + avgvalue1 + '\'' +
                    ", avgvalue2='" + avgvalue2 + '\'' +
                    ", biaoZhun1='" + biaoZhun1 + '\'' +
                    ", biaoZhun2='" + biaoZhun2 + '\'' +
                    ", biaoZhun3='" + biaoZhun3 + '\'' +
                    ", f_GUID='" + f_GUID + '\'' +
                    ", f_SBBH='" + f_SBBH + '\'' +
                    ", header3='" + header3 + '\'' +
                    ", header5='" + header5 + '\'' +
                    ", isQualified='" + isQualified + '\'' +
                    ", is_testtime='" + is_testtime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MarshallStabilityBean{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
