package se.citerus.dddsample.domain.model.cargo;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import se.citerus.dddsample.domain.model.location.Location;
import se.citerus.dddsample.domain.model.voyage.Voyage;
import se.citerus.dddsample.domain.shared.ValueObject;

import javax.persistence.*;
import java.util.Date;

/**
 * An itinerary consists of one or more legs.
 */
@Entity
public class Leg implements ValueObject<Leg> {

    @ManyToOne
    @JoinColumn(name = "voyage_id", foreignKey = @ForeignKey(name = "voyage_fk"))
    private Voyage voyage;

    @ManyToOne
    @JoinColumn(name = "load_location_id", foreignKey = @ForeignKey(name = "load_location_fk"))
    private Location loadLocation;

    @ManyToOne
    @JoinColumn(name = "unload_location_id", foreignKey = @ForeignKey(name = "unload_location_fk"))
    private Location unloadLocation;

    @Column(name = "load_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loadTime;

    @Column(name = "unload_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unloadTime;

  public Leg(Voyage voyage, Location loadLocation, Location unloadLocation, Date loadTime, Date unloadTime) {
    Validate.noNullElements(new Object[] {voyage, loadLocation, unloadLocation, loadTime, unloadTime});
    
    this.voyage = voyage;
    this.loadLocation = loadLocation;
    this.unloadLocation = unloadLocation;
    this.loadTime = loadTime;
    this.unloadTime = unloadTime;
  }

  public Voyage voyage() {
    return voyage;
  }

  public Location loadLocation() {
    return loadLocation;
  }

  public Location unloadLocation() {
    return unloadLocation;
  }

  public Date loadTime() {
    return new Date(loadTime.getTime());
  }

  public Date unloadTime() {
    return new Date(unloadTime.getTime());
  }

  @Override
  public boolean sameValueAs(final Leg other) {
    return other != null && new EqualsBuilder().
      append(this.voyage, other.voyage).
      append(this.loadLocation, other.loadLocation).
      append(this.unloadLocation, other.unloadLocation).
      append(this.loadTime, other.loadTime).
      append(this.unloadTime, other.unloadTime).
      isEquals();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Leg leg = (Leg) o;

    return sameValueAs(leg);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().
      append(voyage).
      append(loadLocation).
      append(unloadLocation).
      append(loadTime).
      append(unloadTime).
      toHashCode();
  }

  Leg() {
    // Needed by Hibernate
  }

  // Auto-generated surrogate key
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
