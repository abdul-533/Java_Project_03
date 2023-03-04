package Project_3;

import java.util.ArrayList;
import java.util.Scanner;

public class Project_3_Main {
    public static void main(String[] args) {

        Scanner scanInt = new Scanner(System.in);
        Scanner sacnStr = new Scanner(System.in);

        ArrayList<Actions> actions = new ArrayList<>();
        Actions d1 = new Actions("Money_Deposit -----1");
        actions.add(d1);
        Actions d2 = new Actions("Money_Withdraw-----2");
        actions.add(d2);
        Actions d3 = new Actions("Transfer-----------3");
        actions.add(d3);
        Actions d4 = new Actions("Exit---------------4");
        actions.add(d4);

        // Define 2 accounts for each customer. Account class must include account number and amount of money in the account...
        ArrayList<Customer> customers = new ArrayList<>();
        Customer c1 = new Customer("User1", "password1");
        c1.account1 = new Account("1234", 200);
        c1.account2 = new Account("4321", 2000);
        customers.add(c1);
        Customer c2 = new Customer("User2", "password2");
        c2.account1 = new Account("5678", 500);
        c2.account2 = new Account("8765", 5000);
        customers.add(c2);

        Customer c3 = new Customer("User3", "password3");
        c3.account1 = new Account("9100", 700);
        c3.account2 = new Account("0019", 7000);
        customers.add(c3);

        // Define the bank customers as a list by creating objects from the Customer class... Belonging to a customer in the Customer class
        // Must have username, password and more than one account (Account)...Define these customers here accordingly...

        // The places that appear in RED in the code below are related to the classes you will create and the objects you will define above.
        // it will run normally...
        Customer activeUser;
        Account selectedAccount;

        while (true) {
            System.out.println("Please enter your username: ");
            String username = sacnStr.nextLine();
            System.out.println("Please enter your password: ");
            String password = sacnStr.nextLine();

            // -> You will use this method below to determine which user is logged in, create...
            activeUser = confirmUsernameAndPassword(customers, username, password);

            if (activeUser != null) {
                System.out.println("You have successfully logged in,congratulation");
                break;
            }
            System.out.println("There is no such user registered in the system. Try again");
        }

        // Actions starts here
        while (true) {
            System.out.println("Select the action you want to do...");
            for (int i = 0; i < actions.size(); i++) {
                System.out.println(actions.get(i).description);
            }
            int userChoice = scanInt.nextInt();

            switch (userChoice) {

                case 1: {
                    Account returned = null;
                    String account_No;

                    do {
                        System.out.println(activeUser.account1.getAccountNo());
                        System.out.println(activeUser.account2.getAccountNo());

                        System.out.println("Which account would you like to withdraw money from?");
                        account_No = sacnStr.nextLine();

                        returned = confirmAccountNumber(activeUser, account_No);


                    } while ((returned == null));

                    // Activate the deposit feature...
                    /*
                    List the accounts belonging to the user and ask which account number they want to deposit money to.
                    If the account number chosen by the user is incorrect, warn it and turn it back on again...
                    Selection confirmAccountNumber(currentCustomer, chosen); if validated by the method
                    Ask how much money you want to deposit...
                    And increase the amount of money in the account by taking the user's input...
                     */

                    System.out.println("You  have: " + returned.getAccountMoney() + " euros in your account");
                    System.out.println("How much money would you like to deposit?");
                    int money = scanInt.nextInt();
                    returned.moneyDepozit(money);
                    break;
                }

                case 2: {

                    while (true) {
                        // Here now only the bank accounts of that user are listed... Benefits of using CLASS...
                        System.out.println("Please enter the account number you want to withdraw money from...: ");
                        System.out.println(activeUser.account1.getAccountNo() + "\n" + activeUser.account2.getAccountNo());
                        String chose = sacnStr.next();

                        // You will use the confirmAccountNumber() method to confirm that the user has entered the correct account information.
                        selectedAccount = confirmAccountNumber(activeUser, chose);
                        if (selectedAccount == null) {
//                            System.out.println("You entered an incorrect account number...");
                            continue;
                        }
                        System.out.println("Please enter the amount of money you want to withdraw...: ");
                        int withdrawnMoney = scanInt.nextInt();
                        // Deduct the money from the user's balance if there is sufficient balance in the user's account using the withdraw() method,
                        // If there is not enough balance, you will use it to warn the user...Create
                        if (!withdraw(selectedAccount, withdrawnMoney)) {
                            break;
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.println("We are not able to perform this operation at the moment...");
                    break;
                }

                case 4:
                    System.exit(1);

                default: {
                    System.out.println("You did not make a successful choice...");
                }

            }
        }
    }

    // TODO - Define the return type and content of the following 3 methods, so that the above results as they should...

    public static Customer confirmUsernameAndPassword(ArrayList<Customer> users, String username, String password) {

        Customer returned = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username) && users.get(i).password.equals(password))
                returned = users.get(i);
        }
        return returned;


    }

    public static Account confirmAccountNumber(Customer currentUser, String chosenAccount) {

        Account returned = null;
        if (currentUser.account1.getAccountNo().equals(chosenAccount)) {
            returned = currentUser.account1;
        } else if (currentUser.account2.getAccountNo().equals(chosenAccount)) {
            returned = currentUser.account2;

        } else
            System.out.println("You entered an incorrect account number...");

        return returned;

    }


    public static boolean withdraw(Account chosenAccount, int amountToWithdraw) {

        boolean returned = true;
        int remainder = 0;
        if (amountToWithdraw <= chosenAccount.getAccountMoney()) {
            remainder = chosenAccount.getAccountMoney() - amountToWithdraw;
            chosenAccount.setAccountMoney(remainder);
            returned = false;
            System.out.println("You did  a successful choice and you have " + chosenAccount.getAccountMoney() + " euros in your account");
        } else
            System.out.println("You do not have sufficient funds in your account.");
        return returned;
    }

}
