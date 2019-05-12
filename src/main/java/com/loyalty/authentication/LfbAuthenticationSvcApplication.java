package com.loyalty.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class LfbAuthenticationSvcApplication {

	public static void main(String[] args) {
		try {
			System.setProperty("ip", InetAddress.getLocalHost().getHostAddress());
			SpringApplication.run(LfbAuthenticationSvcApplication.class, args);
		}catch (UnknownHostException e){
			e.printStackTrace();
		}
	}

}
