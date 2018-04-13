package br.com.motorapido.enums;

import br.com.minhaLib.enums.IEnum;

public enum ParametroEnum implements IEnum {

	IP_SERVIDOR("IP_SERVIDOR", "IP_SERVIDOR"),
	COD_PERFIL_MOTORISTA("COD_PERFIL_MOTORISTA","COD_PERFIL_MOTORISTA"),
	CHAVE_SEGURANCA("CHAVE_SEGURANCA","CHAVE_SEGURANCA"),
	CHAVE_MAPS("CHAVE_MAPS","CHAVE_MAPS");
	
	private final String descricao;
	private final String codigo;

	private ParametroEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}

	@Override
	public String getCodigo() {
		return codigo;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	public static ParametroEnum valueOf(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (ParametroEnum parametroEnum : values()) {
			if (parametroEnum.getCodigo().equals(codigo)) {
				return parametroEnum;
			}
		}
		return null;
	}

}
