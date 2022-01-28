package com.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO {
	private String ano;
	private String owner;
	private double balance;
	
	public AccountDTO(String ano, double balance) {
		super();
		this.ano = ano;
		this.balance = balance;
	}
}
