package com.popovgosha.motelbackend.repository;

import com.popovgosha.motelbackend.domain.Check;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
public interface CheckRepository extends JpaRepository<Check, Long>{
}
