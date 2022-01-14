package com.tcs.ins.emp.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.ins.exception.ApplicationException;

@ExtendWith({ MockitoExtension.class })
public class ApplicationExceptionTest {

	@Test
	void errorCodeTest() {
		assertEquals("501", ApplicationException.errorCode(null, "501").getMessage());
	}

	@Test
	void unknownTest() {
		assertEquals("000", ApplicationException.unknown("000").getMessage());
	}

	@Test
	void noRecordFoundTest() {
		assertEquals("002", ApplicationException.noRecordFound("002").getMessage());
	}

	@Test
	void noRecordFoundThrowTest() {
		assertEquals("002", ApplicationException.noRecordFound("002", null).getMessage());
	}

	@Test
	void duplicateRecordTest() {
		assertEquals("003", ApplicationException.duplicateRecord("003").getMessage());
	}

	@Test
	void duplicateRecordThrowTest() {
		assertEquals("003", ApplicationException.duplicateRecord("003", null).getMessage());
	}

	@Test
	void invalidRecordTest() {
		assertEquals("005", ApplicationException.invalidRecord("005").getMessage());
	}

	@Test
	void accessViolationTest() {
		assertEquals("004", ApplicationException.accessViolation("004").getMessage());
	}

	@Test
	void invalidStateTest() {
		assertEquals("022", ApplicationException.invalidState("022").getMessage());
	}

	@Test
	void invalidOperationTest() {
		assertEquals("023", ApplicationException.invalidOperation("023").getMessage());
	}

	@Test
	void mqProducerTest() {
		assertEquals("052", ApplicationException.mqProducer("052", null).getMessage());
	}

	@Test
	void fileNotFoundTest() {
		assertEquals("006", ApplicationException.fileNotFound("006").getMessage());
	}

	@Test
	void fileNotFoundnThrowTest() {
		assertEquals("006", ApplicationException.fileNotFound("006", null).getMessage());
	}

	@Test
	void serverErrorTest() {
		assertEquals("042", ApplicationException.serverError("042").getMessage());
	}

	@Test
	void serverErrorThrowTest() {
		assertEquals("042", ApplicationException.serverError("042", null).getMessage());
	}

	@Test
	void unAuthorizedTest() {
		assertEquals("043", ApplicationException.unAuthorized("043").getMessage());
	}

	@Test
	void unAuthorizedThrowTest() {
		assertEquals("043", ApplicationException.unAuthorized("043", null).getMessage());
	}

	@Test
	void ruleEngineTest() {
		assertEquals("024", ApplicationException.ruleEngine("024").getMessage());
	}

	@Test
	void ruleEngineThrowTest() {
		assertEquals("024", ApplicationException.ruleEngine("024", null).getMessage());
	}
}
