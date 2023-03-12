package br.com.alurafood.pagamentos.repository;

import br.com.alurafood.pagamentos.model.Pagamento;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {






}
