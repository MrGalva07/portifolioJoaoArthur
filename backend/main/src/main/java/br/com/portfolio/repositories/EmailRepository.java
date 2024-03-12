package br.com.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portfolio.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long>{
}   