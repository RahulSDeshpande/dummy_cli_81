package in.imates.smartset;


import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class PremiumPlan {
	static int AccidentAmount;
	static double AfterDiscount;
	static int Age;
	static int Age1;
	static double AgeDiff;
	static String AgentCode;
	static double Amount;
	static String AnnuityOption;
	static double BeforeDiscount;
	static Date Birthdate;
	static double Bonus;
	static int BonusTerm;
	static String BranchCode;
	static String CIR;
	static double CIRSA;
	static String CalculateReturn;
	static int Child1Age;
	static double Child1Hcb;
	static double Child1Premium;
	static int Child2Age;
	static double Child2Hcb;
	static double Child2Premium;
	static int Child3Age;
	static double Child3Hcb;
	static double Child3Premium;
	static int Child4Age;
	static double Child4Hcb;
	static double Child4Premium;
	static double ChildAge;
	static double CirPwb;
	static double CirValue;
	static String Class1;
	static String ClubMembership;
	static double CollegeCost;
	static double CollegeDuration;
	static double CollegeYear;
	static String Config;
	static String ContactNo;
	static double DAB;
	static String DABValue;
	static String DabAvailbale;
	static String DeviceId;
	static String Division;
	static String DoCode;
	static double EducationDuration;
	static String EmailId;
	static String EmployeeCode;
	static int EndTerm;
	static double ExisitingSA;
	static double ExpectedInflationRate;
	static double ExpectedReturnRate;
	static double FAB;
	static double Fourthyear;
	static String FundName;
	static double GrowthRate;
	static String IMEI;
	static double ITLimit;
	static double ITSlab;
	static double InterestRate;
	static String InternationalMembership;
	static String IsServiceTax;
	static double Loyalty;
	static double Maturity;
	static int MaturityAge;
	static String MedicalRequirement;
	static String Message;
	static int Mode;
	static String ModelNo;
	static double MonthlyPremium;
	static String Name;
	static double NetDiscount;
	static int OptionSelected;
	static int PIAge;
	static double PIHcb;
	static double PIPremium;
	static double PPT;
	static int PPT1;
	static String PanNo;
	static int Parent1Age;
	static double Parent1Hcb;
	static double Parent1Premium;
	static int Parent2Age;
	static double Parent2Hcb;
	static double Parent2Premium;
	static int ParentInLaw1Age;
	static double ParentInLaw1Hcb;
	static double ParentInLaw1Premium;
	static int ParentInLaw2Age;
	static double ParentInLaw2Hcb;
	static double ParentInLaw2Premium;
	static int Plan1;
	static int Plan2;
	static int Plan3;
	static int Plan4;
	static int PlanNo;
	static double Premium103;
	static double Premium149;
	static double Premium168;
	static double Premium175;
	static double Premium178;
	static double Premium179;
	static double Premium184;
	static double Premium185;
	static double Premium75;
	static double Premium815;
	static double Premium816;
	static double Premium820;
	static double PremiumAmount;
	static int PremiumMode;
	static int ProposerAge;
	static String Pwb;
	static double Rate;
	static double RateOfInterest;
	static double Revival;
	static double SA103;
	static double SA149;
	static double SA164;
	static double SA168;
	static double SA175;
	static double SA178;
	static double SA179;
	static double SA184;
	static double SA185;
	static double SA190;
	static double SA5;
	static double SA75;
	static double SA814;
	static double SA815;
	static double SA816;
	static double SA820;
	static double SA822;
	static double SA823;
	static int SdkVersion;
	static String SenderID;
	static String SinglePremium;
	static int SpouseAge;
	static Date SpouseBirthDate;
	static double SpouseHcb;
	static double SpousePremium;
	static int StartAge;
	static int StartTerm;
	static String TR;
	static double TRSA;
	static int Term;
	static int Term103;
	static int Term149;
	static double Term164;
	static int Term168;
	static int Term175;
	static int Term178;
	static int Term179;
	static double Term184;
	static double Term190;
	static double Term5;
	static int Term75;
	static double Term814;
	static double Term815;
	static int Term816;
	static int Term820;
	static double Term822;
	static double Term823;
	static double TermRider;
	static double TotalHalfyearlyPremium;
	static double TotalMaturity;
	static double TotalMonthlyPremium;
	static double TotalQuarterlyPremium;
	static double TotalSSSPremium;
	static double TotalYealyPremium;
	static String UniqueUserKey;
	static String UserName;
	static String Verificationkey;
	static double YearlyPremium;
	static double f2301a;
	static double eighteenyear;
	static double eightyear;
	static double fifteenyear;
	static double fifthyear;
	static int gsv;
	static double halfyearlypremium;
	private static List<Double> lstTerm;
	private static List<Integer> lstTerm1;
	static double nineteenyear;
	static double ninthyear;
	static String osVersion;
	static double ppt184;
	static double quarterlypremium;
	static double sa822;
	static double seventeenyear;
	static double sixteenyear;
	static double sixthyear;
	static int sp;
	static double ssspremium;
	static double tenthyear;
	static double thirdyear;
	static double twelveyear;
	static double twentyfiveyear;
	static double twentyoneyear;
	static double twentyyear;
	static double yearlypremium;

	public static Hashtable<Integer, Plan> readsettingFile(Context context) {
		IOException e;
		Hashtable<Integer, Plan> htsetting = null;
		try {
			InputStream input = context.getAssets().open("setting.txt");
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			input.close();
			String[] line = new String(buffer).split("\n");
			Hashtable<Integer, Plan> htsetting2 = new Hashtable();
			int i = 0;
			while (i < line.length) {
				String[] a = line[i].split(",");
				Hashtable<Integer, Plan> hashtable = htsetting2;
				hashtable.put(Integer.valueOf(Integer.parseInt(a[0])), new Plan(Integer.parseInt(a[0]), a[1], (int) Double.parseDouble(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]), a[5], Double.parseDouble(a[6]), (int) Double.parseDouble(a[7]), (int) Double.parseDouble(a[8])));
				i++;
			}
			htsetting = htsetting2;
			return htsetting2;
		} catch (IOException e3) {
			e = e3;
			e.printStackTrace();
			return htsetting;
		}
	}

	public static Hashtable<Integer, Plan> readPlanInfo(Context context) {
		IOException e;
		Exception e2;
		Hashtable<Integer, Plan> hashtable = null;
		try {
			InputStream input = context.getAssets().open("planconfig.txt");
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			input.close();
			String[] line = new String(buffer).split("\n");
			Hashtable<Integer, Plan> htsetting = new Hashtable();
			int i = 0;
			while (i < line.length) {
				try {
					String[] a = line[i].split(",");
					Hashtable<Integer, Plan> hashtable2 = htsetting;
					hashtable2.put(Integer.valueOf(Integer.parseInt(a[0])), new Plan(Integer.parseInt(a[0]), a[1], Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(a[6]), Integer.parseInt(a[7]), Integer.parseInt(a[8]), a[9], a[10], a[11], a[12], a[13], a[14], a[15], a[16], Double.parseDouble(a[17]), Double.parseDouble(a[18]), Double.parseDouble(a[19]), Double.parseDouble(a[20]), Double.parseDouble(a[21]), Double.parseDouble(a[22]), Double.parseDouble(a[23]), Double.parseDouble(a[24]), Double.parseDouble(a[25]), Integer.parseInt(a[26]), Integer.parseInt(a[27]), Integer.parseInt(a[28]), Double.parseDouble(a[29]), a[30], a[31], a[32], a[33], a[34], a[35], a[36], a[37], a[38], a[39], a[40], a[41], a[42], a[43], a[44]));
					Log.i("value", a[0] + "," + a[1] + "," + a[2] + "," + a[3] + "," + a[4] + "," + a[5] + "," + a[6] + "," + a[7] + "," + a[8] + "," + a[9] + "," + a[10] + "," + a[11] + "," + a[12] + "," + a[13] + "," + a[14] + "," + a[15] + "," + a[16] + "," + a[17] + "," + a[18] + "," + a[19] + "," + a[20] + "," + a[21] + "," + a[22] + "," + a[23] + "," + a[24] + "," + a[25] + "," + a[26] + "," + a[27] + "," + a[28] + "," + a[29] + "," + a[30] + "," + a[31] + "," + a[32] + "," + a[33] + "," + a[34] + "," + a[35] + "," + a[36] + "," + a[37] + "," + a[38] + "," + a[39] + "," + a[40] + "," + a[41] + "," + a[42] + "," + a[43] + "," + a[44]);
					i++;
				} catch (Exception e4) {
					e2 = e4;
					hashtable = htsetting;
				}
			}
			hashtable = htsetting;
			return htsetting;
		} catch (IOException e5) {
			e = e5;
			e.printStackTrace();
			return hashtable;
		} catch (Exception e6) {
			e2 = e6;
			e2.printStackTrace();
			return hashtable;
		}
	}

	public static List<Double> getlstTerm() {
		return lstTerm;
	}

	public static Hashtable<Integer, Hashtable<Double, Double>> readFromFile(String FileName, Context context) {
		IOException e;
		Hashtable<Integer, Hashtable<Double, Double>> htAge = null;
		try {
			lstTerm = new ArrayList();
			InputStream input = context.getAssets().open(FileName);
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			input.close();
			String[] line = new String(buffer).split("\n");
			Hashtable<Integer, Hashtable<Double, Double>> htAge2 = new Hashtable();
			for (int i = 0; i < line.length; i++) {
				String[] a = line[i].split(",");
				Hashtable<Double, Double> htTermRate = new Hashtable();
				for (int j = 1; j < a.length; j++) {
					Log.i("TEST1", "#" + a[j] + "#");
					if (i == 0) {
						lstTerm.add(Double.valueOf(Double.parseDouble(a[j])));
						readsettingFile(context);
					} else {
						try {
							htTermRate.put((Double) lstTerm.get(j - 1), Double.valueOf(a[j]));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
				if (i > 0) {
					htAge2.put(Integer.valueOf(a[0]), htTermRate);
				}
			}
			htAge = htAge2;
			return htAge2;
		} catch (IOException e3) {
			e = e3;
			e.printStackTrace();
			return htAge;
		}
	}

	public static Hashtable<String, String> GetPlanNo(Context context) {
		IOException e;
		Exception e2;
		Hashtable<String, String> hashtable = null;
		try {
			InputStream input = context.getAssets().open("plan.txt");
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			input.close();
			String[] line = new String(buffer).split("\n");
			Hashtable<String, String> htsetting = new Hashtable();
			int j = 0;
			while (j < line.length) {
				try {
					String[] a = line[j].split(",");
					htsetting.put(a[0], a[1]);
					j++;
				} catch (Exception e4) {
					e2 = e4;
					hashtable = htsetting;
				}
			}
			hashtable = htsetting;
			return htsetting;
		} catch (IOException e5) {
			e = e5;
			e.printStackTrace();
			return hashtable;
		} catch (Exception e6) {
			e2 = e6;
			e2.printStackTrace();
			return hashtable;
		}
	}

	public static List<Integer> getlstTerm1() {
		return lstTerm1;
	}

	public static Hashtable<Integer, Hashtable<Integer, Double>> readCir(String FileName, Context context) {
		IOException e;
		Exception e2;
		Hashtable<Integer, Hashtable<Integer, Double>> hashtable = null;
		try {
			lstTerm1 = new ArrayList();
			InputStream input = context.getAssets().open(FileName);
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			input.close();
			String[] line = new String(buffer).split("\n");
			Hashtable<Integer, Hashtable<Integer, Double>> htAge = new Hashtable();
			int i = 0;
			while (i < line.length) {
				try {
					String[] a = line[i].split(",");
					Hashtable<Integer, Double> htTermRate = new Hashtable();
					for (int j = 1; j < a.length; j++) {
						if (i == 0) {
							lstTerm1.add(Integer.valueOf((int) Double.parseDouble(a[j])));
						} else {
							try {
								htTermRate.put((Integer) lstTerm1.get(j - 1), Double.valueOf(a[j]));
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
					if (i > 0) {
						htAge.put(Integer.valueOf(a[0]), htTermRate);
					}
					i++;
				} catch (Exception e4) {
					e2 = e4;
					hashtable = htAge;
				}
			}
			hashtable = htAge;
			return htAge;
		} catch (IOException e5) {
			e = e5;
			e.printStackTrace();
			return hashtable;
		} catch (Exception e6) {
			e2 = e6;
			e2.printStackTrace();
			return hashtable;
		}
	}

	public static Hashtable<Double, Hashtable<Integer, Double>> readSvFactor(String FileName, Context context) {
		IOException e;
		Exception e2;
		Hashtable<Double, Hashtable<Integer, Double>> hashtable = null;
		try {
			lstTerm1 = new ArrayList();
			InputStream input = context.getAssets().open(FileName);
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			input.close();
			String[] line = new String(buffer).split("\n");
			Hashtable<Double, Hashtable<Integer, Double>> htAge = new Hashtable();
			int i = 0;
			while (i < line.length) {
				try {
					String[] a = line[i].split(",");
					Hashtable<Integer, Double> htTermRate = new Hashtable();
					for (int j = 1; j < a.length; j++) {
						if (i == 0) {
							lstTerm1.add(Integer.valueOf((int) Double.parseDouble(a[j])));
						} else {
							try {
								htTermRate.put((Integer) lstTerm1.get(j - 1), Double.valueOf(a[j]));
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
					if (i > 0) {
						htAge.put(Double.valueOf(a[0]), htTermRate);
					}
					i++;
				} catch (Exception e4) {
					e2 = e4;
					hashtable = htAge;
				}
			}
			hashtable = htAge;
			return htAge;
		} catch (IOException e5) {
			e = e5;
			e.printStackTrace();
			return hashtable;
		} catch (Exception e6) {
			e2 = e6;
			e2.printStackTrace();
			return hashtable;
		}
	}

	public static Hashtable<Integer, Hashtable<Double, Double>> GetBonusRate(String FileName, Context context) {
		IOException e;
		Hashtable<Integer, Hashtable<Double, Double>> htAge = null;
		try {
			lstTerm = new ArrayList();
			InputStream input = context.getAssets().open(FileName);
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			input.close();
			String[] line = new String(buffer).split("\n");
			Hashtable<Integer, Hashtable<Double, Double>> htAge2 = new Hashtable();
			int i = 0;
			while (i < line.length) {
				String[] a = line[i].split(",");
				Hashtable<Double, Double> htTermRate = new Hashtable();
				for (int j = 1; j < a.length; j++) {
					if (i == 0) {
						lstTerm.add(Double.valueOf(Double.parseDouble(a[j])));
					} else {
						try {
							htTermRate.put((Double) lstTerm.get(j - 1), Double.valueOf(a[j]));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
				if (i > 0) {
					htAge2.put(Integer.valueOf(a[0]), htTermRate);
				}
				i++;
			}
			htAge = htAge2;
			return htAge2;
		} catch (IOException e3) {
			e = e3;
			e.printStackTrace();
			return htAge;
		}
	}

	public static Hashtable<Integer, Plan> readPlanFeatures(Context context) {
		IOException e;
		Exception e2;
		Hashtable<Integer, Plan> hashtable = null;
		try {
			InputStream input = context.getAssets().open("planfeatures.txt");
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			input.close();
			String[] line = new String(buffer).split("\n");
			Hashtable<Integer, Plan> htsetting = new Hashtable();
			int i = 0;
			while (i < line.length) {
				try {
					String[] a = line[i].split(",");
					htsetting.put(Integer.valueOf(Integer.parseInt(a[0])), new Plan(Integer.parseInt(a[0]), a[1], a[2], a[3], a[4], a[5], a[6], a[7]));
					Log.i("value", a[0] + "," + a[1] + "," + a[2] + "," + a[3] + "," + a[4] + "," + a[5] + "," + a[6] + "," + a[7]);
					i++;
				} catch (Exception e4) {
					e2 = e4;
					hashtable = htsetting;
				}
			}
			hashtable = htsetting;
			return htsetting;
		} catch (IOException e5) {
			e = e5;
			e.printStackTrace();
			return hashtable;
		} catch (Exception e6) {
			e2 = e6;
			e2.printStackTrace();
			return hashtable;
		}
	}
}