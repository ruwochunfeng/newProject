package com.zhoubing.newsproject.bean;

import java.util.List;

/**
 * Created by dell on 2017/6/26.
 */

public class MeiziFuli {
    /**
     * error : false
     * results : [{"_id":"56cc6d29421aa95caa708153","createdAt":"2016-01-12T01:40:27.540Z","desc":"1.12","publishedAt":"2016-01-12T04:15:24.605Z","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezwgshzjpmj21ao1y0tf0.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa708143","createdAt":"2016-01-11T01:55:37.294Z","desc":"1.11","publishedAt":"2016-01-11T04:41:57.989Z","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezvbmuqz9cj20hs0qoq6o.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa708129","createdAt":"2016-01-08T01:18:42.681Z","desc":"1.8","publishedAt":"2016-01-08T05:44:42.715Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1ezrtpmdv45j20u00spahy.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa708118","createdAt":"2016-01-07T01:39:07.601Z","desc":"1.7","publishedAt":"2016-01-07T03:36:25.265Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1ezqon28qrzj20h80pamze.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa7080ff","createdAt":"2016-01-06T03:01:53.814Z","desc":"1.6","publishedAt":"2016-01-06T05:04:37.20Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1ezplg7s8mdj20xc0m8jwf.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa7080f6","createdAt":"2016-01-05T01:40:17.832Z","desc":"1.5","publishedAt":"2016-01-05T05:47:06.972Z","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezodh37eadj20n90qotfr.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa7080e9","createdAt":"2016-01-04T01:22:47.291Z","desc":"1.4","publishedAt":"2016-01-04T04:24:04.641Z","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1ezn79ievhzj20p00odwhr.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d29421aa95caa7080ca","createdAt":"2015-12-31T01:31:20.602Z","desc":"12.31--2015最后一天福利满满","publishedAt":"2015-12-31T04:29:30.902Z","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezil3n0cqdj20p00ou776.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d28421aa95caa7080bc","createdAt":"2015-12-30T02:28:56.847Z","desc":"12.30","publishedAt":"2015-12-30T04:14:13.83Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1ezhh5rh1r9j20hs0qoadi.jpg","used":true,"who":"张涵宇"},{"_id":"56cc6d26421aa95caa7080b0","createdAt":"2015-12-29T01:55:58.728Z","desc":"12.29","publishedAt":"2015-12-29T05:04:33.48Z","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezgal5vpjfj20go0p0adq.jpg","used":true,"who":"张涵宇"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 56cc6d29421aa95caa708153
         * createdAt : 2016-01-12T01:40:27.540Z
         * desc : 1.12
         * publishedAt : 2016-01-12T04:15:24.605Z
         * type : 福利
         * url : http://ww2.sinaimg.cn/large/7a8aed7bjw1ezwgshzjpmj21ao1y0tf0.jpg
         * used : true
         * who : 张涵宇
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
