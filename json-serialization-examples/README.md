# JSON Serialization Examples

The examples in this package leverage the [Course and Course.Listing POJOs](src/main/java/venket/examples/serialization/json/model/Course.java) as driving examples for serialization and deserialization. The pom.xml file contains all of the dependencies used, such as Gson.

## Deserialization with Gson

The [GsonDeserializationExamplesMain](src/main/java/venket/examples/serialization/json/gson/main/GsonDeserializationExamplesMain.java) Java file contains five separate examples for deserialization with Gson:

* Deserializing a JSON string
* Deserializing a JSON string with some properties missing
* Deserializing a JSON string with nested JSON
* Deserializing a JSON string with property names in snake case to a POJO with the analogous properties in camel case
* Deserializing a list of JSON items to an equiavalent Java List.

The file itself is organized similar to a suite of unit tests but each case simply pretty-prints the output of deserialization. Running it prints out the input JSON and the output deserialized object's properties and the actual execution can be accomplished either in Eclipse or through the command line leveraging Maven.

### Running with Eclipse

Right-click on the GsonDeserializationExamplesMain.java in the Project Explorer window and select Run As > Java Application.

### Running via Maven on Command Line

Execute on the command line: ```mvn exec:java -Pdeserialization-examples```
