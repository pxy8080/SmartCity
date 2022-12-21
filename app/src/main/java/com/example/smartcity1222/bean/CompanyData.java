package com.example.smartcity1222.bean;

public class CompanyData {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5,"companyName":"牙大夫","introductory":"守望口腔汇聚了国内外优秀的医疗人才，采用\u201c四手\u201d操作，执行严格的消毒规范，为患者提供安心、安全的治疗环境。诊所内设有典雅舒适、风格各异的独立诊疗室，为患者提供一个轻松温馨的诊疗氛围"}
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
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 5
         * companyName : 牙大夫
         * introductory : 守望口腔汇聚了国内外优秀的医疗人才，采用“四手”操作，执行严格的消毒规范，为患者提供安心、安全的治疗环境。诊所内设有典雅舒适、风格各异的独立诊疗室，为患者提供一个轻松温馨的诊疗氛围
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private Integer id;
        private String companyName;
        private String introductory;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsDTO getParams() {
            return params;
        }

        public void setParams(ParamsDTO params) {
            this.params = params;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getIntroductory() {
            return introductory;
        }

        public void setIntroductory(String introductory) {
            this.introductory = introductory;
        }

        public static class ParamsDTO {
        }
    }
}
