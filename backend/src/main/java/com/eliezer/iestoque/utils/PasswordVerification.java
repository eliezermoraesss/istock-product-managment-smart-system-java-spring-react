package com.eliezer.iestoque.utils;

import java.util.regex.Pattern;

import com.eliezer.iestoque.services.exceptions.BusinessException;

public class PasswordVerification {

	public static void verificarForcaSenha(String senha) {
		int comprimentoMinimo = 8;

		boolean temLetraMaiuscula = Pattern.compile("[A-Z]").matcher(senha).find();
		boolean temLetraMinuscula = Pattern.compile("[a-z]").matcher(senha).find();
		boolean temNumero = Pattern.compile("\\d").matcher(senha).find();
		boolean temCaractereEspecial = Pattern.compile("\\W").matcher(senha).find();
		boolean temSequenciaComum = senha.matches(".*(?i)123456.*|.*(?i)abcdef.*");
		boolean temPalavraComum = senha.equalsIgnoreCase("password") || senha.equalsIgnoreCase("123456")
				|| senha.equalsIgnoreCase("qwerty");

		boolean temComprimentoMinimo = (senha.length() >= comprimentoMinimo) ? true : false;

		if (!temComprimentoMinimo) {
			throw new BusinessException("Sua senha e muito curta. Recomenda-se no minimo 8 caracteres.");
		} else if (temLetraMaiuscula && temLetraMinuscula && temNumero && temCaractereEspecial && !temSequenciaComum
				&& !temPalavraComum) {
			throw new BusinessException("Sua senha atende aos requisitos de seguranca. Parabens!");
		} else {
			throw new BusinessException("Sua senha nao atende aos requisitos de seguranca.");
		}
	}
}
