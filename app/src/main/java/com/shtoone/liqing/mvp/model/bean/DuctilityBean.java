package com.shtoone.liqing.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class DuctilityBean {


    /**
     * data : [{"IS_TESTTIME":"2015-10-31 16:54","SHeader2":"龙永高速","SHeader3":"龙永高速","SHeader4":"7.00","avgvalue1":"11.33","banhezhanminchen":"云湛高速3标1号沥青站","biaozhunzhi1":"100.00","biaozhunzhi2":"10000.00","f_GUID":"4EF41EA3222041659A8307B4847BB787","f_SBBH":"yzgslq03_01","header3":"湖南龙永高速公路","header5":"YP-2015-YD0001","isQualified":"不合格","yandu11":"","yandu12":"12.00","yandu13":"15.00"},{"IS_TESTTIME":"2015-10-30 16:19","SHeader2":"下面层","SHeader3":"A-50","SHeader4":"1022.00","avgvalue1":"1024.67","banhezhanminchen":"云湛高速3标1号沥青站","biaozhunzhi1":"800.00","biaozhunzhi2":"1500.00","f_GUID":"8151C387B3C6421ABA48078E60676363","f_SBBH":"yzgslq03_01","header3":"湖南龙永高速","header5":"ptlq0001","isQualified":"合格","yandu11":"","yandu12":"1025.00","yandu13":"1027.00"},{"IS_TESTTIME":"2015-10-30 14:50","SHeader2":"下面层","SHeader3":"50号道路石油沥青","SHeader4":"849.00","avgvalue1":"850.67","banhezhanminchen":"云湛高速3标1号沥青站","biaozhunzhi1":"800.00","biaozhunzhi2":"1500.00","f_GUID":"6322D935EA514665BC2A8C3035DFA5E8","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速","header5":"201510300002","isQualified":"合格","yandu11":"","yandu12":"851.00","yandu13":"852.00"},{"IS_TESTTIME":"2015-10-29 17:15","SHeader2":"沥青路面下面层","SHeader3":"50#A级道路石油基质沥青","SHeader4":"1014.00","avgvalue1":"1015.33","banhezhanminchen":"云湛高速3标1号沥青站","biaozhunzhi1":"800.00","biaozhunzhi2":"10000.00","f_GUID":"0DCB70C21AAA4E45B49BD352C18CBF06","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ-010","isQualified":"合格","yandu11":"","yandu12":"1015.00","yandu13":"1017.00"},{"IS_TESTTIME":"2015-10-29 12:29","SHeader2":"下面层","SHeader3":"50号道路石油沥青  ","SHeader4":"974.00","avgvalue1":"975.67","banhezhanminchen":"云湛高速3标1号沥青站","biaozhunzhi1":"0.00","biaozhunzhi2":"80.00","f_GUID":"341D71587BFE46438EFC16F99C10927F","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速","header5":"201510290001","isQualified":"不合格","yandu11":"","yandu12":"976.00","yandu13":"977.00"},{"IS_TESTTIME":"2015-10-28 19:26","SHeader2":"沥青路面中面层（配合比用）","SHeader3":"SBS改性沥青","SHeader4":"360.00","avgvalue1":"363.67","banhezhanminchen":"云湛高速3标1号沥青站","biaozhunzhi1":"200.00","biaozhunzhi2":"10000.00","f_GUID":"4FB1BBBC2D154BB6A83C26421861726C","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ-009","isQualified":"合格","yandu11":"","yandu12":"365.00","yandu13":"366.00"},{"IS_TESTTIME":"2015-09-30 13:49","SHeader2":"龙永","SHeader3":"延度","SHeader4":"5.00","avgvalue1":"6.67","banhezhanminchen":"云湛高速3标1号沥青站","biaozhunzhi1":"5.00","biaozhunzhi2":"10.00","f_GUID":"B1E0A37E144B4221A101F1CB2B1F9245","f_SBBH":"yzgslq03_01","header3":"湖南龙永高速","header5":"000000000002","isQualified":"合格","yandu11":"","yandu12":"7.00","yandu13":"8.00"},{"IS_TESTTIME":"2015-09-05 15:36","SHeader2":"路面工程","SHeader3":"延度试验","SHeader4":"0.00","avgvalue1":"0.00","banhezhanminchen":"云湛高速3标1号沥青站","biaozhunzhi1":"10.00","biaozhunzhi2":"15.00","f_GUID":"506021E3B24B4D19A0E74495F133E93F","f_SBBH":"yzgslq03_01","header3":"湖南龙永高速公路","header5":"YP-2015-YD0001","isQualified":"不合格","yandu11":"","yandu12":"0.00","yandu13":"0.00"}]
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
         * IS_TESTTIME : 2015-10-31 16:54
         * SHeader2 : 龙永高速
         * SHeader3 : 龙永高速
         * SHeader4 : 7.00
         * avgvalue1 : 11.33
         * banhezhanminchen : 云湛高速3标1号沥青站
         * biaozhunzhi1 : 100.00
         * biaozhunzhi2 : 10000.00
         * f_GUID : 4EF41EA3222041659A8307B4847BB787
         * f_SBBH : yzgslq03_01
         * header3 : 湖南龙永高速公路
         * header5 : YP-2015-YD0001
         * isQualified : 不合格
         * yandu11 :
         * yandu12 : 12.00
         * yandu13 : 15.00
         */

        private String IS_TESTTIME;
        private String SHeader2;
        private String SHeader3;
        private String SHeader4;
        private String avgvalue1;
        private String banhezhanminchen;
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

        public void setBanhezhanminchen(String banhezhanminchen) {
            this.banhezhanminchen = banhezhanminchen;
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

        public String getBanhezhanminchen() {
            return banhezhanminchen;
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
