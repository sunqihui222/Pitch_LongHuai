package com.shtoone.liqing.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class AsphaltPenetrationBean {

    /**
     * data : [{"SHeader2":"上面层","avgvalue1":"54.17","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"4333F1D8FF8E44DAB6D6212FF700EDF3","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速公路","header5":"20160720","isQualified":"合格","is_testtime":"2016-07-20 11:45","zhenrudu":"54.20,53.90,54.40"},{"SHeader2":"沥青混凝土面层","avgvalue1":"51.57","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"FE9BA683D26346AD86E68D19E10D476A","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速第三十四合同段","header5":"2016072001","isQualified":"合格","is_testtime":"2016-07-20 10:35","zhenrudu":"51.50,51.90,51.30"},{"SHeader2":"沥青混凝土面层","avgvalue1":"51.80","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"3F1E96968F68466C9F0771D326728531","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速第三十四合同段","header5":"2016071701","isQualified":"合格","is_testtime":"2016-07-17 11:47","zhenrudu":"51.80,52.00,51.60"},{"SHeader2":"沥青混凝土面层","avgvalue1":"49.30","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"117923AAAB494B1E8F90B9A6FCDCAA63","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速第三十四合同段","header5":"2016071401","isQualified":"合格","is_testtime":"2016-07-14 20:40","zhenrudu":"49.30,49.00,49.60"},{"SHeader2":"上面层","avgvalue1":"53.37","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"C549D69C0C4840C18AEB5838957B6C0D","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速公路","header5":"20160715","isQualified":"合格","is_testtime":"2016-07-14 19:34","zhenrudu":"53.30,53.80,53.00"},{"SHeader2":"沥青混凝土面层","avgvalue1":"51.30","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"03A6B93741D846478E5245A0475C035E","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速第三十四合同段","header5":"2016071101","isQualified":"合格","is_testtime":"2016-07-11 18:23","zhenrudu":"50.40,51.40,52.10"},{"SHeader2":"上面层","avgvalue1":"54.37","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"56AF3A50B3DD4E8A806F272837A049B9","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速公路","header5":"20160710","isQualified":"合格","is_testtime":"2016-07-10 11:21","zhenrudu":"53.90,54.40,54.80"},{"SHeader2":"沥青混凝土面层","avgvalue1":"53.40","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"3CF4B79A2CC64933812EC6B96E9CE248","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速第三十四合同段","header5":"2016071001","isQualified":"合格","is_testtime":"2016-07-10 10:13","zhenrudu":"52.60,52.80,54.80"},{"SHeader2":"上面层","avgvalue1":"50.73","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"06AA0375BA3E41B7A4D22E708A942C27","f_SBBH":"yzgslq03_01","header3":"湖南龙永高速公路","header5":"20160013","isQualified":"合格","is_testtime":"2016-07-08 16:59","zhenrudu":"50.30,51.30,50.60"},{"SHeader2":"沥青路面上面层","avgvalue1":"49.80","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"F5E6F83080EB406892A28D36B2922911","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ-093","isQualified":"合格","is_testtime":"2016-07-08 14:34","zhenrudu":"50.30,49.40,49.70"},{"SHeader2":"沥青路面上面层","avgvalue1":"50.83","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"BB8339575A704BE98B192B10CBCE0A81","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ-092","isQualified":"合格","is_testtime":"2016-07-08 12:30","zhenrudu":"51.10,50.60,50.80"},{"SHeader2":"沥青混凝土面层","avgvalue1":"53.50","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"5FA437639853444C96D65E1B664E8C47","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速第三十四合同段","header5":"2016070801","isQualified":"合格","is_testtime":"2016-07-08 11:00","zhenrudu":"54.00,53.30,53.20"},{"SHeader2":"沥青路面上面层","avgvalue1":"46.30","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"8CE06C44E3114C9FA2591BD7CB2FF9CE","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ-091","isQualified":"合格","is_testtime":"2016-07-08 10:54","zhenrudu":"47.00,45.90,46.00"},{"SHeader2":"上面层","avgvalue1":"53.80","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"E697092610C740EE84A8C2ED2DCC4816","f_SBBH":"yzgslq03_01","header3":"湖南龙永高速公路","header5":"20160012","isQualified":"合格","is_testtime":"2016-07-07 17:30","zhenrudu":"53.90,53.70,53.80"},{"SHeader2":"沥青路面上面层","avgvalue1":"51.37","biaoZhun1":"55.00","biaoZhun2":"","f_GUID":"98218586E5A34847BD2159922E5CD11D","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ-090","isQualified":"合格","is_testtime":"2016-07-06 18:19","zhenrudu":"51.00,51.90,51.20"}]
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
         * avgvalue1 : 54.17
         * biaoZhun1 : 55.00
         * biaoZhun2 :
         * f_GUID : 4333F1D8FF8E44DAB6D6212FF700EDF3
         * f_SBBH : yzgslq03_01
         * header3 : 湖南省龙永高速公路
         * header5 : 20160720
         * isQualified : 合格
         * is_testtime : 2016-07-20 11:45
         * zhenrudu : 54.20,53.90,54.40
         */

        private String SHeader2;
        private String avgvalue1;
        private String biaoZhun1;
        private String biaoZhun2;
        private String f_GUID;
        private String f_SBBH;
        private String header3;
        private String header5;
        private String isQualified;
        private String is_testtime;
        private String zhenrudu;

        public void setSHeader2(String SHeader2) {
            this.SHeader2 = SHeader2;
        }

        public void setAvgvalue1(String avgvalue1) {
            this.avgvalue1 = avgvalue1;
        }

        public void setBiaoZhun1(String biaoZhun1) {
            this.biaoZhun1 = biaoZhun1;
        }

        public void setBiaoZhun2(String biaoZhun2) {
            this.biaoZhun2 = biaoZhun2;
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

        public void setIs_testtime(String is_testtime) {
            this.is_testtime = is_testtime;
        }

        public void setZhenrudu(String zhenrudu) {
            this.zhenrudu = zhenrudu;
        }

        public String getSHeader2() {
            return SHeader2;
        }

        public String getAvgvalue1() {
            return avgvalue1;
        }

        public String getBiaoZhun1() {
            return biaoZhun1;
        }

        public String getBiaoZhun2() {
            return biaoZhun2;
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

        public String getIs_testtime() {
            return is_testtime;
        }

        public String getZhenrudu() {
            return zhenrudu;
        }
    }
}
