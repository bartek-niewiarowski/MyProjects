#pragma once
#include <SFML/Graphics.hpp>
#include <SFML/System.hpp>

#define MAX_NUMBER_OF_ITEMS 3

class Menu
{
public:
	Menu();
	Menu(float width, float height, std::vector<std::string> info);
	~Menu();

	void set_info(int index, std::string info) { this->text[index].setString(info); }
	void reupload_info(std::vector<std::string> info);

	void draw(sf::RenderWindow& window);
	int RunMenu(sf::RenderWindow& window);

protected:
	sf::Font font;
	std::vector<sf::Text> text;
	void fill_info(std::vector<std::string> info, size_t sum);
	float width;
	float height;
};

class DecisionMenu
	:public Menu
{
public:
	DecisionMenu() {}
	DecisionMenu(float width, float height, std::vector<std::string> info, std::vector<std::string> options);
	~DecisionMenu();

	void MoveUp();
	void MoveDown();
	int GetPressedItem() { return selectedItemIndex; }
	void draw(sf::RenderWindow& window);
	int RunMenu(sf::RenderWindow& window);

protected:
	void fill_options(std::vector<std::string> options, size_t sum);
	int selectedItemIndex;
	std::vector<sf::Text> options;
};

class LoginWindow
	:public DecisionMenu
{
public:
	LoginWindow();
	LoginWindow(float width, float height, std::vector<std::string> info, std::vector<std::string> options);
	~LoginWindow();
	void draw(sf::RenderWindow& window);
	int RunMenu(sf::RenderWindow& window);
	sf::String getInPut() { return nameInPut; }

protected:
	sf::String nameInPut;
	sf::Text Name;
	sf::RectangleShape nameRect;
};
