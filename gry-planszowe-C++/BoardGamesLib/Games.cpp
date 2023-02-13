//#include "Games.h"
//
//Games::Games(std::string gameValue)
//{
//	game = gameValue;
//	char** array;
//	if (game == "Crossword")
//	{
//		array = new char* [20];
//		for (int i = 0; i < 20; i++)
//			array[i] = new char[20];
//		rows = 20;
//		columns = 20;
//	}
//	else if (game == "Sudoku")
//	{
//		array = new char* [10];
//		for (int i = 0; i < 10; i++)
//			array[i] = new char[10];
//		rows = 10;
//		columns = 10;
//	}
//	else if (game == "TikTacToe")
//	{
//		array = new char* [3];
//		for (int i = 0; i < 3; i++)
//			array[i] = new char[3];
//		rows = 3;
//		columns = 3;
//	}
//	board = array;
//}
//
//void Games::calc_emptySquare()
//{
//	if (game == "Crossword")
//		emptySquare = '_';
//	else if (game == "Sudoku")
//		emptySquare = 0;
//	else if (game == "TikTacToe")
//		emptySquare = '_';
//}
//
//
//void Games::insert(int row = 0, int column = 0, char value = 0, int num = 0, std::string clueValue = "_")
//{
//	if (game == "Crossword")
//	{
//		crosswordCluesUser[num - 1].second.first = clueValue; //how to get access to attribute of inherit class
//	}
//	else if (game == "Sudoku")
//	{
//		if (num <= 9 && num >= 0)
//			board[row][column] = num;
//	}
//	else if (game == "TikTacToe")
//	{
//		if (isEmpty(row, column))
//			board[row][column] = value;
//	}
//}
//
//bool Games::isEmpty(int& num, int& row, int& column, int** tab)
//{
//	if (game == "Crossword")
//	{
//		if (crosswordCluesUser[num].second.first[0] == emptySquare)
//			return true;
//		return false; //how to get access to attribute of inherit class
//	}
//	else if (game == "Sudoku" || game == "TikTacToe")
//	{
//		if (board[row][column] == emptySquare)
//			return true;
//		return false;
//	}
//}
//
//void Games::display()
//{
//	return;
//}
//
//void Games::play()
//{
//	if (game == "Crossword")
//	{
//		{
//			while (!isSolved() && !checkCorrectness())
//			{
//				display();
//				int num;
//				std::cout << std::endl;
//				std::cout << "choose number: " << std::endl;
//				std::string clue;
//				std::cin >> num;
//				std::cout << "write clue: " << std::endl;
//				std::cin >> clue;
//				insert(num, clue);
//			}
//			display();
//			std::cout << std::endl;
//			std::cout << std::endl;
//			std::cout << "Crossword is solved" << std::endl;
//			std::cout << std::endl;
//			std::cout << "CLUE: " << crosswordClue.first << std::endl;
//			std::cout << "CLUE INFO: " << crosswordClue.second << std::endl;
//			std::cout << std::endl;
//		}
//	}
//	else if (game == "Sudoku")
//	{
//		int k = 0;
//		while (!(k >= difficultyLevel.first && k <= difficultyLevel.second))
//		{
//			generateStartBoard();
//			k = calcDifficultyLevel(board);
//		}
//		int i = 0;
//		int j = 0;
//		while (!isSolved(board, i, j))
//		{
//			display();
//			int row, col, num;
//			std::cout << "Choose the row" << std::endl;
//			std::cin >> row;
//			std::cout << "Choose the column" << std::endl;
//			std::cin >> col;
//			std::cout << "Enter the number" << std::endl;
//			std::cin >> num;
//			insert(row - 1, col - 1, num);
//		}
//	}
//
//	else if (game == "TicTacToe")
//	{
//		char playerCounter;
//		char AICounter;
//		std::cout << "Choose counter ( 'O' or 'X' )" << std::endl;
//		std::cin >> playerCounter;
//		if (playerCounter == 'X')
//			AICounter = 'O';
//		else
//			AICounter = 'X';
//		int move = 1;
//		while (!gameOver())
//		{
//			if (move % 2 == 1)
//			{
//				display();
//				int row, col;
//				std::cout << "Choose row" << std::endl;
//				std::cin >> row;
//				std::cout << "Choose column" << std::endl;
//				std::cin >> col;
//				insert(row - 1, col - 1, playerCounter);
//			}
//			else
//				moveAI(AICounter, playerCounter);
//			move++;
//		}
//	}
//}