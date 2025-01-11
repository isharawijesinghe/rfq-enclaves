package com.rfq.quote.enclave;

import com.rfq.enclave.autoconfigure.EnableNitroEnclavesEnclaveSide;
import com.rfq.enclave.enclave.server.NitroEnclaveServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableNitroEnclavesEnclaveSide
public class QuoteAggregatorApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(QuoteAggregatorApplication.class, args);
		NitroEnclaveServer server = ctx.getBean(NitroEnclaveServer.class);
		server.run();
	}

}
