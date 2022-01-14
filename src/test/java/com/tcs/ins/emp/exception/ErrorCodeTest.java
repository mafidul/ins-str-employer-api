package com.tcs.ins.emp.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.ins.exception.ErrorCode;

@ExtendWith({ MockitoExtension.class })
public class ErrorCodeTest {

	@Test
	void codeTest() {
		String code = "000";
		ErrorCode errorCode = ErrorCode.UNKONWN;
		assertEquals(code, errorCode.getCode());
	}

	@Test
	void getMessgeTest() {
		String code = "Unknown Error: %s";
		ErrorCode errorCode = ErrorCode.UNKONWN;
		assertEquals(code, errorCode.getMessge());
	}

	@Test
	void getFormattedMessageTest() {
		String messge = "No record found: null";
		ErrorCode errorCode = ErrorCode.DATA_ERROR;
		assertEquals(messge, errorCode.getFormattedMessage(null));
	}
}
