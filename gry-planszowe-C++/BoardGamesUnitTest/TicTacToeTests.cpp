#include "CppUnitTest.h"
#include "..\BoardGamesLib\TicTacToe.h"

// -------------------------------------------------- Ewa Miazga ----------------------------------------------------------

using namespace Microsoft::VisualStudio::CppUnitTestFramework;
using namespace std;

namespace TicTacToeTests
{
	TEST_CLASS(TicTacToeTests)
	{
	public:

		TEST_METHOD(createTicTacToeBoard)
		{
			TicTacToe board;
			Assert::IsTrue(board.isEmpty(0, 0));
		}

		TEST_METHOD(Insert)
		{
			TicTacToe board;
			board.insert(0, 1, 'X');
			Assert::IsTrue(board.isEmpty(0, 0));
			Assert::IsFalse(board.isEmpty(0, 1));
		}

		TEST_METHOD(getWinnerX)
		{
			TicTacToe board;
			board.insert(0, 1, 'X');
			board.insert(0, 2, 'X');
			board.insert(0, 0, 'X');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'X');
			board.insert(1, 2, 'O');
			board.insert(2, 1, 'O');
			board.insert(2, 2, 'X');
			char symbol = 'X';
			Assert::IsFalse(board.isEmpty(0, 0));
			Assert::IsFalse(board.isEmpty(0, 1));
			Assert::AreEqual(board.getWinner(), symbol);
		}

		TEST_METHOD(getWinnerXNotFullBoard)
		{
			TicTacToe board;
			board.insert(0, 1, 'X');
			board.insert(0, 2, 'X');
			board.insert(0, 0, 'X');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'X');
			board.insert(1, 2, 'O');
			board.insert(2, 1, 'O');
			char symbol = 'X';
			Assert::IsFalse(board.isEmpty(0, 0));
			Assert::IsFalse(board.isEmpty(0, 1));
			Assert::AreEqual(board.getWinner(), symbol);
		}

		TEST_METHOD(getWinnerO)
		{
			TicTacToe board;
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'X');
			board.insert(0, 0, 'X');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'X');
			board.insert(1, 2, 'O');
			board.insert(2, 1, 'O');
			board.insert(2, 2, 'O');
			char symbol = 'O';
			Assert::IsFalse(board.isEmpty(0, 0));
			Assert::IsFalse(board.isEmpty(0, 1));
			Assert::AreEqual(board.getWinner(), symbol);
		}

		TEST_METHOD(getWinnerTie)
		{
			TicTacToe board;
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'X');
			board.insert(0, 0, 'X');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'X');
			board.insert(1, 2, 'O');
			board.insert(2, 1, 'X');
			board.insert(2, 2, 'O');
			char symbol = ' ';
			Assert::IsFalse(board.isEmpty(0, 0));
			Assert::IsFalse(board.isEmpty(0, 1));
			Assert::AreEqual(board.getWinner(), symbol);
		}

		TEST_METHOD(getWinnerTie2Winners)
		{
			TicTacToe board;
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'O');
			board.insert(0, 0, 'O');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'X');
			board.insert(1, 2, 'X');
			board.insert(2, 1, 'X');
			board.insert(2, 2, 'O');
			char symbol = ' ';
			Assert::IsFalse(board.isEmpty(0, 0));
			Assert::IsFalse(board.isEmpty(0, 1));
			Assert::AreEqual(board.getWinner(), symbol);
		}

		TEST_METHOD(isfullBoardTrue)
		{
			TicTacToe board;
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'X');
			board.insert(0, 0, 'X');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'X');
			board.insert(1, 2, 'O');
			board.insert(2, 1, 'X');
			board.insert(2, 2, 'O');
			Assert::IsTrue(board.isFullBoard());
		}

		TEST_METHOD(isfullBoardFalse)
		{
			TicTacToe board;
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'X');
			board.insert(0, 0, 'X');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'X');
			board.insert(2, 1, 'X');
			board.insert(2, 2, 'O');
			Assert::IsFalse(board.isFullBoard());
		}

		TEST_METHOD(gameOverNotFullBoard)
		{
			TicTacToe board;
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'X');
			board.insert(2, 1, 'X');
			board.insert(2, 2, 'O');
			Assert::IsFalse(board.gameOver());
		}

		TEST_METHOD(gameOverWinnerO)
		{
			TicTacToe board;
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'X');
			board.insert(0, 0, 'X');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'X');
			board.insert(1, 2, 'O');
			board.insert(2, 1, 'O');
			board.insert(2, 2, 'O');
			char symbol = 'O';
			Assert::IsTrue(board.gameOver());
		}

		TEST_METHOD(gameOverTie)
		{
			TicTacToe board;
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'O');
			board.insert(0, 0, 'O');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'X');
			board.insert(1, 2, 'X');
			board.insert(2, 1, 'X');
			board.insert(2, 2, 'O');
			char symbol = ' ';
			Assert::IsTrue(board.gameOver());
		}

		TEST_METHOD(checkWillWinRow)
		{
			TicTacToe board;
			board.insert(0, 1, 'X');
			board.insert(0, 2, 'X');
			board.insert(1, 0, 'X');
			pair<int, int> dim = std::make_pair(0, 0);
			Assert::AreEqual(board.checkWillWin('X').first, dim.first);
			Assert::AreEqual(board.checkWillWin('X').second, dim.second);
		}

		TEST_METHOD(checkWillWinRow2)
		{
			TicTacToe board;
			board.insert(2, 0, 'X');
			board.insert(2, 2, 'X');
			board.insert(1, 0, 'O');
			pair<int, int> dim = std::make_pair(2, 1);
			Assert::AreEqual(board.checkWillWin('X').first, dim.first);
			Assert::AreEqual(board.checkWillWin('X').second, dim.second);
		}

		TEST_METHOD(checkWillWinSq)
		{
			TicTacToe board;
			board.insert(0, 0, 'O');
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'X');
			board.insert(1, 2, 'X');
			board.insert(1, 0, 'X');
			board.insert(2, 0, 'O');
			board.insert(2, 1, 'X');
			board.insert(2, 2, 'O');
			pair<int, int> dim = std::make_pair(1, 1);
			Assert::AreEqual(board.checkWillWin('O').first, dim.first);
			Assert::AreEqual(board.checkWillWin('O').second, dim.second);
		}

		TEST_METHOD(checkWillWinCol2)
		{
			TicTacToe board;
			board.insert(1, 1, 'O');
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'X');
			board.insert(2, 2, 'X');
			pair<int, int> dim = std::make_pair(2, 1);
			Assert::AreEqual(board.checkWillWin('O').first, dim.first);
			Assert::AreEqual(board.checkWillWin('O').second, dim.second);
		}

		TEST_METHOD(checkWillWinCol)
		{
			TicTacToe board;
			board.insert(0, 0, 'O');
			board.insert(0, 1, 'O');
			board.insert(0, 2, 'X');
			board.insert(2, 0, 'O');
			pair<int, int> dim = std::make_pair(1, 0);
			Assert::AreEqual(board.checkWillWin('O').first, dim.first);
			Assert::AreEqual(board.checkWillWin('O').second, dim.second);
		}

		TEST_METHOD(checkWillWinSq2)
		{
			TicTacToe board;
			board.insert(2, 0, 'O');
			board.insert(1, 1, 'O');
			board.insert(0, 1, 'X');
			pair<int, int> dim = std::make_pair(0, 2);
			Assert::AreEqual(board.checkWillWin('O').first, dim.first);
			Assert::AreEqual(board.checkWillWin('O').second, dim.second);
		}

		TEST_METHOD(chooseSecondMoveifInColIsOpponent)
		{
			TicTacToe board;
			board.insert(0, 0, 'X');
			board.insert(2, 0, 'O');
			pair<int, int> dim = std::make_pair(1, 1);
			Assert::AreEqual(board.chooseSecondMove('X').first, dim.first);
			Assert::AreEqual(board.chooseSecondMove('X').second, dim.second);
		}

		TEST_METHOD(chooseSecondMoveifInRowIsOpponent)
		{
			TicTacToe board;
			board.insert(0, 1, 'X');
			board.insert(1, 2, 'O');
			pair<int, int> dim = std::make_pair(0, 0);
			Assert::AreEqual(board.chooseSecondMove('X').first, dim.first);
			Assert::AreEqual(board.chooseSecondMove('X').second, dim.second);
		}

		TEST_METHOD(chooseSecondMoveifInRowAndColIsOpponent)
		{
			TicTacToe board;
			board.insert(0, 2, 'X');
			board.insert(0, 1, 'O');
			board.insert(2, 2, 'O');
			pair<int, int> dim = std::make_pair(1, 1);
			Assert::AreEqual(board.chooseSecondMove('X').first, dim.first);
			Assert::AreEqual(board.chooseSecondMove('X').second, dim.second);
		}

		TEST_METHOD(chooseSecondMoveifInRowAndSqIsOpponent)
		{
			TicTacToe board;
			board.insert(0, 0, 'X');
			board.insert(1, 1, 'O');
			board.insert(0, 2, 'O');
			pair<int, int> dim = std::make_pair(1, 0);
			Assert::AreEqual(board.chooseSecondMove('X').first, dim.first);
			Assert::AreEqual(board.chooseSecondMove('X').second, dim.second);
		}


		TEST_METHOD(chooseFirstMoveIsEmptyThirdRow)
		{
			TicTacToe board;
			board.insert(0, 0, 'X');
			board.insert(1, 2, 'X');
			board.insert(1, 1, 'O');
			board.insert(0, 2, 'O');
			board.insert(1, 0, 'O');
			pair<int, int> dim = std::make_pair(2, 2);
			Assert::AreEqual(board.emptyLineFullOpponentSq('X').first, dim.first);
			Assert::AreEqual(board.emptyLineFullOpponentSq('X').second, dim.second);
		}

		TEST_METHOD(chooseFirstMoveIsEmptyThirdCol)
		{
			TicTacToe board;
			board.insert(0, 0, 'X');
			board.insert(1, 1, 'O');
			board.insert(1, 0, 'O');
			board.insert(2, 1, 'X');
			pair<int, int> dim = std::make_pair(2, 2);
			Assert::AreEqual(board.emptyLineFullOpponentSq('X').first, dim.first);
			Assert::AreEqual(board.emptyLineFullOpponentSq('X').second, dim.second);
		}

		TEST_METHOD(chooseFirstMoveIsEmptySecondCol)
		{
			TicTacToe board;
			board.insert(0, 0, 'X');
			board.insert(1, 2, 'O');
			board.insert(1, 0, 'O');
			board.insert(2, 2, 'X');
			pair<int, int> dim = std::make_pair(0, 1);
			Assert::AreEqual(board.emptyLineFullOpponentSq('X').first, dim.first);
			Assert::AreEqual(board.emptyLineFullOpponentSq('X').second, dim.second);
		}

		TEST_METHOD(chooseFirstMoveIsEmptySq)
		{
			TicTacToe board;
			board.insert(0, 0, 'X');
			board.insert(1, 2, 'O');
			board.insert(1, 0, 'O');
			board.insert(2, 2, 'X');
			board.insert(0, 1, 'O');
			pair<int, int> dim = std::make_pair(2, 0);
			Assert::AreEqual(board.emptyLineFullOpponentSq('X').first, dim.first);
			Assert::AreEqual(board.emptyLineFullOpponentSq('X').second, dim.second);
		}

	};
}
