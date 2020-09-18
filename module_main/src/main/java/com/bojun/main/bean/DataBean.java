package com.bojun.main.bean;


import java.util.List;

public class DataBean {

    /**
     * transType : DoctorAdvicess
     * code : success
     * msg : 成功
     * showMsg : null
     * result : {"list":[{"TZYS":"廖佰华","YZZH":"100","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"120","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"120","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"130","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"130","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"130","ZYH":"717449"}],"patientData":{"feeNo":"717449","bedNo":"WZ17"}}
     */

    private String transType;
    private String code;
    private String msg;
    private String showMsg;
    private ResultBean result;

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * list : [{"TZYS":"廖佰华","YZZH":"100","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"120","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"120","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"130","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"130","ZYH":"717449"},{"TZYS":"廖佰华","YZZH":"130","ZYH":"717449"}]
         * patientData : {"feeNo":"717449","bedNo":"WZ17"}
         */

        private PatientDataBean patientData;
        private List<ListBean> list;

        public PatientDataBean getPatientData() {
            return patientData;
        }

        public void setPatientData(PatientDataBean patientData) {
            this.patientData = patientData;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PatientDataBean {
            /**
             * feeNo : 717449
             * bedNo : WZ17
             */

            private String feeNo;
            private String bedNo;

            public String getFeeNo() {
                return feeNo;
            }

            public void setFeeNo(String feeNo) {
                this.feeNo = feeNo;
            }

            public String getBedNo() {
                return bedNo;
            }

            public void setBedNo(String bedNo) {
                this.bedNo = bedNo;
            }

            @Override
            public String toString() {
                return "PatientDataBean{" +
                        "feeNo='" + feeNo + '\'' +
                        ", bedNo='" + bedNo + '\'' +
                        '}';
            }
        }

        public static class ListBean {
            /**
             * TZYS : 廖佰华
             * YZZH : 100
             * ZYH : 717449
             */

            private String TZYS;
            private String YZZH;
            private String ZYH;

            public String getTZYS() {
                return TZYS;
            }

            public void setTZYS(String TZYS) {
                this.TZYS = TZYS;
            }

            public String getYZZH() {
                return YZZH;
            }

            public void setYZZH(String YZZH) {
                this.YZZH = YZZH;
            }

            public String getZYH() {
                return ZYH;
            }

            public void setZYH(String ZYH) {
                this.ZYH = ZYH;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "TZYS='" + TZYS + '\'' +
                        ", YZZH='" + YZZH + '\'' +
                        ", ZYH='" + ZYH + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "patientData=" + patientData +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "transType='" + transType + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", showMsg='" + showMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
