package com.example.demo.export;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by dequan.yu on 2020/11/18.
 */
@NoArgsConstructor
@Data
public class LogResponse {

    private int took;
    private boolean timed_out;
    private Shards _shards;
    private HitsX hits;

    @NoArgsConstructor
    @Data
    public static class Shards {
        private int total;
        private int successful;
        private int skipped;
        private int failed;
    }

    @NoArgsConstructor
    @Data
    public static class HitsX {
        private Total total;
        private Object max_score;
        private List<Hits> hits;

        @NoArgsConstructor
        @Data
        public static class Total {
            private int value;
            private String relation;
        }

        @NoArgsConstructor
        @Data
        public static class Hits {
            private String _index;
            private String _type;
            private String _id;
            private Object _score;
            private Source _source;
            private Highlight highlight;
            private List<Long> sort;

            @NoArgsConstructor
            @Data
            public static class Source {
                private String appName;
                private String className;
                private String content;
                private String dateTime;
                private long dtTime;
                private String logLevel;
                private String method;
                private String serverName;
                private String traceId;
            }

            @NoArgsConstructor
            @Data
            public static class Highlight {
                private List<String> content;
            }
        }
    }
}
