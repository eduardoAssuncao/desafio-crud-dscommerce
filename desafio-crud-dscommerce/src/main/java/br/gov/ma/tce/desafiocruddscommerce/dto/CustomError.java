package br.gov.ma.tce.desafiocruddscommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

}
