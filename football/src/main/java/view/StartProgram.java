package view;

import java.util.List;
import java.util.Scanner;
import controller.footballPlayerHelper;
import model.footballPlayer;

public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static footballPlayerHelper fph = new footballPlayerHelper();

	private static void addPick() {
		System.out.print("Enter owner's name: ");
		String owner = in.nextLine();
		System.out.print("Enter athlete's name: ");
		String name = in.nextLine();
		System.out.print("Enter athlete's team: ");
		String team = in.nextLine();
		footballPlayer toAdd = new footballPlayer(owner, name, team);
		fph.insertTeam(toAdd);
	}

	private static void deletePick() {
		System.out.print("Enter the owner's name to delete: ");
		String owner = in.nextLine();
		System.out.print("Enter the athlete's name to delete: ");
		String name = in.nextLine();
		System.out.print("Enter the athlete's team to delete: ");
		String team = in.nextLine();
		footballPlayer toDelete = new footballPlayer(owner, name, team);
		fph.deleteTeam(toDelete);
	}

	private static void editPick() {
		System.out.println("Enter number of how would you like to search? ");
		System.out.println("1 : Search by athlete's Name");
		System.out.println("2 : Search by athlete's Team");
		System.out.println("3 : Search by owner's Name");
		int searchBy = in.nextInt();
		in.nextLine();
		List<footballPlayer> foundTeams;

		if (searchBy == 1) {
			System.out.print("Enter the athlete's name: ");
			String name = in.nextLine();
			foundTeams = fph.searchForTeamByName(name);
		} else if (searchBy == 2) {
			System.out.print("Enter the athlete's team: ");
			String teamName = in.nextLine();
			foundTeams = fph.searchForTeamByTeam(teamName);
		} else if (searchBy == 3) {
			System.out.print("Enter the owner's name: ");
			String ownerName = in.nextLine();
			foundTeams = fph.searchForTeamByOwner(ownerName);

			if (!foundTeams.isEmpty()) {
				System.out.println("Found Results.");
				for (footballPlayer l : foundTeams) {
					System.out.println("ID: " + l.getId() + " - " + l.returnTeamDetails());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();
				footballPlayer toEdit = fph.searchForTeamById(idToEdit);
				System.out.println("Retrieved " + toEdit.getName() + " from " + toEdit.getTeam() + " Owned by "
						+ toEdit.getOwner());
				System.out.println("1 : Update Name");
				System.out.println("2 : Update Team");
				System.out.println("3 : Update Owner");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Name: ");
					String newName = in.nextLine();
					toEdit.setName(newName);
				} else if (update == 2) {
					System.out.print("New Team: ");
					String newTeam = in.nextLine();
					toEdit.setTeam(newTeam);
				} else if (update == 3) {
					System.out.print("New Owner: ");
					String newOwner = in.nextLine();
					toEdit.setOwner(newOwner);
				}

				fph.updateTeam(toEdit);
			} else {
				System.out.println("---- No results found");
			}
		}
	}

	public static void searchPick() {
		System.out.print("Enter the owner's name: ");
		String ownerName = in.nextLine();
		List<footballPlayer> foundTeams;
		foundTeams = fph.searchForTeamByOwner(ownerName);

		System.out.println();

		for (footballPlayer l : foundTeams) {
			System.out.println(l.returnTeamDetails());
		}
		System.out.println();
	}

	public static void main(String[] args) {
		runMenu();
	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- loading football player tracker ---");

		while (goAgain) {
			System.out.println("*  What would you like to do? ");
			System.out.println("*  1 -- Add a pick");
			System.out.println("*  2 -- Edit a pick");
			System.out.println("*  3 -- Delete a pick");
			System.out.println("*  4 -- View the list of picks");
			System.out.println("*  5 -- View an owners picks");
			System.out.println("*  6 -- Quit");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addPick();
			} else if (selection == 2) {
				editPick();
			} else if (selection == 3) {
				deletePick();
			} else if (selection == 4) {
				viewTheList();
			} else if (selection == 5) {
				searchPick();
			} else {
				fph.cleanUp();
				System.out.println("   End of the line!   ");
				goAgain = false;
			}
		}
	}

	private static void viewTheList() {
		List<footballPlayer> allTeams = fph.showAllTeams();
		System.out.println();

		for (footballPlayer l : allTeams) {
			System.out.println(l.returnTeamDetails());
		}
		System.out.println();
	}
}
