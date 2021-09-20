package com.example.demo.config.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value =  TransactionSystemException.class)
	public  ErroDeFormularioDto handleTrasaction(TransactionSystemException exception) {
		ErroDeFormularioDto dto = new ErroDeFormularioDto();
		dto.setMessage("Erro na operação");
		dto.setCode(HttpStatus.NOT_FOUND.value());
		return  dto;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value =  NumberFormatException.class)
	public  ErroDeFormularioDto handleNumber(NumberFormatException exception) {
		ErroDeFormularioDto dto = new ErroDeFormularioDto();
		dto.setMessage("Erro na operação");
		dto.setCode(HttpStatus.NOT_FOUND.value());
		return  dto;
	}

}
