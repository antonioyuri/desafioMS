package com.example.demo.config.validacao;

public class ErroDeFormularioDto {
	
	private String statusCode;
	private String message;
	
	public ErroDeFormularioDto(String statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}
	
	

}
