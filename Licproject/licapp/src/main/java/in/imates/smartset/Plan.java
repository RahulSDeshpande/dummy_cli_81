package in.imates.smartset;


public class Plan {
	String ADBR;
	double ASA;
	double Above100000SaRebate;
	int AccidentBenefit;
	double AccidentBenefitAmount;
	String Advance;
	String BasicClass1;
	String Bonus;
	String Cir;
	String CirAvailable;
	String CirPwbAvailable;
	String Commision;
	int EndAge;
	String Fab;
	String Fv;
	String Gadd;
	double HalfYearly;
	String Loan;
	String Loyalty;
	int MaturityAge;
	int MaxExactAge;
	int MaxPPT;
	String MaxSumAssured;
	int MaxTerm;
	int MinExactAge;
	int MinPPT;
	String MinSumAssured;
	int MinTerm;
	int Mode;
	String MoneyBack;
	double Monthly;
	String Multiply;
	String OnDeathAfterTheTermOfPolicy;
	String OnDeathWithinTheTerm;
	String OnSurvivalToMaturity;
	int PPT;
	String PWB;
	String PlanName;
	int PlanNo;
	String Premiums;
	String PwbClass1;
	double Quarterly;
	String SA;
	double SSS;
	String SSV;
	String Settlement;
	String SilentFeatures;
	int StartAge;
	String Suitability;
	double SumAssured;
	String TermClass1;
	int TermInput;
	String TermRange;
	String TermRider;
	String TermRiderAvailable;
	double Upto100000SaRebate;
	double Upto50000SaRebate;
	double Yearly;
	String pwbAvailable;

	public Plan(int planno, String planname, int terminput, int startage, int endage, String termrange, double sumassured, int accidentbenefit, int ppt) {
		this.PlanNo = planno;
		this.PlanName = planname;
		this.TermInput = terminput;
		this.StartAge = startage;
		this.EndAge = endage;
		this.TermRange = termrange;
		this.SumAssured = sumassured;
		this.AccidentBenefit = accidentbenefit;
		this.PPT = ppt;
	}

	public Plan(int planno, String planname, int startage, int endage, int minterm, int maxterm, int maturityage, String minsumassured, String maxsumassured, String multiply, String termrider, String cir, String loan, int accidentbenefitamount, int upto50000sarebate, double upto100000sarebate, int above100000sarebate, double yearly, double halfyearly, double quarterly, double monthly, double sss, int mode, int minexactage, int maxexactage) {
		this.PlanNo = planno;
		this.PlanName = planname;
		this.StartAge = startage;
		this.EndAge = endage;
		this.MaturityAge = maturityage;
		this.MinTerm = minterm;
		this.MaxTerm = maxterm;
		this.MinSumAssured = minsumassured;
		this.MaxSumAssured = maxsumassured;
		this.Multiply = multiply;
		this.TermRider = termrider;
		this.Cir = cir;
		this.Loan = loan;
		this.AccidentBenefitAmount = (double) accidentbenefitamount;
		this.Upto50000SaRebate = (double) upto50000sarebate;
		this.Upto100000SaRebate = upto100000sarebate;
		this.Above100000SaRebate = (double) above100000sarebate;
		this.Yearly = yearly;
		this.HalfYearly = halfyearly;
		this.Quarterly = quarterly;
		this.Monthly = monthly;
		this.SSS = sss;
		this.Mode = mode;
		this.MinExactAge = minexactage;
		this.MaxExactAge = maxexactage;
	}

	public Plan(int planno, String planname, int startage, int endage, int minppt, int maxppt, int minterm, int maxterm, int maturityage, String minsumassured, String maxsumassured, String multiply, String termrideravailable, String ciravailable, String cirpwbavailable, String pwbavailable, String loan, double accidentbenefitamount, double upto50000sarebate, double upto100000sarebate, double above100000sarebate, double yearly, double halfyearly, double quarterly, double monthly, double sss, int mode, int minexactage, int maxexactage, double asa, String advance, String settlement, String adbr, String sa, String bonus, String fab, String loyalty, String gadd, String ssv, String moneyback, String fv, String commision, String basicclass1, String Termclass1, String Pwbclass1) {
		this.PlanNo = planno;
		this.PlanName = planname;
		this.StartAge = startage;
		this.EndAge = endage;
		this.MaturityAge = maturityage;
		this.MinPPT = minppt;
		this.MaxPPT = maxppt;
		this.MinTerm = minterm;
		this.MaxTerm = maxterm;
		this.MinSumAssured = minsumassured;
		this.MaxSumAssured = maxsumassured;
		this.Multiply = multiply;
		this.TermRiderAvailable = termrideravailable;
		this.CirAvailable = ciravailable;
		this.CirPwbAvailable = cirpwbavailable;
		this.pwbAvailable = pwbavailable;
		this.Loan = loan;
		this.AccidentBenefitAmount = accidentbenefitamount;
		this.Upto50000SaRebate = upto50000sarebate;
		this.Upto100000SaRebate = upto100000sarebate;
		this.Above100000SaRebate = above100000sarebate;
		this.Yearly = yearly;
		this.HalfYearly = halfyearly;
		this.Quarterly = quarterly;
		this.Monthly = monthly;
		this.SSS = sss;
		this.Mode = mode;
		this.MinExactAge = minexactage;
		this.MaxExactAge = maxexactage;
		this.ASA = asa;
		this.Advance = advance;
		this.Settlement = settlement;
		this.ADBR = adbr;
		this.SA = sa;
		this.Bonus = bonus;
		this.Fab = fab;
		this.Loyalty = loyalty;
		this.Gadd = gadd;
		this.SSV = ssv;
		this.MoneyBack = moneyback;
		this.Fv = fv;
		this.Commision = commision;
		this.BasicClass1 = basicclass1;
		this.TermClass1 = Termclass1;
		this.PwbClass1 = Pwbclass1;
	}

	public Plan(int planno, String planname) {
		this.PlanName = planname;
		this.PlanNo = planno;
	}

	public Plan(int planNo, String planname, String suitability, String silentfeatures, String onsurvivaltomaturity, String ondeathwithintheterm, String ondeathafterthetermofpolicy, String premiums) {
		this.PlanNo = planNo;
		this.PlanName = planname;
		this.Suitability = suitability;
		this.SilentFeatures = silentfeatures;
		this.OnSurvivalToMaturity = onsurvivaltomaturity;
		this.OnDeathWithinTheTerm = ondeathwithintheterm;
		this.OnDeathAfterTheTermOfPolicy = ondeathafterthetermofpolicy;
		this.Premiums = premiums;
	}

	public String toString() {
		return this.PlanNo + "," + this.PlanName;
	}
}