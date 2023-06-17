package com.klasha.assessment.util.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HttpHeader {
    private String name;
    private String value;
}
