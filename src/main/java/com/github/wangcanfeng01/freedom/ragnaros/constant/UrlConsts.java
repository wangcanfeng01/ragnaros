package com.github.wangcanfeng01.freedom.ragnaros.constant;

/**
 * 功能说明：
 * Created in 16:12-2020/4/13
 *
 * @author wangcanfeng
 * @since 2.0.0
 */
public interface UrlConsts {

    /**
     * 默认接口地址
     */
    class Default {
        public final static String API_THROUGHPUT_WATCH_ALL = "/ui/throughput/all/watch";
        public final static String API_THROUGHPUT_OPEN_ALL = "/ui/throughput/all/open";
        public final static String API_THROUGHPUT_CLOSE_ALL = "/ui/throughput/all/close";
        public final static String API_THROUGHPUT_WATCH_SINGLE = "/ui/throughput/single/watch";
        public final static String API_THROUGHPUT_OPEN_SINGLE = "/ui/throughput/single/open";
        public final static String API_THROUGHPUT_CLOSE_SINGLE = "/ui/throughput/single/single";
    }

    /**
     * 自定义接口地址
     */
    class Customize {
        public final static String API_THROUGHPUT_WATCH_ALL = "api.throughput.watch.all";
        public final static String API_THROUGHPUT_OPEN_ALL = "api.throughput.open.all";
        public final static String API_THROUGHPUT_CLOSE_ALL = "api.throughput.close.all";
        public final static String API_THROUGHPUT_WATCH_SINGLE = "api.throughput.watch.single";
        public final static String API_THROUGHPUT_OPEN_SINGLE = "api.throughput.open.single";
        public final static String API_THROUGHPUT_CLOSE_SINGLE = "api.throughput.close.single";
    }
}