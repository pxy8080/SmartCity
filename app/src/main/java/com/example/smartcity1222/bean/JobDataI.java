package com.example.smartcity1222.bean;

public class JobDataI {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"companyId":5,"professionId":5,"contacts":"王女士","name":"医生助理五险一金双休","obligation":"辅助医生参与患者治疗和管理","address":"大连市沙河口区中山路","need":"工作经验1-2年","salary":"5000","companyName":null,"professionName":null}
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
         * id : 2
         * companyId : 5
         * professionId : 5
         * contacts : 王女士
         * name : 医生助理五险一金双休
         * obligation : 辅助医生参与患者治疗和管理
         * address : 大连市沙河口区中山路
         * need : 工作经验1-2年
         * salary : 5000
         * companyName : null
         * professionName : null
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private Integer id;
        private Integer companyId;
        private Integer professionId;
        private String contacts;
        private String name;
        private String obligation;
        private String address;
        private String need;
        private String salary;
        private Object companyName;
        private Object professionName;

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

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public Integer getProfessionId() {
            return professionId;
        }

        public void setProfessionId(Integer professionId) {
            this.professionId = professionId;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObligation() {
            return obligation;
        }

        public void setObligation(String obligation) {
            this.obligation = obligation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNeed() {
            return need;
        }

        public void setNeed(String need) {
            this.need = need;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public Object getProfessionName() {
            return professionName;
        }

        public void setProfessionName(Object professionName) {
            this.professionName = professionName;
        }

        public static class ParamsDTO {
        }
    }
}
