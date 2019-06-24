package com.techelevator;

import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_REPORT = "Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_REPORT };
	
	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_OPTION_FEED_MONEY, SUB_MENU_OPTION_SELECT_PRODUCT,
			SUB_MENU_OPTION_FINISH_TRANSACTION };
	
	private static final String MONEY_MENU_ADD_DOLLAR = "Feed One Dollar";
	private static final String MONEY_MENU_ADD_FIVE_DOLLARS = "Feed Five Dollars";
	private static final String MONEY_MENU_ADD_TEN_DOLLARS = "Feed Ten Dollars";
	private static final String MONEY_MENU_EXIT = "Done adding money";
	private static final String[] MONEY_MENU_OPTIONS = { MONEY_MENU_ADD_DOLLAR, MONEY_MENU_ADD_FIVE_DOLLARS,
			MONEY_MENU_ADD_TEN_DOLLARS, MONEY_MENU_EXIT };

	private static VendingMachine vm = new VendingMachine();

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		boolean finishedMain = false;
		while (finishedMain == false) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				System.out.println(vm.displayInventory());
			}

			else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean finishedSub = false;
				while (finishedSub == false) {

					choice = (String) menu.getChoiceFromOptions(SUB_MENU_OPTIONS);

					if (choice.equals(SUB_MENU_OPTION_FEED_MONEY)) {
						boolean finishedMoney = false;

						while (finishedMoney == false) {

							choice = (String) menu.getChoiceFromOptions(MONEY_MENU_OPTIONS);
							String message = null;

							if (choice.equals(MONEY_MENU_ADD_DOLLAR)) {
								message = vm.feedMoney(1);
							} else if (choice.equals(MONEY_MENU_ADD_FIVE_DOLLARS)) {
								message = vm.feedMoney(5);
							} else if (choice.equals(MONEY_MENU_ADD_TEN_DOLLARS)) {
								message = vm.feedMoney(10);
							} else if (choice.equals(MONEY_MENU_EXIT)) {

								finishedMoney = true;
							}
							if (message != null) {
								System.out.println(message);
							}
						}
					}

					else if (choice.equals(SUB_MENU_OPTION_SELECT_PRODUCT)) {
						boolean productSelected = false;
						while (productSelected == false) {

							System.out.println(vm.displayInventory());
							System.out.println(
									"Please choose an item (A1-D4) or \nQ to quit and return to previous menu: ");
							@SuppressWarnings("resource")
							Scanner makeSelection = new Scanner(System.in);
							String selection = makeSelection.nextLine();
							String result = vm.purchase(selection);
							System.out.println(result);
							System.out.println("");

							if (result.contains("previous menu")) {
								productSelected = true;
							}

						}

					}

					else if (choice.equals(SUB_MENU_OPTION_FINISH_TRANSACTION)) {
						System.out.println(vm.completeTransaction());
						finishedSub = true;
					}
				}

			}

			else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {

				vm.closeAuditFile();
				finishedMain = true;
			}

			else {

				vm.produceReport();
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
