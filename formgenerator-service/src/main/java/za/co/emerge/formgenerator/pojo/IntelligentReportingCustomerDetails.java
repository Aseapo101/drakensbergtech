package za.co.emerge.formgenerator.pojo;

public class IntelligentReportingCustomerDetails 
{

	private String clientName;
	private String companyName;
	private String numberOfActiveAcc;
	private String accBeneficiary;
	
	public IntelligentReportingCustomerDetails(String... params) {
		
		this.clientName = params[0];
		this.companyName = params[1];
		this.numberOfActiveAcc = params[2];
		this.accBeneficiary = params[3];
		
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNumberOfActiveAcc() {
		return numberOfActiveAcc;
	}

	public void setNumberOfActiveAcc(String numberOfActiveAcc) {
		this.numberOfActiveAcc = numberOfActiveAcc;
	}

	public String getAccBeneficiary() {
		return accBeneficiary;
	}

	public void setAccBeneficiary(String accBeneficiary) {
		this.accBeneficiary = accBeneficiary;
	}
	
	
}
