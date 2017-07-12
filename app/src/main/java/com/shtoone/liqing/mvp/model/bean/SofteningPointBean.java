package com.shtoone.liqing.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class SofteningPointBean {


    /**
     * data : [{"SHeader2":"上面层","avgvalue1":"81.80","biaoZhun1":"75.00","f_GUID":"4321135A2DA04229A94779F03ACEEEE2","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速公路","header5":"20160720","isQualified":"合格","is_testtime":"2016-07-20 10:22","ruanhuadian1":"81.70","ruanhuadian2":"81.90"},{"SHeader2":"沥青混凝土面层","avgvalue1":"83.25","biaoZhun1":"75.00","f_GUID":"E15C8B3737D341E4BBF2F65D89F718D9","f_SBBH":"yzgslq03_01","header3":"龙永高速三十四合同段","header5":"2016072001","isQualified":"合格","is_testtime":"2016-07-20 09:44","ruanhuadian1":"83.30","ruanhuadian2":"83.20"},{"SHeader2":"沥青混凝土面层","avgvalue1":"82.55","biaoZhun1":"75.00","f_GUID":"4CF8C2A01B524C4F94FEE6A6BC04740B","f_SBBH":"yzgslq03_01","header3":"龙永高速三十四合同段","header5":"2016071701","isQualified":"合格","is_testtime":"2016-07-17 09:57","ruanhuadian1":"82.70","ruanhuadian2":"82.40"},{"SHeader2":"沥青混凝土面层","avgvalue1":"79.55","biaoZhun1":"75.00","f_GUID":"E2C745C121DE41F09C2BB578335D27C3","f_SBBH":"yzgslq03_01","header3":"龙永高速三十四合同段","header5":"2016071401","isQualified":"合格","is_testtime":"2016-07-14 18:17","ruanhuadian1":"79.30","ruanhuadian2":"79.80"},{"SHeader2":"上面层","avgvalue1":"80.85","biaoZhun1":"75.00","f_GUID":"AC7AB13DAB5B44F68CC787947F01A594","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速公路","header5":"20160714","isQualified":"合格","is_testtime":"2016-07-14 17:56","ruanhuadian1":"80.70","ruanhuadian2":"81.00"},{"SHeader2":"沥青混凝土面层","avgvalue1":"81.85","biaoZhun1":"75.00","f_GUID":"9EE2D40E539F4FDF8E6D2AD559CBBE48","f_SBBH":"yzgslq03_01","header3":"龙永高速三十四合同段","header5":"2016071101","isQualified":"合格","is_testtime":"2016-07-11 16:31","ruanhuadian1":"81.60","ruanhuadian2":"82.10"},{"SHeader2":"沥青混凝土面层","avgvalue1":"84.45","biaoZhun1":"75.00","f_GUID":"CA91412E7F3D48D19F1E10AAE1D5D921","f_SBBH":"yzgslq03_01","header3":"龙永高速三十四合同段","header5":"2016071002","isQualified":"合格","is_testtime":"2016-07-10 11:31","ruanhuadian1":"84.30","ruanhuadian2":"84.60"},{"SHeader2":"上面层","avgvalue1":"80.15","biaoZhun1":"75.00","f_GUID":"7CF1540521824B4DA3323777FAD4E625","f_SBBH":"yzgslq03_01","header3":"湖南省龙永高速公路","header5":"20160710","isQualified":"合格","is_testtime":"2016-07-10 10:36","ruanhuadian1":"79.90","ruanhuadian2":"80.40"},{"SHeader2":"沥青混凝土面层","avgvalue1":"85.85","biaoZhun1":"75.00","f_GUID":"8116A585E4BE4355ABEE0D3D88C7A87E","f_SBBH":"yzgslq03_01","header3":"龙永高速三十四合同段","header5":"20160701001","isQualified":"合格","is_testtime":"2016-07-10 08:46","ruanhuadian1":"84.60","ruanhuadian2":"87.10"},{"SHeader2":"上面层","avgvalue1":"81.90","biaoZhun1":"75.00","f_GUID":"2059FA5FF18148F0B78818C90CA31925","f_SBBH":"yzgslq03_01","header3":"湖南龙永高速公路","header5":"20160013","isQualified":"合格","is_testtime":"2016-07-08 17:08","ruanhuadian1":"81.70","ruanhuadian2":"82.10"},{"SHeader2":"沥青路面上面层","avgvalue1":"82.40","biaoZhun1":"75.00","f_GUID":"CF640B59881A4D6C9F5EFDBCEB09C446","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ093","isQualified":"合格","is_testtime":"2016-07-08 14:07","ruanhuadian1":"82.40","ruanhuadian2":"82.40"},{"SHeader2":"沥青路面上面层","avgvalue1":"79.30","biaoZhun1":"75.00","f_GUID":"AD25E532E7164E8C87B8A1DC61D2FFDE","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ092","isQualified":"合格","is_testtime":"2016-07-08 11:41","ruanhuadian1":"79.20","ruanhuadian2":"79.40"},{"SHeader2":"沥青路面上面层","avgvalue1":"80.90","biaoZhun1":"75.00","f_GUID":"38BCE99BDB9040CD99CECF5E53CC3CCA","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ091","isQualified":"合格","is_testtime":"2016-07-08 09:37","ruanhuadian1":"80.70","ruanhuadian2":"81.10"},{"SHeader2":"沥青混凝土面层","avgvalue1":"83.75","biaoZhun1":"75.00","f_GUID":"4D63BA1328AD4F66A78A7F47A2C6BB9C","f_SBBH":"yzgslq03_01","header3":"龙永高速三十四合同段","header5":"2016070801","isQualified":"合格","is_testtime":"2016-07-08 09:33","ruanhuadian1":"84.00","ruanhuadian2":"83.50"},{"SHeader2":"沥青路面上面层","avgvalue1":"82.05","biaoZhun1":"75.00","f_GUID":"0F80B1BB27614348ADBC0CC4B3FD2B0B","f_SBBH":"yzgslq03_01","header3":"龙永高速35合同段","header5":"LY-GXLQ090","isQualified":"合格","is_testtime":"2016-07-06 17:42","ruanhuadian1":"81.40","ruanhuadian2":"82.70"}]
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
         * avgvalue1 : 81.80
         * biaoZhun1 : 75.00
         * f_GUID : 4321135A2DA04229A94779F03ACEEEE2
         * f_SBBH : yzgslq03_01
         * header3 : 湖南省龙永高速公路
         * header5 : 20160720
         * isQualified : 合格
         * is_testtime : 2016-07-20 10:22
         * ruanhuadian1 : 81.70
         * ruanhuadian2 : 81.90
         */

        private String SHeader2;
        private String avgvalue1;
        private String biaoZhun1;
        private String f_GUID;
        private String f_SBBH;
        private String header3;
        private String header5;
        private String isQualified;
        private String is_testtime;
        private String ruanhuadian1;
        private String ruanhuadian2;

        public void setSHeader2(String SHeader2) {
            this.SHeader2 = SHeader2;
        }

        public void setAvgvalue1(String avgvalue1) {
            this.avgvalue1 = avgvalue1;
        }

        public void setBiaoZhun1(String biaoZhun1) {
            this.biaoZhun1 = biaoZhun1;
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

        public void setRuanhuadian1(String ruanhuadian1) {
            this.ruanhuadian1 = ruanhuadian1;
        }

        public void setRuanhuadian2(String ruanhuadian2) {
            this.ruanhuadian2 = ruanhuadian2;
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

        public String getRuanhuadian1() {
            return ruanhuadian1;
        }

        public String getRuanhuadian2() {
            return ruanhuadian2;
        }
    }
}
