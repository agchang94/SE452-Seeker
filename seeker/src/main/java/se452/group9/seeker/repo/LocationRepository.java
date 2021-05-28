package se452.group9.seeker.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import se452.group9.seeker.model.Location;

public interface LocationRepository extends JpaRepository <Location, Long>{
    @Query("Select l from locations l where l.cityName like %?1% OR l.stateName LIKE %?1%")
    public List<Location> search(String keyword);

}
