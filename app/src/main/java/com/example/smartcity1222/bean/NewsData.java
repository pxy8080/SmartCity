package com.example.smartcity1222.bean;

import java.util.List;

public class NewsData {
    /**
     * total : 1
     * rows : [{"searchValue":null,"createBy":"1","createTime":"2021-05-08 15:01:37","updateBy":"1111165","updateTime":"2021-12-06 14:11:28","remark":null,"params":{},"id":64,"appType":"smart_city","cover":"/prod-api/profile/upload/image/2021/05/08/20dd5970-ce92-4832-b957-0996702eed43.jpg","title":"微软作证支持Epic遭苹果反击：要求公布Xbox损益表","subTitle":null,"content":"<p><br><\/p><p>北京时间5月8日上午消息，据报道，随着\u201cEpic诉苹果\u201d案的第一周庭审结束，苹果也开始对其中一位证人发起反击。<\/p><p>&nbsp;<\/p><p>　　这个证人就是微软Xbox业务开发副总裁劳里·赖特（Lori Wright）。她在周三关于iPhone是\u201c通用目的\u201d还是\u201c特殊目的\u201d设备的辩论中，给出了偏向Epic的证词。赖特的证词还引发了关于微软出售Xbox硬件是否赚钱的疑问。苹果指责赖特在文件制作过程中存在不规范行为，因而要求法官认定该证词信誉存疑。<\/p><p>&nbsp;<\/p><p>　　苹果公司在新提交的文件中辩称，赖特在证词中提到的某些文件不是事先制作的，整个证词的信誉存疑。苹果的律师主要瞄准了赖特的一个说法：即Xbox硬件以成本价销售，以补贴游戏销售。<\/p><p>&nbsp;<\/p><p>　　苹果在文件中写道：\u201c赖特小姐作证称微软的主机业务无利可图，但却没有提供损益表来以证明（或证伪）她的证词。\u201d<\/p><p>&nbsp;<\/p><p>　　苹果之前就采取过类似的措施。该公司4月曾经表示，由于开庭前几周的文件制作不规范，因此赖特的证词应从记录中剔除。现在，他们又认为赖特的证词超出预设范围，因此应当认定其整个证词不可信。<\/p><p>&nbsp;<\/p><p>　　问题的核心是微软对Xbox硬件的损益分析，但苹果方面却没有人见过这份分析。值得注意的是，苹果和微软数十年来一直处于激烈的竞争之中，尽管苹果没有专门与Xbox竞争的产品，但这两家公司公司却在激烈竞争和业务合作之间达成了微妙的平衡。正因如此，微软肯定不希望向苹果公司提供有关Xbox的敏感财务数据，而苹果公司认为损益表是向微软发起反击的一种方式\u2014\u2014毕竟是微软首先插手了\u201cEpic诉苹果\u201d案。<\/p><p>&nbsp;<\/p><p>　　自该案开庭以来，文件始终是一个重要问题。只要诉讼继续推进，这个问题就很可能继续存在。双方事先同意将相关文件上传到一个公共Box文件夹中，但对文件夹的实际使用却非常混乱。第一天就有100多份不同的文件被上传到通用展示文件夹，只有一小部分进行密封。几乎每天都会有文档都会被添加到Box中，然后又被撤回。<\/p><p>&nbsp;<\/p><p>　　其中一些是正常的。此次法律斗争的很大一部分是关于哪些文件可以在审判中使用，哪些文件的哪些部分应该涂黑以隐藏商业机密或企业不愿透露的其他信息。这些企业的律师在这些细节上纠缠了几个月\u2014\u2014这也正是律师的职责所在。<\/p><p>&nbsp;<\/p><p>　　业内人士认为，此案的过程异常混乱，可能是因为许多流程采用远程模式，加之涉及的企业数量过多所致。这个案件的取证过程暴露了很多信息，但并非所有信息都符合流程。微软现在陷入了两难境地：要么放弃可能对法庭有帮助的信息，要么将商业机密曝光给宿敌。微软希望Epic能赢得这场官司，如果有助于实现这一目标，他们愿意谈论Xbox的盈利能力。但在公开法庭上公开这些信息可能并不符合微软的意愿。<\/p>","status":"Y","publishDate":"2021-05-08","tags":null,"commentNum":10,"likeNum":552,"readNum":2356,"type":"22","top":"N","hot":"N"}]
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
         * createBy : 1
         * createTime : 2021-05-08 15:01:37
         * updateBy : 1111165
         * updateTime : 2021-12-06 14:11:28
         * remark : null
         * params : {}
         * id : 64
         * appType : smart_city
         * cover : /prod-api/profile/upload/image/2021/05/08/20dd5970-ce92-4832-b957-0996702eed43.jpg
         * title : 微软作证支持Epic遭苹果反击：要求公布Xbox损益表
         * subTitle : null
         * content : <p><br></p><p>北京时间5月8日上午消息，据报道，随着“Epic诉苹果”案的第一周庭审结束，苹果也开始对其中一位证人发起反击。</p><p>&nbsp;</p><p>　　这个证人就是微软Xbox业务开发副总裁劳里·赖特（Lori Wright）。她在周三关于iPhone是“通用目的”还是“特殊目的”设备的辩论中，给出了偏向Epic的证词。赖特的证词还引发了关于微软出售Xbox硬件是否赚钱的疑问。苹果指责赖特在文件制作过程中存在不规范行为，因而要求法官认定该证词信誉存疑。</p><p>&nbsp;</p><p>　　苹果公司在新提交的文件中辩称，赖特在证词中提到的某些文件不是事先制作的，整个证词的信誉存疑。苹果的律师主要瞄准了赖特的一个说法：即Xbox硬件以成本价销售，以补贴游戏销售。</p><p>&nbsp;</p><p>　　苹果在文件中写道：“赖特小姐作证称微软的主机业务无利可图，但却没有提供损益表来以证明（或证伪）她的证词。”</p><p>&nbsp;</p><p>　　苹果之前就采取过类似的措施。该公司4月曾经表示，由于开庭前几周的文件制作不规范，因此赖特的证词应从记录中剔除。现在，他们又认为赖特的证词超出预设范围，因此应当认定其整个证词不可信。</p><p>&nbsp;</p><p>　　问题的核心是微软对Xbox硬件的损益分析，但苹果方面却没有人见过这份分析。值得注意的是，苹果和微软数十年来一直处于激烈的竞争之中，尽管苹果没有专门与Xbox竞争的产品，但这两家公司公司却在激烈竞争和业务合作之间达成了微妙的平衡。正因如此，微软肯定不希望向苹果公司提供有关Xbox的敏感财务数据，而苹果公司认为损益表是向微软发起反击的一种方式——毕竟是微软首先插手了“Epic诉苹果”案。</p><p>&nbsp;</p><p>　　自该案开庭以来，文件始终是一个重要问题。只要诉讼继续推进，这个问题就很可能继续存在。双方事先同意将相关文件上传到一个公共Box文件夹中，但对文件夹的实际使用却非常混乱。第一天就有100多份不同的文件被上传到通用展示文件夹，只有一小部分进行密封。几乎每天都会有文档都会被添加到Box中，然后又被撤回。</p><p>&nbsp;</p><p>　　其中一些是正常的。此次法律斗争的很大一部分是关于哪些文件可以在审判中使用，哪些文件的哪些部分应该涂黑以隐藏商业机密或企业不愿透露的其他信息。这些企业的律师在这些细节上纠缠了几个月——这也正是律师的职责所在。</p><p>&nbsp;</p><p>　　业内人士认为，此案的过程异常混乱，可能是因为许多流程采用远程模式，加之涉及的企业数量过多所致。这个案件的取证过程暴露了很多信息，但并非所有信息都符合流程。微软现在陷入了两难境地：要么放弃可能对法庭有帮助的信息，要么将商业机密曝光给宿敌。微软希望Epic能赢得这场官司，如果有助于实现这一目标，他们愿意谈论Xbox的盈利能力。但在公开法庭上公开这些信息可能并不符合微软的意愿。</p>
         * status : Y
         * publishDate : 2021-05-08
         * tags : null
         * commentNum : 10
         * likeNum : 552
         * readNum : 2356
         * type : 22
         * top : N
         * hot : N
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String appType;
        private String cover;
        private String title;
        private Object subTitle;
        private String content;
        private String status;
        private String publishDate;
        private Object tags;
        private int commentNum;
        private int likeNum;
        private int readNum;
        private String type;
        private String top;
        private String hot;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
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

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(Object subTitle) {
            this.subTitle = subTitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getReadNum() {
            return readNum;
        }

        public void setReadNum(int readNum) {
            this.readNum = readNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public static class ParamsBean {
        }
    }
}
