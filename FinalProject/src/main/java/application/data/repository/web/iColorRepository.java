package application.data.repository.web;

import application.data.model.Color;
import org.springframework.data.repository.CrudRepository;

public interface iColorRepository extends CrudRepository<Color,Integer> {
}
