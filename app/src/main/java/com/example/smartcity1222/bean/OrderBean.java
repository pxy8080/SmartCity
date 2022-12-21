package com.example.smartcity1222.bean;

import java.util.List;

public class OrderBean {
    /**
     * total : 5
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2021-12-10 13:28:00","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":509,"orderNo":"2021121013275967435","theaterId":13,"roomId":4,"seatId":null,"timesId":2,"movieId":3,"price":36,"status":"N","userId":1112564,"paymentType":null,"theatreName":"北联影城","roomName":"1号厅","seatRow":null,"seatCol":null,"movieName":"悬崖之上","userName":null,"startTime":"10:30","endTime":"11:45","playDate":"2021-05-09","payTime":null,"orderItems":[]},{"searchValue":null,"createBy":null,"createTime":"2021-12-10 13:48:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":510,"orderNo":"2021121013482329939","theaterId":6,"roomId":44,"seatId":null,"timesId":32,"movieId":5,"price":25,"status":"N","userId":1112564,"paymentType":null,"theatreName":"中影华臣影城","roomName":"3号厅","seatRow":null,"seatCol":null,"movieName":" 你的婚礼","userName":null,"startTime":"05:15","endTime":"07:45","playDate":"2021-05-13","payTime":null,"orderItems":[]},{"searchValue":null,"createBy":null,"createTime":"2021-12-10 13:52:51","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":511,"orderNo":"2021121013525033736","theaterId":13,"roomId":4,"seatId":null,"timesId":2,"movieId":3,"price":36,"status":"N","userId":1112564,"paymentType":null,"theatreName":"北联影城","roomName":"1号厅","seatRow":null,"seatCol":null,"movieName":"悬崖之上","userName":null,"startTime":"10:30","endTime":"11:45","playDate":"2021-05-09","payTime":null,"orderItems":[]},{"searchValue":null,"createBy":null,"createTime":"2021-12-10 14:14:57","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":515,"orderNo":"2021121014145743916","theaterId":13,"roomId":4,"seatId":null,"timesId":2,"movieId":3,"price":36,"status":"N","userId":1112564,"paymentType":null,"theatreName":"北联影城","roomName":"1号厅","seatRow":null,"seatCol":null,"movieName":"悬崖之上","userName":null,"startTime":"10:30","endTime":"11:45","playDate":"2021-05-09","payTime":null,"orderItems":[]},{"searchValue":null,"createBy":null,"createTime":"2021-12-10 14:16:16","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":516,"orderNo":"2021121014161567409","theaterId":13,"roomId":4,"seatId":null,"timesId":2,"movieId":3,"price":36,"status":"N","userId":1112564,"paymentType":null,"theatreName":"北联影城","roomName":"1号厅","seatRow":null,"seatCol":null,"movieName":"悬崖之上","userName":null,"startTime":"10:30","endTime":"11:45","playDate":"2021-05-09","payTime":null,"orderItems":[]}]
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
         * createTime : 2021-12-10 13:28:00
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 509
         * orderNo : 2021121013275967435
         * theaterId : 13
         * roomId : 4
         * seatId : null
         * timesId : 2
         * movieId : 3
         * price : 36
         * status : N
         * userId : 1112564
         * paymentType : null
         * theatreName : 北联影城
         * roomName : 1号厅
         * seatRow : null
         * seatCol : null
         * movieName : 悬崖之上
         * userName : null
         * startTime : 10:30
         * endTime : 11:45
         * playDate : 2021-05-09
         * payTime : null
         * orderItems : []
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String orderNo;
        private int theaterId;
        private int roomId;
        private Object seatId;
        private int timesId;
        private int movieId;
        private int price;
        private String status;
        private int userId;
        private Object paymentType;
        private String theatreName;
        private String roomName;
        private Object seatRow;
        private Object seatCol;
        private String movieName;
        private Object userName;
        private String startTime;
        private String endTime;
        private String playDate;
        private Object payTime;
        private List<?> orderItems;

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

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getTheaterId() {
            return theaterId;
        }

        public void setTheaterId(int theaterId) {
            this.theaterId = theaterId;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public Object getSeatId() {
            return seatId;
        }

        public void setSeatId(Object seatId) {
            this.seatId = seatId;
        }

        public int getTimesId() {
            return timesId;
        }

        public void setTimesId(int timesId) {
            this.timesId = timesId;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(Object paymentType) {
            this.paymentType = paymentType;
        }

        public String getTheatreName() {
            return theatreName;
        }

        public void setTheatreName(String theatreName) {
            this.theatreName = theatreName;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public Object getSeatRow() {
            return seatRow;
        }

        public void setSeatRow(Object seatRow) {
            this.seatRow = seatRow;
        }

        public Object getSeatCol() {
            return seatCol;
        }

        public void setSeatCol(Object seatCol) {
            this.seatCol = seatCol;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getPlayDate() {
            return playDate;
        }

        public void setPlayDate(String playDate) {
            this.playDate = playDate;
        }

        public Object getPayTime() {
            return payTime;
        }

        public void setPayTime(Object payTime) {
            this.payTime = payTime;
        }

        public List<?> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<?> orderItems) {
            this.orderItems = orderItems;
        }

        public static class ParamsBean {
        }
    }
}
