#include "App.h"

const float width = 900;
const float height = 900;
const std::string path;

App::App(sf::Font& font) : font(font)
{
	std::vector<std::string> start_info = { "BOARD GAMES!!!" };
	std::vector<std::string> start_opt = { "Quick Game", "Load User", "Exit" };
	start_window = DecisionMenu(width, height, start_info, start_opt);

	std::vector<std::string> load_info = { "Enter user's name" };
	std::vector<std::string> load_opt = { "Confirm", "Exit" };
	load_window = LoginWindow(width, height, load_info, load_opt);

	std::vector<std::string> game_info = { "What do you want to do?", "User: " };
	std::vector<std::string> game_opt = { "Sudoku", "Tic Tac Toe", "Crosswords", "Stats", "The best users", "Exit" };
	game_window = DecisionMenu(width, height, game_info, game_opt);

	std::vector<std::string> sudoku_info = { "Sudoku" };
	sudoku_window = Menu(width, height, sudoku_info);

	std::vector<std::string> ttc_info = { "Tic Tac Toe" };
	ttc_window = Menu(width, height, ttc_info);

	std::vector<std::string> crosswords_info = { "Crosswords" };
	crosswords_window = Menu(width, height, crosswords_info);

	std::vector<std::string> stats_info = { "Stats" };
	Menu statistics(width, height, stats_info);

	result = 0;
}

App::~App()
{

}

void App::RunStartMenu(sf::RenderWindow& window)
{
	result = load_window.RunMenu(window);

	switch (result)
	{
	case 0:
		QuickGame(window);
	case 1:
		LoadUser(window);
	case 2:
		window.close();
		return;
	}
}

void App::QuickGame(sf::RenderWindow& window)
{
	user* current_user = new user;
	game_window.set_info(1, "User: new_user");
	result = game_window.RunMenu(window);
	switch (result)
	{
	case 0:
		PlaySudoku(window, *current_user);
		break;
	case 1:
		PlayTTC(window, *current_user);
		break;
	case 2:
		PlayTTC(window, *current_user);
		break;
	case 3:
		stats_window.reupload_info((*current_user).show_my_stats());
		result = stats_window.RunMenu(window);
		break;
	case 4:
		stats_window.reupload_info({ "You do not have access\nto ranking of the best players" });
		result = stats_window.RunMenu(window);
		break;
	case 5:
		window.close();
		break;
	case -1:
		delete current_user;
		RunStartMenu(window);
	}
}

void App::LoadUser(sf::RenderWindow& window)
{
	std::string* name = new std::string;
	user_account* current_user = new user_account;
	result = load_window.RunMenu(window);
	switch (result)
	{
	case 0:
	{
		*name = "User: ";
		*name += load_window.getInPut();
		*current_user = users.find_user(load_window.getInPut());
		/*if ((*current_user).update_to_premium())
		{
			std::cout << "we've got it" << std::endl;
			premium_user* current_premium_user = new premium_user;
			*current_premium_user = (*current_premium_user).update_to_premium(*current_user);
			delete current_user;
			premium_user* current_user = new premium_user;
			current_user = current_premium_user;
			delete current_premium_user;
		}*/
		UserGame(window, *current_user);
	}
	case 1:
		delete current_user;
		window.close();
		return;
	case -1:
		delete current_user;
		RunStartMenu(window);
	}
}

void App::UserGame(sf::RenderWindow& window, user_account current_user)
{
	switch (result)
	{
	case 0:
		PlaySudoku(window, current_user);
		break;
	case 1:
		PlayTTC(window, current_user);
		break;
	case 2:
		PlayTTC(window, current_user);
		break;
	case 3:
		stats_window.reupload_info((current_user).show_my_stats());
		result = stats_window.RunMenu(window);
		break;
	case 4:
		stats_window.reupload_info({ "You do not have access\nto ranking of the best players" });
		result = stats_window.RunMenu(window);
		break;
	case 5:
		window.close();
		break;
	case -1:
		RunStartMenu(window);
	}
}

void App::PlaySudoku(sf::RenderWindow & window, user current_user)
{
	int input;
	Sudoku level("hard");
	Board gboard(sf::Vector2f(0, 0),
		sf::Color::Black,
		sf::Color(155, 155, 155, 100),
		sf::Color::Black,
		font,
		900,
		9,
		window);
	while (true)
	{
		result = move(window, current_user);
		while (result != 1)
		{
			result = move(window, current_user);
		}
		input = get_sudoku_input(window);
		level.insert(current_user.get_x(), current_user.get_y(), input);
		gboard.display(level);
	}
}

void App::PlayTTC(sf::RenderWindow& window, user current_user)
{

}

void App::PlayCrosswords(sf::RenderWindow& window, user current_user)
{

}

int App::get_sudoku_input(sf::RenderWindow& window)
{
	sf::Event event;
	while (window.isOpen())
	{
		while (window.pollEvent(event))
		{
			switch (event.type)
			{
			case sf::Event::KeyReleased:
				switch (event.key.code)
				{
				case sf::Keyboard::Num1:
					return 1;
				case sf::Keyboard::Num2:
					return 2;
				case sf::Keyboard::Num3:
					return 3;
				case sf::Keyboard::Num4:
					return 4;
				case sf::Keyboard::Num5:
					return 5;
				case sf::Keyboard::Num6:
					return 6;
				case sf::Keyboard::Num7:
					return 7;
				case sf::Keyboard::Num8:
					return 8;
				case sf::Keyboard::Num9:
					return 9;
				case sf::Keyboard::Escape:
					return -1;
				}
				break;
			case sf::Event::Closed:
				window.close();
			}
		}
	}
}

std::string App::get_crosswords_input(sf::RenderWindow& window)
{
	sf::String input;
	while (window.isOpen())
	{
		sf::Event event;

		while (window.pollEvent(event))
		{
			switch (event.type)
			{
			case sf::Event::TextEntered:
			{
				input += event.text.unicode;
			}
			case sf::Event::KeyReleased:
				switch (event.key.code)
				{
				case sf::Keyboard::Return:
					return input;
				case sf::Keyboard::Escape:
					return "";
					break;

					break;
				}
			case sf::Event::Closed:
				window.close();
				break;
			}
		}
	}
}

int App::move(sf::RenderWindow& window, user& current_user)
{
	sf::Event event;
	while (window.isOpen())
	{
		while (window.pollEvent(event))
		{
			switch (event.type)
			{
			case sf::Event::KeyReleased:
				switch (event.key.code)
				{
				case sf::Keyboard::Up:
					current_user.move(0, -1);
					return 0;
				case sf::Keyboard::Down:
					current_user.move(0, 1);
					return 0;
				case sf::Keyboard::Left:
					current_user.move(-1, 0);
					return 0;
				case sf::Keyboard::Right:
					current_user.move(1, 0);
					return 0;
				case sf::Keyboard::Return:
					return 1;
				case sf::Keyboard::Escape:
					return -1;
				}
				break;
			case sf::Event::Closed:
				window.close();
				return 2;
			}
		}
	}
}