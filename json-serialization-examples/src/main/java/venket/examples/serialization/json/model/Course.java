package venket.examples.serialization.json.model;

public class Course {

  public static class Listing {
    private String departmentId;
    private String courseId;

    public Listing(final String departmentId, final String courseId) {
      this.departmentId = departmentId;
      this.courseId = courseId;
    }

    public String getDepartmentId() {
      return departmentId;
    }

    public String getCourseId() {
      return courseId;
    }

    @Override
    public String toString() {
        return "Listing [departmentId=" + departmentId + ", courseId=" + courseId + "]";
    }
  }

  private String name;
  private Listing listing;

  public Course(final String name, final Listing listing) {
    this.name = name;
    this.listing = listing;
  }

  public String getName() {
    return name;
  }

  public Listing getListing() {
    return listing;
  }

  @Override
  public String toString() {
    return "Course [name=" + name + ", listing=" + listing + "]";
  }
}
