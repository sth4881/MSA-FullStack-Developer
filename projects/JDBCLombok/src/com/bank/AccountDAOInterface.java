package com.bank;

public interface AccountDAOInterface {
	void accountInsert(AccountDTO newAccount);
	void accountList();
	void accountPlusUpdate(AccountDTO account);
	void accountMinusUpdate(AccountDTO account);
	boolean accountFindOne(String ano);
}