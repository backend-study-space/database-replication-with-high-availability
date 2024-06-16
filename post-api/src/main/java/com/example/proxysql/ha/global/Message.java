package com.example.proxysql.ha.global;

public record Message<T>(
        CudType cudType,
        T object,
        Topic topic
) {
    public enum CudType {
        INSERT, UPDATE, DELETE
    }

    public enum Topic {
        POST_INSERT,
        POST_DELETE,
        POST_UPDATE
    }
}


