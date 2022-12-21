package com.example.smartcity1222.bean;

public class BackData {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"plateNo":"辽B12345","tel":"13800000000"}
     */

    private String msg;
    private Integer code;
    private DataDTO data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * plateNo : 辽B12345
         * tel : 13800000000
         */

        private String plateNo;
        private String tel;

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
