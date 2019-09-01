package application.data.repository.web;

import application.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface iOrderRepository extends JpaRepository<Order,Integer> {

}