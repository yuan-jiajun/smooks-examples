package org.smooks.examples.test;

import org.smooks.Smooks;
import org.smooks.api.ExecutionContext;
import org.smooks.api.SmooksException;
import org.smooks.engine.DefaultApplicationContextBuilder;
import org.smooks.engine.report.HtmlReportGenerator;
import org.smooks.support.StreamUtils;
import org.smooks.io.payload.StringResult;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmooksTransform {

    private static byte[] messageIn = readInputMessage();

    private final Smooks smooks;

    public SmooksTransform() throws IOException, SAXException {
        // Instantiate Smooks with the config...
        smooks = new Smooks(new DefaultApplicationContextBuilder().withClassLoader(this.getClass().getClassLoader()).build());
        smooks.addResourceConfigs("smooks-example-test/EDI856-OB-LENNY-TURNKEY-smooks-config.xml");
    }

    private static byte[] readInputMessage() {
        try {
            return StreamUtils.readStream(Files.newInputStream(Paths.get("smooks-example-test/EDI856-OB-LENNY-TURNKEY-test-input.json")));
        } catch (IOException e) {
            e.printStackTrace();
            return "<no-message/>".getBytes();
        }
    }

    protected String runSmooksTransform(ExecutionContext executionContext) throws IOException, SmooksException {
        try {
            StringResult result = new StringResult();

            // Configure the execution context to generate a report...
            executionContext.getContentDeliveryRuntime().addExecutionEventListener(new HtmlReportGenerator("target/report/report.html", executionContext.getApplicationContext()));

            // Filter the input message to the outputWriter, using the execution context...
            smooks.filterSource(executionContext, new StreamSource(new ByteArrayInputStream(messageIn)), result);

            return result.toString();
        } finally {
            smooks.close();
        }
    }

    public String runSmooksTransform() throws IOException {
        ExecutionContext executionContext = smooks.createExecutionContext();
        return runSmooksTransform(executionContext);
    }
}