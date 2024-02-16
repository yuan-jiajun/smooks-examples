Smooks Examples
===============

![Build Status](https://github.com/smooks/smooks-examples/workflows/CI/badge.svg)

Examples are essential in helping newcomers get started with Smooks. This project is a catalogue of code examples illustrating the many uses of Smooks. Most of the examples are accompanied by a README file that describes the example and instructions on how to run it. [Maven 3](https://maven.apache.org/) and JDK 17 need to be installed in order to build any of the examples. All the examples are built and run in the same way:

1. Clone this repository into a new directory with `git clone https://github.com/smooks/smooks-examples.git`
2. Change directory to `smooks-examples` and build all the examples with `mvn clean package`
3. Run an example by changing to the directory containing the example and running `mvn exec:exec`

Read the [Smooks User Guide](https://www.smooks.org/documentation/) to learn more about Smooks. Example contributions in the form of [pull requests](https://github.com/smooks/smooks-examples/pulls) are more than welcome, and in fact, such contributions are encouraged since they help the Smooks community to grow.

## Catalogue

The following table briefly describes each example within this project. More information on an example can be found in the accompanying README file within the example's directory.

| **Name**                                                               | **Description**                                                                                                                                                                                                                                                 |
| ---------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [camel-csv-to-xml](camel/camel-csv-to-xml/README.md)                   | Illustrates the [Camel cartridge](https://github.com/smooks/smooks-camel-cartridge) in a basic example where [Apache Camel](https://camel.apache.org/) directs the transformation from CSV into XML.                                                            |
| [camel-dataformat](camel/camel-dataformat/README.md)                   | Transforms from EDI into XML using the [unmarshal](https://camel.apache.org/components/next/eips/unmarshal-eip.html) EIP in [Apache Camel](https://camel.apache.org/) with the help of the [Camel cartridge](https://github.com/smooks/smooks-camel-cartridge). |
| [camel-integration](camel/camel-integration/README.md)                 | Illustrates the routing capabilities of Smooks when used from within [Apache Camel](https://camel.apache.org/).                                                                                                                                                 |
| [camel-unedifact-to-xml](camel/camel-unedifact-to-xml/README.md)       | Another basic example of the [Camel cartridge](https://github.com/smooks/smooks-camel-cartridge) where [Apache Camel](https://camel.apache.org/) directs the transformation from EDIFACT into XML.                                                              |
| [cross-domain-solution](cross-domain-solution/README.md)               | Uses Smooks together the [DFDL cartridge](https://github.com/smooks/smooks-dfdl-cartridge) to connect two different security domains, permitting data to flow from one domain into another while minimising the associated security risks.                      |
| [csv-to-java](csv-to-java/README.md)                                   | Smooks XML configuration for binding CSV records to Java beans.                                                                                                                                                                                                 |
| [csv-to-java-programmatic](csv-to-java-programmatic/README.md)         | Smooks programmatic configuration for binding CSV records to Java beans.                                                                                                                                                                                        |
| [csv-to-xml](csv-to-xml/README.md)                                     | Configures Smooks to turning a CSV stream into an XML stream.                                                                                                                                                                                                   |
| [csv-to-xml-2](csv-to-xml-2/README.md)                                 | Extends the `csv-to-xml` example by demonstrating how to perform XML transformation on the individual records in the CSV stream.                                                                                                                                |
| [csv-variable-record](csv-variable-record/README.md)                   | Configures Smooks to ingest variable records in a CSV stream.                                                                                                                                                                                                   |
| [dao-router](dao-router/README.md)                                     | Demonstrates routing and persistence of data access objects with the [persistence cartridge](https://github.com/smooks/smooks-persistence-cartridge).                                                                                                           |
| [db-extract-transform-load](db-extract-transform-load/README.md)       | Illustrates how Smooks can be used to extract data from an EDI message and load this data into a database, all without writing a single line of Java code, except for the `Main` class that executes Smooks.                                                    |
| [drools-fusion](drools-fusion/README.md)                               | Shows Smooks feeding events to [Drools](https://www.drools.org/) for [complex event processing (CEP)](https://en.wikipedia.org/wiki/Complex_event_processing) and business process management (BPM).                                                            |
| [dynamic-model-builder](dynamic-model-builder/README.md)               | Binds an XML document that contains multiple XML namespaces to different Java beans, depending on the namespace, and then serialises the beans back to XML.                                                                                                     |
| [edi-to-java](edi-to-java/README.md)                                   | Configures Smooks to ingest an EDI stream and bind data from that stream to Java beans.                                                                                                                                                                         |
| [edi-to-xml](edi-to-xml/README.md)                                     | Configures Smooks to ingest an EDI stream and write it out as XML.                                                                                                                                                                                              |
| [edi-with-import-to-java](edi-with-import-to-java/README.md)           | Very similar to the `edi-to-java` example but references parent events by name when binding Java beans to EDI data.                                                                                                                                             |
| [edifact-to-java](edifact-to-java/README.md)                           | Binds an EDIFACT document to a Java POJO with JAXB.                                                                                                                                                                                                             |
| [edifact-to-xml](edifact-to-xml/README.md)                             | Ingests an EDIFACT document to generate an XML stream that can be processed by standard XML tools (XSLT, XQuery etc) or by other Smooks components.                                                                                                             |
| [file-router](file-router/README.md)                                   | Smooks splitting a message into smaller messages and route the smaller messages to file.                                                                                                                                                                        |
| [fixed-length-to-java](fixed-length-to-java/README.md)                 | Configures Smooks to turn fixed-length records into Java beans.                                                                                                                                                                                                 |
| [flatfile-to-xml-regex](flatfile-to-xml-regex/README.md)               | Shows how to use the <regex:reader> to process a somewhat arbitrary flat file format.                                                                                                                                                                           |
| freemarker-huge-transform                                              | Simulates the transformation of a large XML document in Smooks using [FreeMarker](https://freemarker.apache.org/index.html).                                                                                                                                    |
| [groovy](groovy/README.md)                                             | Demonstrates how Smooks can be used to perform fragment-based transformations using Groovy.                                                                                                                                                                     |
| [java-basic](java-basic/README.md)                                     | Illustrates how Smooks can be used to apply a Java-based transform on a message fragment.                                                                                                                                                                       |
| [java-to-edifact](java-to-edifact/README.md)                           | Smooks XML configuration for turning Java POJOs into EDIFACT.                                                                                                                                                                                                   |
| [java-to-edifact-programmatic](java-to-edifact-programmatic/README.md) | Smooks programmatic configuration for turning Java POJOs into EDIFACT.                                                                                                                                                                                          |
| [java-to-java](java-to-java/README.md)                                 | This example illustrates how Smooks can be used to transform one Java Object Graph to another Java Object Graph, without constructing any intermediate models.                                                                                                  |
| [java-to-xml](java-to-xml/README.md)                                   | This example illustrates how Smooks can be used to transform a Java object graph to XML.  It shows how to use a JavaSource to supply a Java object graph to Smooks for transformation.                                                                          |
| [jms-router](jms-router/README.md)                                     | Illustrates how Smooks can be used to split a message (XML in this case) and route the split messages to a JMS Queue.                                                                                                                                           |
| [json-to-java](json-to-java/README.md)                                 | Illustrates the use of the [JSON cartridge](https://github.com/smooks/smooks-json-cartridge) for reading JSON from within Smooks.                                                                                                                               |
| [model-driven-basic]()                                                 | Basic "Model Driven Transformation" using the Smooks JavaBean and Templating cartridges.  The templating is done using [FreeMarker](https://freemarker.apache.org/index.html).                                                                                  |
| [model-driven-basic-virtual](model-driven-basic-virtual/README.md)     | This example is exactly the same as the `model-driven-basic` example except that it uses maps.                                                                                                                                                                  |
| [pipelines](pipelines/README.md)                                       | Ingests a CSV file consisting of individual sale orders with pipelines. Each individual order is transformed into JSON and XML while also an EDIFACT document aggregating the orders is created.                                                                |
| [profiling](profiling/README.md)                                       | An example of how Smooks can be used to profile a set of messages, and so share transformation/analysis configurations across a message set.                                                                                                                    |
| [sj-testimonial](sj-testimonial/README.md)                             | Use case donated by the Swedish Railway (SJ) where EDI describing information about train compositions (rolling stock) is bound to Java beans.                                                                                                                  |
| [validation-basic](validation-basic/README.md)                         | Illustrates how Smooks can be sed to validate XML message fragments.                                                                                                                                                                                            |
| [xml-read-write](xml-read-write/README.md)                             | Reads and write an XML message to and from a Java object model.                                                                                                                                                                                                 |
| [xml-read-write-transform](xml-read-write-transform/README.md)         | Read and write different versions of an XML into a common Java object model.                                                                                                                                                                                    | 
| [xml-to-edi](xml-to-edi/README.md)                                     | Configures Smooks to ingest an XML stream and turn it into EDI.                                                                                                                                                                                                 |
| [xml-to-java](xml-to-java/README.md)                                   | Illustration of how Smooks can be used to extract data from an XML message, and use the data to populate an Object graph.  It also shows how this Object graph is then accessible outside Smooks via the ExecutionContext.                                      |
| [xml-to-java-virtual](xml-to-java-virtual/README.md)                   | Exactly the same as `xml-to-java` accept that it uses maps.                                                                                                                                                                                                     |
| [xslt-basic](xslt-basic/README.md)                                     | Illustrates how Smooks can be used to apply an XSLT-based transformation on a message fragment.                                                                                                                                                                 |
| [xslt-groovy](xslt-groovy/README.md)                                   | Illustration of how Smooks can be used to combine Groovy scripting with XSLT to perform an Order message transform, simplifying the XSLT and maintaining its portability across XSLT processors.                                                                |
| [xslt-namespaces](xslt-namespaces/README.md)                           | An example that illustrates how Smooks can be used to apply an XSLT-based transformation on a message fragment that contains namespaces.                                                                                                                        |
| [yaml-to-java](yaml-to-java/README.md)                                 | Demonstrates the use of the [YAML cartridge](https://github.com/smooks/smooks-yaml-cartridge/) for reading YAML within Smooks                                                                                                                                   |

## Smooks Execution Report

Most of the examples generate a Smooks Execution Report in the `target/report` directory of the example.  This can be a useful tool for comprehending the processing performed by Smooks.
