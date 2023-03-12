package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.config.exception.PaymentNotFoundException;
import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.service.PagamentoService;
import br.com.alurafood.pagamentos.util.ExceptionHandler;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/payment")
public class PagamentoController {

    @Autowired
    private PagamentoService service;
    @Autowired
    private ExceptionHandler handler;

    @GetMapping
    public @ResponseBody ResponseEntity<Stream<PagamentoDto>> getAllPayments(@RequestHeader("page") int page, @RequestHeader("size") int size) {
        return service.getAll();
    }

    @GetMapping("/byId")
    public @ResponseBody PagamentoDto getPaymentById(@RequestHeader("id") Long id) {
        try {
            return service.getPaymentById(id);
        } catch (EntityNotFoundException E) {
            throw new PaymentNotFoundException(id);
        }
    }


    @PostMapping("/one")
    public ResponseEntity<Pagamento> postPayment(@RequestBody @Valid PagamentoDto paymentDto, UriComponentsBuilder uriBuilder) {
        try {
            return service.postPayment(paymentDto);
        } catch (ConstraintViolationException e) {
            return handler.ConstraintViolationExceptionHandler(e);
        }

    }

    @PostMapping()
    public ResponseEntity<List<Pagamento>> postPayments(@RequestBody @Valid List<PagamentoDto> paymentsDto) {
        try {
            return service.postPayments(paymentsDto);
        } catch (ConstraintViolationException e) {
            return handler.ConstraintViolationExceptionHandler(e);
        }

    }
    @PutMapping()
    public ResponseEntity<Pagamento> updatePayment(@RequestBody @Valid PagamentoDto paymentDto){
        try{
            return service.updatePayment(paymentDto);

        } catch (EntityNotFoundException E) {
            throw new PaymentNotFoundException(paymentDto.getId());
        }
    }

}
