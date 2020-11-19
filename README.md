[![Join the chat at https://gitter.im/rdfhdt](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/rdfhdt)

# HDTQ Library, Java Implementation. Extension of HDT. http://www.rdfhdt.org

## Overview

HDTQ-Lib is a Java Library that extends HDT (https://github.com/rdfhdt/hdt-java) to support handling of named graphs (quads).

It provides several components:
- hdt-java-api: Abstract interface for dealing with HDT files.
- hdt-java-core: Core library for accessing HDT files programmatically from java. It allows creating HDT files from RDF and converting HDT files back to RDF. It also provides a Search interface to find triples that match a specific triple pattern.
- hdt-java-cli: Commandline tools to convert RDF to HDT and access HDT files from a terminal.
- hdt-jena: Jena integration. Provides a Jena Graph implementation that allows accessing HDT files as normal Jena Models. In turn, this can be used with Jena ARQ to provide more advanced searches, such as SPARQL, and even setting up SPARQL Endpoints with Fuseki.
- hdt-java-package: Generates a package with all the components and launcher scripts.
- hdt-fuseki: Packages Apache Jena Fuseki with the HDT jars and a fast launcher, to start a SPARQL endpoint out of HDT files very easily.


## Compiling

Use mvn install to let Apache Maven install the required jars in your system.

You can also run mvn assembly:single under hdt-java-package to generate a distribution directory with all the jars and launcher scripts.


## Usage

Examples on how to use this library are provided in the org.rdfhdt.hdt.example package of the hdt-java-core module (in src/main/java).
To test the functionality, one can execute the JUnit tests in org.rdfhdt.hdt.graphs package of the hdt-java-core module (in src/test/java).


## License

Each module has a different License. Core is LGPL, examples and tools are Apache.

hdt-api: Apache License
hdt-java-cli (Commandline tools and examples): Apache License
hdt-java-core: Lesser General Public License
hdt-jena: Lesser General Public License
hdt-fuseki: Apache License


## Authors

Julian Reindorf
