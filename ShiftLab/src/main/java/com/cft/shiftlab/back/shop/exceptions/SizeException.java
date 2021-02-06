package com.cft.shiftlab.back.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason = "impossible size value")
public class SizeException extends RuntimeException {
}
