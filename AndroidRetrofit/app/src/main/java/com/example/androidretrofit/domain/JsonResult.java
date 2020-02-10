package com.example.androidretrofit.domain;

import java.util.List;

public class JsonResult {

    /**
     * success : true
     * code : 10000
     * message : 获取成功
     * data : [{"id":"1223406200789880832","title":"Android加载大图片，解决OOM问题","viewCount":155,"commentCount":38,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/2.png"},{"id":"1223406200877961216","title":"Volley/Xutils对大图片处理算法源码分析","viewCount":78,"commentCount":28,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/9.png"},{"id":"1223406200877961217","title":"Android开发网络安全配置","viewCount":180,"commentCount":45,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/1.png"},{"id":"1223406200877961218","title":"Android开发网络编程，请求图片","viewCount":178,"commentCount":87,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/0.png"},{"id":"1223406200877961219","title":"Intent页面跳转工具类分享","viewCount":142,"commentCount":84,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/2.png"},{"id":"1223406200877961220","title":"阳光沙滩商城的API文档","viewCount":176,"commentCount":89,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/10.png"},{"id":"1223406200877961221","title":"Android课程视频打包下载","viewCount":316,"commentCount":16,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/0.png"},{"id":"1223406200877961222","title":"非常轻量级的gif录制软件","viewCount":122,"commentCount":23,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/9.png"},{"id":"1223406200877961223","title":"Fiddler抓包工具，墙裂推荐，功能很强大很全的一个工具","viewCount":157,"commentCount":75,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/2.png"},{"id":"1223406200877961224","title":"AndroidStudio奇淫技巧-代码管理","viewCount":193,"commentCount":79,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/1.png"},{"id":"1223406200877961225","title":"OC和Swift混编","viewCount":322,"commentCount":42,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/6.png"},{"id":"1223406200877961226","title":"最新的Android studio是不是没有Android Device Monitor","viewCount":244,"commentCount":75,"publishTime":"2020-02-01T00:42:22.734+0000","userName":"程序员拉大锯","cover":"/imgs/9.png"}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1223406200789880832
         * title : Android加载大图片，解决OOM问题
         * viewCount : 155
         * commentCount : 38
         * publishTime : 2020-02-01T00:42:22.734+0000
         * userName : 程序员拉大锯
         * cover : /imgs/2.png
         */

        private String id;
        private String title;
        private int viewCount;
        private int commentCount;
        private String publishTime;
        private String userName;
        private String cover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
