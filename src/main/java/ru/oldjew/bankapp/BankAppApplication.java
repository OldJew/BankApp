package ru.oldjew.bankapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.oldjew.bankapp.controller.UserOperationController;

import java.math.BigDecimal;

@SpringBootApplication
public class BankAppApplication {

	private static final Logger logger = LoggerFactory.getLogger(BankAppApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(BankAppApplication.class, args);

		UserOperationController userOperationController =
				(UserOperationController)app.getBean(UserOperationController.class);
		logger.info(userOperationController.getBalance(1L).toString());
		logger.info(userOperationController.takeMoney(1L, new BigDecimal(1000)).toString());
		logger.info(userOperationController.putMoney(1l, new BigDecimal(1000)).toString());


	}
}
