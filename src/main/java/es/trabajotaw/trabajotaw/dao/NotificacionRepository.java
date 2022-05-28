package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion,Integer> {
}
