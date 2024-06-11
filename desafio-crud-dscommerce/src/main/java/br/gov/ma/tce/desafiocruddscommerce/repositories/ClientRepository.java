package br.gov.ma.tce.desafiocruddscommerce.repositories;

import br.gov.ma.tce.desafiocruddscommerce.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
