package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.request.FormPaymentRequest;
import br.com.joelmaciel.food.api.dtos.response.FormPaymentDTO;
import br.com.joelmaciel.food.domain.exception.EntityInUseException;
import br.com.joelmaciel.food.domain.exception.FormPaymentNotFoundException;
import br.com.joelmaciel.food.domain.model.FormPayment;
import br.com.joelmaciel.food.domain.repository.FormPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FormPaymentService {
    private static final String MSG_FORM_PAYMENT_IN_USE
            = "Payment method code %d cannot be removed as it is in use";

    private final FormPaymentRepository formPaymentRepository;

    public List<FormPaymentDTO> findAll() {
        List<FormPayment> formPayments = formPaymentRepository.findAll();
        return formPayments.stream()
                .map(FormPaymentDTO::toDTO)
                .toList();
    }

    public FormPaymentDTO findById(Long formPaymentId) {
        FormPayment formPayment = searchById(formPaymentId);
        return FormPaymentDTO.toDTO(formPayment);
    }

    @Transactional
    public FormPaymentDTO save(FormPaymentRequest formPaymentRequest) {
        FormPayment formPayment = formPaymentRepository.save(FormPaymentRequest.toEntity(formPaymentRequest));
        return FormPaymentDTO.toDTO(formPayment);
    }

    @Transactional
    public FormPaymentDTO update(Long formPaymentId, FormPaymentRequest formPaymentRequest) {
        FormPayment formPayment = searchById(formPaymentId).toBuilder()
                .description(formPaymentRequest.getDescription())
                .build();
        return FormPaymentDTO.toDTO(formPaymentRepository.save(formPayment));
    }

    @Transactional
    public void remove(Long formPaymentId) {
        try {
            formPaymentRepository.deleteById(formPaymentId);
            formPaymentRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new FormPaymentNotFoundException(formPaymentId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_FORM_PAYMENT_IN_USE, formPaymentId));
        }

    }

    public FormPayment searchById(Long formPaymentId) {
        return formPaymentRepository.findById(formPaymentId)
                .orElseThrow(() -> new FormPaymentNotFoundException(formPaymentId));
    }
}
