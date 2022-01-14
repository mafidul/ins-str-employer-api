package com.tcs.ins.emp.messaging;

import com.tcs.ins.emp.service.model.LoginModel;

public interface BatchProcessTriggeredEventProducer {

	void sendMessage(LoginModel event);
}