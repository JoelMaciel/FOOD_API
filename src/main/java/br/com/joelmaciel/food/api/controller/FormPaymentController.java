package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.request.FormPaymentRequest;
import br.com.joelmaciel.food.api.dtos.response.FormPaymentDTO;
import br.com.joelmaciel.food.domain.service.FormPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/forms-payment")
public class FormPaymentController {

    private final FormPaymentService formPaymentService;

    @GetMapping
    public List<FormPaymentDTO> getAll() {
        return formPaymentService.findAll();
    }

    @GetMapping("{formPaymentId}")
    public FormPaymentDTO findByFormPaymentId(@PathVariable Long formPaymentId) {
        return formPaymentService.findById(formPaymentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormPaymentDTO save(@RequestBody @Valid FormPaymentRequest formPaymentRequest) {
        return formPaymentService.save(formPaymentRequest);
    }

    @PutMapping("/{formPaymentId}")
    public FormPaymentDTO update(@PathVariable Long formPaymentId, @RequestBody @Valid FormPaymentRequest formPaymentRequest) {
        return  formPaymentService.update(formPaymentId, formPaymentRequest);

    }

    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long formPaymentId) {
        formPaymentService.remove(formPaymentId);
    }


}
