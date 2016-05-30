package org.transport.main.app;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Scanner;

import org.transport.main.GeneralPerson;
import org.transport.main.ModeOfTransport;
import org.transport.main.MonthlyPass;
import org.transport.main.Pass;
import org.transport.main.PassType;
import org.transport.main.Person;
import org.transport.main.PersonOrganization;
import org.transport.main.PersonType;
import org.transport.main.PrepaidPass;
import org.transport.main.Ride;
import org.transport.main.TransportationWorker;
import org.transport.main.service.GeneralPersonService;
import org.transport.main.service.PrepaidPassService;
import org.transport.main.service.RideService;

public class TransportationApp {

	public static void main(String[] arg) {
		Scanner s = new Scanner(System.in);
		PersonOrganization po = new PersonOrganization();
		RideService rideService = new RideService();
		PrepaidPassService prepaidPassService = new PrepaidPassService();
		GeneralPersonService generalPersonService = new GeneralPersonService();
		while (true) {
			Ride ride = new Ride();

			System.out.println("Please enter your id");
			long id = 0;
			boolean flag = false;
			while (!flag) {
				try {
					id = s.nextLong();//
					flag = true;
				} catch (Exception e) {
					System.out.println("Please enter a valid id");
					s.next();
				}
			}
			flag = false;
			Person person = po.getPerson(id);
			if (person == null) {
				System.out.println("Please enter a valid id");
				continue;
			}
			PersonType userType = person.getPersonType();

			System.out.println("Please enter your mode of transport 1 commuter 2 bus 3 subway");
			int modeChoice = 0;

			while (!flag) {
				try {
					modeChoice = s.nextInt();//
					if (modeChoice >= 1 && modeChoice <= 3) {
						flag = true;
					} else {
						System.out.println("Please enter a valid choice");
					}
				} catch (Exception e) {
					System.out.println("Please enter a valid choice");
					s.next();
				}
			}
			flag = false;

			ModeOfTransport modeOfTransport = ride.getModeOfTransport(modeChoice);

			if (userType == PersonType.Worker) {
				ride.setPerson(person);
				ride.setPass(((TransportationWorker) (person)).gettWorkerPass());
				System.out.println("You're all set! Please enjoy your journey!");
				System.out.println("Your details: ");
				System.out.println(ride.getPerson().getPersonId());
				System.out.println(ride.getPass().getPassId());
				System.out.println(modeOfTransport);
				continue;
			}

			System.out.println("Please choose: 1. Buy pass 2. add value to pass 3. Use pass 4.Check balance");
			int useChoice = 0;
			while (!flag) {
				try {
					useChoice = s.nextInt();//
					if (useChoice >= 1 && useChoice <= 4) {
						flag = true;
					} else {
						System.out.println("Please enter a valid option");
					}
				} catch (Exception e) {
					System.out.println("Please enter a valid option");
					s.next();
				}
			}
			flag = false;
			if (useChoice == 1) {
				System.out.println("Please choose: 1. Prepaid Pass 2. Monthly pass");
				int passTypeChoice = 0;

				while (!flag) {
					try {
						passTypeChoice = s.nextInt();//
						if (passTypeChoice == 1 || passTypeChoice == 2) {
							flag = true;
						} else {
							System.out.println("Please enter a valid option");
						}
					} catch (Exception e) {
						System.out.println("Please enter a valid option");
						s.next();
					}
				}
				flag = false;

				Pass pass = rideService.generatePass(passTypeChoice, modeOfTransport, person);
				BigDecimal valueToBeAddedToCard = new BigDecimal(0);
				if (passTypeChoice == 1) {
					System.out.println("Please input value to be placed in the card");
					while (!flag) {
						try {
							valueToBeAddedToCard = s.nextBigDecimal();//
							if (valueToBeAddedToCard.compareTo(new BigDecimal(0)) > 0) {
								flag = true;
							} else {
								System.out.println("Please enter a valid amount");
							}
						} catch (Exception e) {
							System.out.println("Please enter a valid amount");
							s.next();
						}
					}
					flag = false;
				}
				BigDecimal price = rideService.calculateFare(pass, valueToBeAddedToCard, modeOfTransport, person);
				System.out.println("Please pay " + price);
				if (pass != null) {
					System.out.println("your generated pass id: " + pass.getPassId());
					if (passTypeChoice == 1) {
						System.out.println(PassType.PREPAID);
						System.out.println(((PrepaidPass) pass).getModeOfTransport());
						System.out.println(((PrepaidPass) pass).getBalance());
					} else if (passTypeChoice == 2) {
						System.out.println(PassType.MONTHLY);
						System.out.println(((MonthlyPass) pass).getModeOfTransport());
						// System.out.println("Valid until " + new
						// SimpleDateFormat("dd:MM:yy:HH:mm:ss")
						// .format(((MonthlyPass) pass).getValidUntil()));
						Calendar calendar = Calendar.getInstance();
						long validUntilInMillis = ((MonthlyPass) pass).getValidUntil();
						calendar.setTimeInMillis(validUntilInMillis);
						System.out.println("Valid until: " + calendar.get(Calendar.DAY_OF_MONTH) + "/"
								+ calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + " TIME::"
								+ calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
					}
				}
				System.out.println("Thank you for using our services!");
				continue;
			}
			if (useChoice == 2) {
				System.out.println("Select the pass to add value to");//

				long cardId = 0;
				while (!flag) {
					try {
						cardId = s.nextLong();
						flag = true;
					} catch (Exception e) {
						System.out.println("Please enter a valid pass");
						s.next();
					}
				}
				flag = false;

				Pass pass = generalPersonService.getValidPrepaidCard(cardId, ((GeneralPerson) person));

				if (pass == null) {
					System.out.println("Sorry! Invalid Pass Id");
					continue;
				}
				System.out.println("Enter the amount to be added");
				BigDecimal value = s.nextBigDecimal();
				((PrepaidPass) pass).setBalance((((PrepaidPass) pass).getBalance()).add(value));
				System.out.println("Your new balance");
				System.out.println(((PrepaidPass) pass).getBalance());
				continue;
			}
			if (useChoice == 3) {
				System.out.println("Please choose the pass you want to use");//
				long passId = 0;
				while (!flag) {
					try {
						passId = s.nextLong();//
						flag = true;
					} catch (Exception e) {
						System.out.println("Please enter a valid pass");
						s.next();
					}
				}
				flag = false;

				Pass pass = generalPersonService.usePass(passId, ride, modeOfTransport, (GeneralPerson) person);
				if (pass != null) {
					ride.setModeOfTransport(modeOfTransport);
					ride.setPass(pass);
					ride.setPerson(person);
					if (pass instanceof MonthlyPass) {
						System.out.println("You're all set. Please enjoy your journey!");
					} else if (pass instanceof PrepaidPass) {
						BigDecimal fare = rideService.calculateFare(pass, modeOfTransport, person);
						boolean isSufficientBalance = prepaidPassService.checkBalance(fare, ((PrepaidPass) pass));
						if (isSufficientBalance) {
							System.out.println("Total charge " + fare);
							System.out.println("Remaining balance " + ((PrepaidPass) pass).getBalance());
							System.out.println("Please enjoy your journey");
						} else {
							System.out.println("Insufficient Balance");
							continue;
						}

					}
				} else {
					System.out.println("Invalid pass Id");
					continue;
				}
			}
			if (useChoice == 4) {
				System.out.println("Select the pass to check balance");//

				long cardId = 0;
				while (!flag) {
					try {
						cardId = s.nextLong();
						flag = true;
					} catch (Exception e) {
						System.out.println("Please enter a valid pass");
						s.next();
					}
				}
				flag = false;

				Pass pass = generalPersonService.getValidPrepaidCard(cardId, ((GeneralPerson) person));

				if (pass == null) {
					System.out.println("Sorry! Invalid Pass Id");
					continue;
				}
				System.out.println("Your balance");
				System.out.println(((PrepaidPass) pass).getBalance());
				continue;
			}
		}
	}
}
