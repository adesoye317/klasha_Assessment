package com.klasha.assessment.util.http;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
@Slf4j
public class HttpUtil {
    // private final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    public static final int TIMEOUT_MILLISECONDS = 1000;
    public static final int CUSTOM_EXCEPTION_STATUS_CODE = 591;

    public HttpResponse post(List<HttpHeader> headers, String url, int timeout, String contentType, String body) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).setSSLContext(this.getSSLContext()).build();
            HttpPost post = this.getHttpPost(headers, url, timeout, contentType, body);

            CloseableHttpResponse response = httpClient.execute(post);

            return new HttpResponse(response.getStatusLine().getStatusCode(), response.getAllHeaders(), EntityUtils.toString(response.getEntity()).trim());
        } catch (IOException | UnsupportedCharsetException | ParseException | NullPointerException |
                 KeyManagementException | KeyStoreException | NoSuchAlgorithmException ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
            return new HttpResponse(HttpUtil.CUSTOM_EXCEPTION_STATUS_CODE, null, ex.getMessage());
        } catch (Exception ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
            return new HttpResponse(HttpUtil.CUSTOM_EXCEPTION_STATUS_CODE, null, ex.getMessage());
        }
    }

    public HttpResponse get(List<HttpHeader> headers, String url, int timeout) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).setSSLContext(this.getSSLContext()).build();
            HttpGet get = this.getHttpGet(headers, url, timeout);

            CloseableHttpResponse response = httpClient.execute(get);

            return new HttpResponse(response.getStatusLine().getStatusCode(), response.getAllHeaders(), EntityUtils.toString(response.getEntity()).trim());
        } catch (IOException | UnsupportedCharsetException | ParseException | NullPointerException |
                 KeyManagementException | KeyStoreException | NoSuchAlgorithmException ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
            return new HttpResponse(HttpUtil.CUSTOM_EXCEPTION_STATUS_CODE, null, ex.getMessage());
        } catch (Exception ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
            return new HttpResponse(HttpUtil.CUSTOM_EXCEPTION_STATUS_CODE, null, ex.getMessage());
        }
    }


    private SSLContext getSSLContext() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        return new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
    }

    private HttpPost getHttpPost(List<HttpHeader> headers, String url, int timeout, String contentType, String body) {
        // custom method to create HTTP post object
        HttpPost post = new HttpPost(url);

        // add header
        // this is also supposed to be a code line * post.setHeader("cache-control", "no-cache");
        // this is also supposed to be a code line * post.setHeader("Accept-Encoding", "gzip,deflate");
        post.setHeader("Content-Type", contentType);
        //set custom headers
        setHeaders(post, headers);

        //set request config
        RequestConfig requestConfig = RequestConfig.custom()
                // set timeout
                .setSocketTimeout(120 * TIMEOUT_MILLISECONDS)
                .setConnectTimeout(120 * TIMEOUT_MILLISECONDS)
                .setConnectionRequestTimeout(timeout * TIMEOUT_MILLISECONDS)
                .build();
        post.setConfig(requestConfig);
        post.setEntity(new StringEntity(body, this.getContentType(contentType)));

        return post;
    }

    private HttpGet getHttpGet(List<HttpHeader> headers, String url, int timeout) {
        // custom method to create HTTP post object
        HttpGet get = new HttpGet(url);

        // add header
        // this is also supposed to be a code line * post.setHeader("cache-control", "no-cache");
        // this is also supposed to be a code line * post.setHeader("Accept-Encoding", "gzip,deflate");
        // get.setHeader("Content-Type", getContentTypeString(contentType));
        //set custom headers
        setHeaders(get, headers);

        //set request config
        RequestConfig requestConfig = RequestConfig.custom()
                // set timeout
                .setSocketTimeout(120 * TIMEOUT_MILLISECONDS)
                .setConnectTimeout(120 * TIMEOUT_MILLISECONDS)
                .setConnectionRequestTimeout(timeout * TIMEOUT_MILLISECONDS)
                .build();
        get.setConfig(requestConfig);

        return get;
    }

    private void setHeaders(HttpPost post, List<HttpHeader> headers) {
        if (headers != null && headers.size() > 0) {
            headers.stream().filter(Objects::nonNull).forEachOrdered((h) -> post.setHeader(h.getName(), h.getValue()));
        }
    }

    private void setHeaders(HttpGet get, List<HttpHeader> headers) {
        if (headers != null && headers.size() > 0) {
            headers.stream().filter(Objects::nonNull).forEachOrdered((h) -> get.setHeader(h.getName(), h.getValue()));
        }
    }

    private ContentType getContentType(String mimeType) {
        // add as needed
        if (mimeType.contains("application/json"))
            return ContentType.APPLICATION_JSON;

        else if (mimeType.contains("application/xml"))
            return ContentType.APPLICATION_XML;

        else if (mimeType.contains("text/xml"))
            return ContentType.TEXT_XML;

        else if (mimeType.contains("text/plain"))
            return ContentType.TEXT_PLAIN;

        else return ContentType.APPLICATION_JSON;
    }
}
