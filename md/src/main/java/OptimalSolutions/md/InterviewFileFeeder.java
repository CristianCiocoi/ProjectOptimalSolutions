package OptimalSolutions.md;

public class InterviewFileFeeder {
	private String nameA;
	private String surnameB;
	private String mailC;
	private String genderD;
	private String profileImageE;
	private String paymentMethodF;
	private String transactionsG;
	private boolean h;
	private boolean i;
	private String cityJ;
	
	public InterviewFileFeeder(String nameA, String surnameB, String mailC, String genderD, String profileImageE,
			String paymentMethodF, String transactionsG, boolean h, boolean i, String cityJ) {
		super();
		this.nameA = nameA;
		this.surnameB = surnameB;
		this.mailC = mailC;
		this.genderD = genderD;
		this.profileImageE = profileImageE;
		this.paymentMethodF = paymentMethodF;
		this.transactionsG = transactionsG;
		this.h = h;
		this.i = i;
		this.cityJ = cityJ;
	}

	public String getNameA() {
		return nameA;
	}

	public void setNameA(String nameA) {
		this.nameA = nameA;
	}

	public String getSurnameB() {
		return surnameB;
	}

	public void setSurnameB(String surnameB) {
		this.surnameB = surnameB;
	}

	public String getMailC() {
		return mailC;
	}

	public void setMailC(String mailC) {
		this.mailC = mailC;
	}

	public String getGenderD() {
		return genderD;
	}

	public void setGenderD(String genderD) {
		this.genderD = genderD;
	}

	public String getProfileImageE() {
		return profileImageE;
	}

	public void setProfileImageE(String profileImageE) {
		this.profileImageE = profileImageE;
	}

	public String getPaymentMethodF() {
		return paymentMethodF;
	}

	public void setPaymentMethodF(String paymentMethodF) {
		this.paymentMethodF = paymentMethodF;
	}

	public String getTransactionsG() {
		return transactionsG;
	}

	public void setTransactionsG(String transactionsG) {
		this.transactionsG = transactionsG;
	}

	public boolean isH() {
		return h;
	}

	public void setH(boolean h) {
		this.h = h;
	}

	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}

	public String getCityJ() {
		return cityJ;
	}

	public void setCityJ(String cityJ) {
		this.cityJ = cityJ;
	}

	@Override
	public String toString() {
		return "InterviewFileFeeder [nameA=" + nameA + ", surnameB=" + surnameB + ", mailC=" + mailC + ", genderD="
				+ genderD + ", profileImageE=" + profileImageE + ", paymentMethodF=" + paymentMethodF
				+ ", transactionsG=" + transactionsG + ", h=" + h + ", i=" + i + ", cityJ=" + cityJ + "]";
	}


	

}
