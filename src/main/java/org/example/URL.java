package org.example;

public enum URL {

    URL_GET("http://localhost:2222/endpoint1"),  // URL do endpoint1
    URL_POST("http://localhost:2222/endpoint2"),  // URL do endpoint2
    URL_PUT("http://localhost:2222/endpoint3");  // URL do endpoint3

    private String url;

    URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
