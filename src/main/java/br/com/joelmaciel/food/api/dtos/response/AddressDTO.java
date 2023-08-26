package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddressDTO {

    private String zipCode;
    private String street;
    private String number;
    private String complement;
    private String district;
    private CitySummaryDTO city;

    public static AddressDTO toDTO(Address address) {
        return AddressDTO.builder()
                .zipCode(address.getZipCode())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .district(address.getDistrict())
                .city(CitySummaryDTO.toDTO(address.getCity()))
                .build();
    }

}
