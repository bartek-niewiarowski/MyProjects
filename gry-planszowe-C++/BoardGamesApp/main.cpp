#include <iostream>
#include <vector>
#include <fstream>
//#include "../BoardGamesLib/App.h"
#include <SFML/Graphics.hpp>
#include <SFML/System.hpp>

const float width = 900;
const float height = 900;
const std::string path;

int main()
{
	sf::RenderWindow window(sf::VideoMode(900, 900), "Sudoku");
	sf::Font font;
	if (!font.loadFromFile("../resources/fonts/Qarolina.ttf"))
	{
		std::cout << "error loading the file";
		system("pause");
	}

	//App game(font);
	//game.RunStartMenu(window);
	return 0;


	//database users;

	//read_from_file(path, users);
	
	/*sf::RenderWindow window(sf::VideoMode(900, 900), "Sudoku");
	std::vector<std::string> start_info = { "BOARD GAMES!!!" };
	std::vector<std::string> start_opt = { "Quick Game", "Load User", "Exit" };
	DecisionMenu start(width, height, start_info, start_opt);

	std::vector<std::string> load_info = { "Enter user's name" };
	std::vector<std::string> load_opt = { "Confirm", "Exit" };
	LoginWindow load(width, height, load_info, load_opt);

	std::vector<std::string> game_info = { "What do you want to do?", "User: " };
	std::vector<std::string> game_opt = { "Sudoku", "Tic Tac Toe", "Crosswords", "Stats", "The best users", "Exit" };
	DecisionMenu game(width, height, game_info, game_opt);

	std::vector<std::string> sudoku_info = { "Sudoku" };
	Menu sudoku(width, height, sudoku_info);

	std::vector<std::string> ttc_info = { "Tic Tac Toe" };
	Menu ttc(width, height, ttc_info);

	std::vector<std::string> crosswords_info = { "Sudoku" };
	Menu cr(width, height, crosswords_info);

	std::vector<std::string> stats_info = { "Stats" };
	Menu statistics(width, height, stats_info);

	std::vector<Menu> windows = { start, load, game, sudoku, ttc, cr, statistics };

	std::string name = "User: user_1";
	int result = 0;

	database users;
	stats Sudoku("first");
	auto* current_user = new user;
	auto* account = new user_account;

	result = start.RunMenu(window);
	while (window.isOpen())
	{
		if (result == -2)
		{
			result = start.RunMenu(window);
			auto* current_user = new user;
		}
			
		switch (result)
		{
		case 0:
		{
			game.set_info(1, name);
			result = game.RunMenu(window);
			switch (result)
			{
			case 0:
				result = sudoku.RunMenu(window);
				break;
			case 1:
				result = ttc.RunMenu(window);
				break;
			case 2:
				result = cr.RunMenu(window);
				break;
			case 3:
				statistics.reupload_info((*current_user).show_my_stats());
				result = statistics.RunMenu(window);
				break;
			case 4:
				if ((*current_user).is_premium())
					continue;
				else
					statistics.reupload_info({ "You do not have access\nto ranking of the best players" });
				result = statistics.RunMenu(window);
				break;
			case 5:
				window.close();
				break;
			case -1:
				result = -2;
				delete current_user;
				name = "User: user_1";
				break;
			}
			if (result == -1)
			{
				result = 0;
				break;
			}
			break;
		}
		case 1:
		{
			delete current_user;
			user_account* current_user = new user_account;
			result = load.RunMenu(window);
			switch (result)
			{
			case 0:
			{
				name = "User: ";
				name += load.getInPut();
				*current_user = users.find_user(load.getInPut());
				std::cout << "we've got it" << std::endl;
				std::cout << (*current_user).update_to_premium() << std::endl;
				if ((*current_user).update_to_premium())
				{
					std::cout << "we've got it" << std::endl;
					premium_user* current_premium_user = new premium_user;
					*current_premium_user = (*current_premium_user).update_to_premium(*current_user);
					delete current_user;
					premium_user* current_user = new premium_user;
					current_user = current_premium_user;
					delete current_premium_user;
				}
				std::cout << "we've got it" << std::endl;
				result = 0;
				break;
			}
			case 1:
				delete current_user;
				window.close();
				break;
			case -1:
				delete current_user;
				result = -2;
				break;
			}
		}

		case 2:
			window.close();

		case -1:
			delete current_user;
			result = -2;
			name = "User: user_1";
		}
	}
	//write_to_file(path, users);
	//return 0;*/
}
