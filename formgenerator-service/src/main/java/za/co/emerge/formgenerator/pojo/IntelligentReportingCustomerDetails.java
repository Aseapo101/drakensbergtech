package za.co.emerge.formgenerator.pojo;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 * IntelligentReportingCustomerDetails - The class maps the columns of the CSV files. Names variables corresponds to the CSV file column headers.
 */
public class IntelligentReportingCustomerDetails 
{

	
	private String clientName;
	private String companyName;
	private String numberOfActiveAcc;
	private String accBeneficiary;
	
	public IntelligentReportingCustomerDetails(String clientName,String companyName,String numberOfActiveAcc,String accBeneficiary) {
		
		this.clientName = clientName;
		this.companyName = companyName;
		this.numberOfActiveAcc = numberOfActiveAcc;
		this.accBeneficiary = accBeneficiary;
		
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
