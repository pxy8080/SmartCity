package com.example.smartcity1222.bean;

import java.util.List;

public class AppData {
    /**
     * total : 12
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2020-10-23 16:17:56","updateBy":null,"updateTime":"2021-05-10 09:20:12","remark":null,"params":{},"id":17,"serviceName":"停哪儿","serviceDesc":"查询停车场","serviceType":"车主服务","imgUrl":"/prod-api/profile/upload/image/2021/05/10/814fc6c4-de48-48a1-95f8-de3e749e348d.png","pid":1,"link":"park/index","sort":0,"isRecommend":"N"},{"searchValue":null,"createBy":null,"createTime":"2020-10-12 18:17:23","updateBy":null,"updateTime":"2021-05-10 09:20:22","remark":null,"params":{},"id":2,"serviceName":"城市地铁","serviceDesc":"城市地铁路线","serviceType":"生活服务","imgUrl":"/prod-api/profile/upload/image/2021/05/10/3bfb33ee-459f-4878-b89b-4b125aa84013.png","pid":1,"link":"metro_query/index","sort":1,"isRecommend":"N"},{"searchValue":null,"createBy":null,"createTime":"2020-10-12 18:17:33","updateBy":null,"updateTime":"2021-05-10 09:20:33","remark":null,"params":{},"id":3,"serviceName":"智慧巴士","serviceDesc":"智慧巴士站点","serviceType":"便民服务","imgUrl":"/prod-api/profile/upload/image/2021/05/10/aa69f9d0-9718-42f9-9f79-c07b82a48c41.png","pid":1,"link":"bus_query/custom_shuttle","sort":2,"isRecommend":"N"},{"searchValue":null,"createBy":null,"createTime":"2020-10-12 18:17:58","updateBy":null,"updateTime":"2021-05-10 09:20:43","remark":null,"params":{},"id":5,"serviceName":"门诊预约","serviceDesc":"快捷方便不要出门也能门诊预约","serviceType":"便民服务","imgUrl":"/prod-api/profile/upload/image/2021/05/10/31a6533c-bf60-4890-9a25-b18db764776a.png","pid":1,"link":"outpatient/hospitalList","sort":3,"isRecommend":"N"},{"searchValue":null,"createBy":null,"createTime":"2020-10-12 18:18:39","updateBy":null,"updateTime":"2021-05-10 09:20:53","remark":null,"params":{},"id":9,"serviceName":"智慧交管","serviceDesc":"查询车辆违章","serviceType":"车主服务","imgUrl":"/prod-api/profile/upload/image/2021/05/10/426fdd6c-c2dd-4def-97eb-76c68c0103be.png","pid":1,"link":"traffic/index","sort":5,"isRecommend":"N"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2020-10-23 16:17:56
         * updateBy : null
         * updateTime : 2021-05-10 09:20:12
         * remark : null
         * params : {}
         * id : 17
         * serviceName : 停哪儿
         * serviceDesc : 查询停车场
         * serviceType : 车主服务
         * imgUrl : /prod-api/profile/upload/image/2021/05/10/814fc6c4-de48-48a1-95f8-de3e749e348d.png
         * pid : 1
         * link : park/index
         * sort : 0
         * isRecommend : N
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String serviceName;
        private String serviceDesc;
        private String serviceType;
        private String imgUrl;
        private int pid;
        private String link;
        private int sort;
        private String isRecommend;
        int img;

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public RowsBean(String serviceName, int img) {
            this.serviceName = serviceName;
            this.img = img;
        }

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServiceDesc() {
            return serviceDesc;
        }

        public void setServiceDesc(String serviceDesc) {
            this.serviceDesc = serviceDesc;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }

        public static class ParamsBean {
        }
    }
}
