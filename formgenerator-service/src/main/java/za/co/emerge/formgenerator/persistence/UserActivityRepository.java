package za.co.emerge.formgenerator.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.emerge.formgenerator.persistence.entity.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long>

{

}
