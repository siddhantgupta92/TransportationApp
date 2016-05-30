package org.transport.main.service;

import java.math.BigDecimal;

import org.transport.main.PrepaidPass;

public class PrepaidPassService {
	public boolean checkBalance(BigDecimal fare, PrepaidPass pass) {
		if (fare.compareTo(pass.getBalance()) < 1 || fare.compareTo(pass.getBalance()) == 0) {
			pass.setBalance(pass.getBalance().subtract(fare));
			return true;
		}
		return false;
	}
}
