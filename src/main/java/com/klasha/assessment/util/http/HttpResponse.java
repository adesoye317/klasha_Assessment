package com.klasha.assessment.util.http;

import lombok.*;
import org.apache.http.Header;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HttpResponse {
    private int status;
    private Header[] headers;
    private String body;
}
