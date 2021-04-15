package org.bplustree.service.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("finalResponse")
public class FinalResponse {
	public SimpleResponse getResponse(Object data, HttpHeaders responseHeaders, HttpStatus status){
		return new SimpleResponse.SimpleResponseBuilder()
		        .result(data)
		        .responseHeaders(responseHeaders)
		        .status(status)
		        .build();
	}
}
