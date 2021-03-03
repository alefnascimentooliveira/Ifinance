package com.financas.domain.dto.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {

	private String tipo = "Bearer";

	private String token;

}
