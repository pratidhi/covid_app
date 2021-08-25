package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import model.Assessment;
import model.User;

public class main {
	static List<User> USER_LIST= new ArrayList<>();
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String args[]) throws IOException
	{
		
		String choice ="";
		while(true)
		{
			displayMenu();
			choice = in.readLine();
			switch (choice) {
			case "1":
				registerUser(false);
				
				break;
			case "2":
				selfAssessment();
				break;
				
			case "3":
				registerUser(true);
				break;
			case "4":
				updateUserCovidStatus();
				break;
			case "5":
				viewUserProfile();
				break;	
			case "6":
				viewZoneRisk();
				break;	
			default:
				break;
			}
		}
	}

	private static void viewZoneRisk() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter Pin code");
		int pincode = Integer.parseInt(in.readLine());
		int count = 0;
		for(User u : USER_LIST)
		{
			if (u.getPincode() == pincode && u.getCovidStatus().equalsIgnoreCase("Positive"))
			{
				count++;
			}
			
		}
		String colour = "GREEN";
		if(count>0 && count <= 5)
			colour = "ORANGE";
		else if(count > 5)
			colour = "RED";
		
		System.out.println("{\"numCases\":\""+count+"\",\"zoneType\":\""+colour+"\"}");
	}

	private static void viewUserProfile() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter UserID");
		int id = Integer.parseInt(in.readLine());
		User user = null;
		
		for (int i = 0; i < USER_LIST.size(); i++) 
		{
			if(USER_LIST.get(i).getUserid() == id)
			{
				user = USER_LIST.get(i);
			}
		}
		if (null == user)
		{
			System.out.println("No User Found with User ID "+ id);
			return;
		}
		System.out.println(user.toString());
		
	}

	private static void updateUserCovidStatus() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter UserID");
		int id = Integer.parseInt(in.readLine());
		int index = 0;
		User user = null;
		
		for (int i = 0; i < USER_LIST.size(); i++) 
		{
			if(USER_LIST.get(i).getUserid() == id)
			{
				user = USER_LIST.get(i);
				index = i;
			}
		}
		if(null == user)
		{
			System.out.println("{\"updated\":false}");
			return;
		}
		String input ="";
		System.out.println("Has Patient recovered (Y/N)");
		input = in.readLine().toUpperCase();
		if(input.charAt(0) == 'Y')
			user.setCovidStatus("Negative");
		else
			user.setCovidStatus("Positive");
		System.out.println("{\"updated\":true}");
	}

	private static void selfAssessment() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter UserID");
		int id = Integer.parseInt(in.readLine());
		int index = 0;
		User user = null;
		
		for (int i = 0; i < USER_LIST.size(); i++) 
		{
			if(USER_LIST.get(i).getUserid() == id)
			{
				user = USER_LIST.get(i);
				index = i;
			}
		}
		if(null == user)
		{
			System.out.println("No User Found with ID" + id);
			return;
		}
		Assessment as = new Assessment();
		ArrayList<String> symptom = new ArrayList<String>();
		String input = "";
		System.out.println("Do you have cough? (Y/N) ");
		input = in.readLine().toUpperCase();
		if(input.charAt(0) == 'Y')
		symptom.add("cough");
		
		System.out.println("Do you have cold? (Y/N) ");
		input = in.readLine().toUpperCase();
		if(input.charAt(0) == 'Y')
		symptom.add("cold");
		
		System.out.println("Do you have fever? (Y/N) ");
		input = in.readLine().toUpperCase();
		if(input.charAt(0) == 'Y')
		symptom.add("fever");
		
		as.setSymptoms(symptom);
		
		System.out.println("Do you have any travel history? (Y/N) ");
		input = in.readLine().toUpperCase();
		if(input.charAt(0) == 'Y')
		as.setTravelHistory(true);
		
		else
			as.setTravelHistory(false);
		
		System.out.println("Have you been in contact with Covid positive patient? (Y/N) ");
		input = in.readLine().toUpperCase();
		if(input.charAt(0) == 'Y')
		as.setContactWithCovidPatient(true);
		
		else
			as.setContactWithCovidPatient(false);
		
		user.setAssessment(as);
		
		int riskPercent = 0;
		if(user.getAssessment().getSymptoms().isEmpty() && !user.getAssessment().isTravelHistory() && !user.getAssessment().isContactWithCovidPatient())
		riskPercent = 5;
		
		if(user.getAssessment().getSymptoms().size() == 1  && (user.getAssessment().isTravelHistory() || user.getAssessment().isContactWithCovidPatient()))
		riskPercent = 50;
		
		if(user.getAssessment().getSymptoms().size() == 2  && (user.getAssessment().isTravelHistory() || user.getAssessment().isContactWithCovidPatient()))
		riskPercent = 75;
		
		if(user.getAssessment().getSymptoms().size() > 2  && (user.getAssessment().isTravelHistory() || user.getAssessment().isContactWithCovidPatient()))
		riskPercent = 95;
		
		user.setRiskPercent(riskPercent);
		
		USER_LIST.remove(index);
		USER_LIST.add(index, user);
		
		System.out.println("{\"riskPercentage\": \""+riskPercent+"\"}");
		
	}

	private static void registerUser(boolean isAdmin) throws IOException {
		// TODO Auto-generated method stub
		User u = new User();
		System.out.println("Enter name");
		u.setName(in.readLine());
		System.out.println("Enter mobile number");
		u.setMobnum(Long.parseLong(in.readLine()));
		System.out.println("Enter Pincode");
		u.setPincode(Integer.parseInt(in.readLine()));
		if(isAdmin)
			u.setUserType("ADMIN");
		else
		u.setUserType("USER");
		int uid = USER_LIST.size()+1;
		u.setUserid(uid);
		u.setCovidStatus("Negative");
		USER_LIST.add(u);
		if(isAdmin)
			System.out.println("{\"adminId\": \""+u.getUserid()+"\"}");
		else
			System.out.println("{\"userId\": \""+u.getUserid()+"\"}");
		
	}

	private static void displayMenu() {
		// TODO Auto-generated method stub
		System.out.println("1. Register User \n 2. User Assessment \n 3. Register Admin \n 4. Update User Covid Status \n 5. View User Profile \n 6. View Zone Risk Status");
		System.out.println("Enter Choice ");
	}

}
