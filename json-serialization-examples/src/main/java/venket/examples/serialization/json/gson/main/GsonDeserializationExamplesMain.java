package venket.examples.serialization.json.gson.main;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import venket.examples.serialization.json.model.Course;

/**
 * This class illustrates serialization and deserialization with JSON in several different
 * scenarios and is set up in a unit test like manner. Rather than assertions, each example
 * prints out the result of the serialization or deserialization with Gson for visualization.
 *
 * In general, Gson maps the structure of the JSON to the structure of a POJO and maps the values
 * accordingly, accomplishing the deserialization through Java reflection. Therefore, to
 * deserialize with Gson with minimal effort, create POJO with properties and nesting analogous
 * to that of the JSON and the deserialization becomes a one-liner.
 *
 * For the examples below, we work with Course and Course.Listing, which have the following
 * properties:
 *
 * Course
 *  - name(String)
 *  - listing(Listing)
 * 
 * Course.Listing
 *  - departmentId(String)
 *  - courseId(String)
 */
public class GsonDeserializationExamplesMain {

  /**
   * Deserialize the JSON: {"departmentId":"CS","courseId":"378"} to an equivalent Course.Listing
   * object.
   *
   * The deserialization should successfully create a Listing object that contains the departmentId
   * and courseId properties.
   */
  private static void printResultOfDeserializingCourseListing() {
    final String json = "{\"departmentId\":\"CS\",\"courseId\":\"378\"}";
    final Gson gson = new Gson();
    final Course.Listing modernWebAppsListing = gson.fromJson(json, Course.Listing.class);

    prettyPrintResult("Deserialize Course Listing", json, modernWebAppsListing);
  }

  /**
   * Deserialize the JSON: {"courseId":"378"} to an equivalent Course.Listing object.
   *
   * If only some of the properties are present in a given JSON, the deserialization should still
   * succeed. Here, it should successfully create a Listing object that only contains the courseId
   * property.
   */
  private static void printResultOfDeserializingPartialProperties() {
    final String json = "{\"courseId\":\"378\"}";
    final Gson gson = new Gson();
    final Course.Listing modernWebAppsListing = gson.fromJson(json, Course.Listing.class);

    prettyPrintResult("Deserialize Partial Properties", json, modernWebAppsListing);
  }

  /**
   * Deserialize the JSON: 
   * {"name":"Modern Web Apps","listing":{"departmentId":"CS","courseId":"378"}} to an equivalent
   * Course object.
   *
   * Nested JSON requires no additional work from Gson. As long as the structure in the POJOs are
   * analogous to that of the input JSON, Gson does most of the heavy lifting.
   */
  private static void printResultOfDeserializingNestedJson() {
    final String json = "{\"name\":\"Modern Web Apps\","
        + "\"listing\":{\"department_id\":\"CS\",\"course_id\":\"378\"}}";
    final Gson gson = new Gson();
    final Course modernWebAppsCourse = gson.fromJson(json, Course.class);

    prettyPrintResult("Deserialize Nested JSON Properties", json, modernWebAppsCourse);
  }

  /**
   * Deserialize the JSON: {"department_id":"CS","course_id":"378"} to an equivalent Course.Listing
   * object.
   *
   * The deserialization should successfully create a Listing object that contains
   * the departmentId and courseId properties, though they are listed with the property names
   * department_id and course_id, respectively. The key is that Gson needs to be built with
   * FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES and it will take care of the deserialization.
   */
  private static void printResultOfDeserializingSnakeCase() {
    final String json = "{\"department_id\":\"CS\",\"course_id\":\"378\"}";
    final Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
    final Course.Listing modernWebAppsListing = gson.fromJson(json, Course.Listing.class);

    prettyPrintResult("Deserialize Snake Case Property Names", json, modernWebAppsListing);
  }

  /**
   * Deserialize the JSON: 
   * [{"department_id":"CS","course_id":"378"}, {"department_id":"CS","course_id":"378"}] to an
   * equivalent Java List of Course.Listing objects.
   *
   * Since List objects leverage generics, in order to successfully deserialize to POJOs Gson
   * requires a TypeToken to exactly specify the class object in which to deserialize JSON with a
   * list of properties into a List of POJOs.
   */
  private static void printResultOfDeserializingLists() {
    final String json = "[{\"departmentId\":\"CS\",\"courseId\":\"378\"}, "
        + "{\"departmentId\":\"CS\",\"courseId\":\"380D\"}]";
    final Gson gson = new Gson();

    // Since the object to be deserialized is a Type that uses generics and Java doesn't
    // actually provide a way to specify generics, a TypeToken is necessary here to
    // specify the deserialization type as List<Course.Listing>. This is generally not
    // necessary unless the deserialization object Type uses generics.
    final Type courseListingListType = new TypeToken<List<Course.Listing>>(){}.getType();
    final List<Course.Listing> courseListings = gson.fromJson(json, courseListingListType);

    prettyPrintResult("Deserialize List of Items", json, courseListings);
  }

  private static void prettyPrintResult(
      final String objective, final String inputJson, final Object deserializedPojo) {
    System.out.println(String.format("-- Attempting to %s --", objective));
    System.out.println(String.format("Input JSON: %s", inputJson));
    System.out.println(String.format("Result: %s\n", deserializedPojo));
  }

  public static void main(final String[] args) {
    printResultOfDeserializingCourseListing();
    printResultOfDeserializingPartialProperties();
    printResultOfDeserializingNestedJson();
    printResultOfDeserializingSnakeCase();
    printResultOfDeserializingLists();
  }
}
