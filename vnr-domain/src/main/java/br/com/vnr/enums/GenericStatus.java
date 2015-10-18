package br.com.vnr.enums;

public enum GenericStatus {

	ACTIVE("Ativo"), INACTIVE("Inativo");

	private String description;

	GenericStatus(String description) {
		this.description = description;
	}

	public String getDescricao() {
		return description;
	}

}
