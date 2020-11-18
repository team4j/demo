package com.example.demo.export;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by dequan.yu on 2020/11/18.
 */
@NoArgsConstructor
@Data
public class LogRequest {

    private Query query;
    private Highlight highlight;
    private List<Sort> sort;

    @NoArgsConstructor
    @Data
    public static class Query {
        private Bool bool;

        @NoArgsConstructor
        @Data
        public static class Bool {
            private List<Must> must;

            @NoArgsConstructor
            @Data
            public static class Must {
                private MatchPhrase match_phrase;
                private QueryString query_string;
                private Range range;

                @NoArgsConstructor
                @Data
                public static class MatchPhrase {
                    private AppName appName;

                    @NoArgsConstructor
                    @Data
                    public static class AppName {
                        private String query;
                    }
                }

                @NoArgsConstructor
                @Data
                public static class QueryString {
                    private String query;
                    private String default_field;
                }

                @NoArgsConstructor
                @Data
                public static class Range {
                    private DtTime dtTime;

                    @NoArgsConstructor
                    @Data
                    public static class DtTime {
                        private long gte;
                        private long lt;
                    }
                }
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class Highlight {
        private Fields fields;

        @NoArgsConstructor
        @Data
        public static class Fields {
            private Content content;

            @NoArgsConstructor
            @Data
            public static class Content {
                private int fragment_size;
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class Sort {
        private String dtTime;
    }
}
