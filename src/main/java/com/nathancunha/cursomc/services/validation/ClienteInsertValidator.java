package com.nathancunha.cursomc.services.validation;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nathancunha.cursomc.domain.enums.TipoCliente;
import com.nathancunha.cursomc.dto.ClienteNewDTO;
import com.nathancunha.cursomc.resources.exception.FieldMessage;
import com.nathancunha.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		if(isPessoasFisicaAndCpfIvalido(objDto)) {
			list.add(new FieldMessage("CpfOuCpnj","Cpf Invalido"));
		}
		
		if(isPessoasJuridicaAndCnpjIvalido(objDto)) {
			list.add(new FieldMessage("CpfOuCpnj","Cnpj Invalido"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

	private boolean isPessoasJuridicaAndCnpjIvalido(ClienteNewDTO objDto) {
		return objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCpnj());
	}

	private boolean isPessoasFisicaAndCpfIvalido(ClienteNewDTO objDto) {
		return objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCpnj());
	}
}