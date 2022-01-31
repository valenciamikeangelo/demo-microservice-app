package com.caista.birapps.etis.domain.tas;

public class TaxPayerLedgerRequest<T> {
	T taxPayer;

	public T getTaxPayer() {
		return taxPayer;
	}

	public void setTaxPayer(T taxPayer) {
		this.taxPayer = taxPayer;
	}

	public TaxPayerLedgerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaxPayerLedgerRequest(T taxPayer) {
		super();
		this.taxPayer = taxPayer;
	}
}
