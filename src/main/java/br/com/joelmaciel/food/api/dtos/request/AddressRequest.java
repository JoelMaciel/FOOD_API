package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class AddressRequest {

    @NotBlank
    private String zipCode;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    private String complement;
    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdRequest city;

    public static Address toEntity(AddressRequest addressRequest) {
        return Address.builder()
                .zipCode(addressRequest.zipCode)
                .street(addressRequest.getStreet())
                .number(addressRequest.getNumber())
                .complement(addressRequest.getComplement())
                .district(addressRequest.getDistrict())
                .city(CityIdRequest.toEntity(addressRequest.getCity()))
                .build();
    }
}
