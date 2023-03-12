package br.com.alurafood.pagamentos.service;


import br.com.alurafood.pagamentos.config.exception.PaymentNotFoundException;
import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;


    @Autowired
    private ModelMapper modelMapper;


    public ResponseEntity<Stream<PagamentoDto>> getAll() {
        Stream<PagamentoDto> payments = repository.findAll().stream().map(p -> modelMapper.map(p, PagamentoDto.class));
        return new ResponseEntity<Stream<PagamentoDto>>(payments, HttpStatus.CREATED);
    }

    public PagamentoDto getPaymentById(Long id) {
        Pagamento payment = repository.findById(id).orElseThrow(() -> {
            return new PaymentNotFoundException(id);
        });
        return modelMapper.map(payment, PagamentoDto.class);
    }


    public ResponseEntity<Pagamento> postPayment(PagamentoDto paymentDto) {
        paymentDto.setStatus(Status.CRIADO);
        Pagamento payment = repository.save(modelMapper.map(paymentDto, Pagamento.class));

        return new ResponseEntity<Pagamento>(payment, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Pagamento>> postPayments(List<PagamentoDto> paymentsDto) {
        List<Pagamento> listPagamentos = new ArrayList<Pagamento>();
        paymentsDto.forEach(payment -> {
            payment.setStatus(Status.CRIADO);
            listPagamentos.add(repository.save(modelMapper.map(payment, Pagamento.class)));
        });
        return new ResponseEntity<List<Pagamento>>(listPagamentos, HttpStatus.CREATED);
    }


    public ResponseEntity<Pagamento> updatePayment(PagamentoDto paymentDto) {
        PagamentoDto payment = this.getPaymentById(paymentDto.getId());


        return new ResponseEntity<>(modelMapper.map(payment, Pagamento.class), HttpStatus.CREATED);
    }


}
