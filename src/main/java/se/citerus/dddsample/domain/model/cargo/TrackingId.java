package se.citerus.dddsample.domain.model.cargo;

import org.apache.commons.lang.Validate;
import se.citerus.dddsample.domain.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Uniquely identifies a particular cargo. Automatically generated by the application.
 *  
 */
@Embeddable
public final class TrackingId implements ValueObject<TrackingId> {

  @Column(name = "TRACKING_ID", updatable = false, unique = true)
  private String id;

  /**
   * Constructor.
   *
   * @param id Id string.
   */
  public TrackingId(final String id) {
    Validate.notNull(id);
    this.id = id;
  }


  /**
   * @return String representation of this tracking id.
   */
  public String idString() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TrackingId other = (TrackingId) o;

    return sameValueAs(other);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean sameValueAs(TrackingId other) {
    return other != null && this.id.equals(other.id);
  }

  @Override
  public String toString() {
    return id;
  }

  TrackingId() {
    // Needed by Hibernate
  }
}
