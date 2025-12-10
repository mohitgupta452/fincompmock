package com.fincompmock;

import com.fincompmock.expectation.FinCompEntry;
import com.fincompmock.expectation.gst.authentication.AuthExpectation;
import com.fincompmock.expectation.gst.einvoice.EinvoiceExpectation;
import com.fincompmock.expectation.gst.ewaybill.EwayBillExpectation;
import com.fincompmock.expectation.gst.gstin.GstinVerificationExpectation;
import com.fincompmock.expectation.gst.gstin.PanGstinMappingExpectation;
import com.fincompmock.expectation.gst.gstin.ReturnStatusExpectation;
import com.fincompmock.expectation.gst.gstin.TaxpayerProfileExpectation;
import com.fincompmock.expectation.gst.gstr1.*;
import com.fincompmock.expectation.gst.gstr2a2b.Gstr2Expectation;
import com.fincompmock.expectation.gst.gstr3b.Gstr3bExpectation;
import com.fincompmock.expectation.gst.otp.OtpExpectation;
import com.fincompmock.expectation.gst.ledgers.LedgerExpectation;
import org.mockserver.client.server.MockServerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinCompMockApplication {

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			System.err.println("Usage: java -jar <jar> <mockserver-host>:<port>");
			System.exit(1);
		}

		String hostAndPort = args[0];
		System.out.println("Starting MockServer client for -> " + hostAndPort);

		// Load default expectations
		FinCompEntry finCompEntry = new FinCompEntry((hostAndPort));
		finCompEntry.registerAll();


		String[] parts = hostAndPort.split(":");
		String host = parts[0];
		int port = Integer.parseInt(parts[1]);

		registerAll(new MockServerClient(host, port));

		SpringApplication.run(FinCompMockApplication.class, args);
	}

	public static void registerAll(MockServerClient client) {
		Gstr1SaveExpectation.register(client);
		Gstr1SummaryExpectation.register(client);
		Gstr1SubmitExpectation.register(client);
		Gstr1FileExpectation.register(client);
		Gstr1StatusExpectation.register(client);

		GstinVerificationExpectation.register(client);
		PanGstinMappingExpectation.register(client);
		ReturnStatusExpectation.register(client);
		TaxpayerProfileExpectation.register(client);

		AuthExpectation.register(client);
		Gstr3bExpectation.register(client);
		Gstr2Expectation.register(client);
		OtpExpectation.register(client);
		EinvoiceExpectation.register(client);
		EwayBillExpectation.register(client);
		LedgerExpectation.register(client);
	}
}
