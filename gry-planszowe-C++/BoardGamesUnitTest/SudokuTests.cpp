#include "CppUnitTest.h"
#include "..\BoardGamesLib\Sudoku.h"

// -------------------------------------------------- Ewa Miazga ----------------------------------------------------------

using namespace Microsoft::VisualStudio::CppUnitTestFramework;
using namespace std;

namespace SudokuTests
{
	TEST_CLASS(SudokuTests)
	{
	public:
		//
		//		TEST_METHOD(createSudokuBoard)
		//		{
		//			Sudoku board("hard");
		//			int num = 0;
		//			Assert::IsTrue(board.isEmpty(num, num, board.getBoard()));
		//		}
		//
		TEST_METHOD(getBoard)
		{
			Sudoku board("hard");
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = 0;
			std::string tab1 = board.toString(board.getBoard());
			std::string tab2 = board.toString(arr);
			Assert::AreEqual(tab1, tab2);
		}

		TEST_METHOD(fillBoard)
		{
			Sudoku board("hard");
			int sudokuBoard[rows][columns] =
			{
			{ 0, 0, 6, 5, 0, 8, 4, 0, 0 },
			{ 5, 2, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 7, 8, 0, 0, 0, 0, 0, 1 },
			{ 0, 0, 4, 0, 1, 0, 0, 8, 0 },
			{ 9, 0, 0, 8, 2, 3, 0, 0, 5 },
			{ 0, 5, 0, 0, 9, 0, 6, 0, 0 },
			{ 1, 3, 0, 0, 0, 0, 2, 5, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 7, 4 },
			{ 0, 0, 5, 2, 0, 6, 3, 0, 0 }
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = sudokuBoard[i][j];
			board.fillBoard(arr);
			std::string tab1 = board.toString(board.getBoard());
			std::string tab2 = board.toString(arr);
			Assert::AreEqual(tab1, tab2);
		}


		TEST_METHOD(assignDifficultyLevelMedium)
		{
			Sudoku board("medium");
			board.assignDiffcultyLevel("medium");
			std::pair<int, int> diff = std::make_pair(hard + 1, medium);
			Assert::AreEqual(board.getDifficultyLevel().first, diff.first);
			Assert::AreEqual(board.getDifficultyLevel().second, diff.second);
		}

		TEST_METHOD(assignDifficultyLevelHard)
		{
			Sudoku board("hard");
			board.assignDiffcultyLevel("hard");
			std::pair<int, int> diff = std::make_pair(solvable, hard);
			Assert::AreEqual(board.getDifficultyLevel().first, diff.first);
			Assert::AreEqual(board.getDifficultyLevel().second, diff.second);
		}

		TEST_METHOD(assignDifficultyLevelEasy)
		{
			Sudoku board("easy");
			board.assignDiffcultyLevel("easy");
			std::pair<int, int> diff = std::make_pair(medium + 1, N * N);
			Assert::AreEqual(board.getDifficultyLevel().first, diff.first);
			Assert::AreEqual(board.getDifficultyLevel().second, diff.second);
		}

		TEST_METHOD(calcDifficultyLevel)
		{
			Sudoku board("easy");
			int sudokuBoard[rows][columns] =
			{
			{ 1, 2, 6, 5, 5, 8, 4, 2, 2 },
			{ 5, 2, 8, 7, 7, 1, 1, 2, 2 },
			{ 4, 7, 8, 8, 8, 8, 8, 8, 1 },
			{ 5, 6, 4, 4, 1, 3, 2, 8, 1 },
			{ 9, 8, 8, 8, 2, 3, 5, 6, 5 },
			{ 8, 5, 8, 5, 9, 8, 6, 5, 5 },
			{ 1, 3, 8, 3, 4, 3, 2, 5, 5 },
			{ 8, 8, 8, 3, 4, 4, 5, 7, 4 },
			{ 8, 8, 5, 2, 4, 6, 3, 5, 0 }
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = sudokuBoard[i][j];
			board.fillBoard(arr);
			int num = 80;
			Assert::AreEqual(board.calcDifficultyLevel(board.getBoard()), num);
		}

		TEST_METHOD(createEmptyBoard)
		{
			Sudoku board("easy");
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = 0;
			board.createEmptyBoard(board.getBoard());
			std::string tab1 = board.toString(board.getBoard());
			std::string tab2 = board.toString(arr);
			Assert::AreEqual(tab1, tab2);
		}

		TEST_METHOD(usedInRowTrue)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			Assert::IsTrue(board.usedInRow(1, 4, board.getBoard()));
		}

		TEST_METHOD(usedInRowFalse)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			Assert::IsFalse(board.usedInRow(0, 4, board.getBoard()));
		}

		TEST_METHOD(usedInColTrue)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			Assert::IsTrue(board.usedInColumn(0, 4, board.getBoard()));
		}

		TEST_METHOD(usedInColFalse)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			Assert::IsFalse(board.usedInColumn(0, 3, board.getBoard()));
		}


		TEST_METHOD(usedInBoxTrue)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			Assert::IsTrue(board.usedInBox(0, 0, 4, board.getBoard()));
		}

		TEST_METHOD(usedInBoxFalse)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			Assert::IsFalse(board.usedInBox(0, 0, 5, board.getBoard()));
		}

		TEST_METHOD(checkRepetitonTrue)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			Assert::IsTrue(board.usedInBox(0, 1, 4, board.getBoard()));
		}

		TEST_METHOD(checkRepetitionFalse)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			Assert::IsFalse(board.usedInBox(0, 1, 5, board.getBoard()));
		}

		TEST_METHOD(isEmptyTrue)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			int c1 = 0;
			int c2 = 1;
			Assert::IsTrue(board.isEmpty(c1, c2, board.getBoard()));
		}

		TEST_METHOD(isEmptyFalse)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			int c1 = 0;
			int c2 = 0;
			Assert::IsFalse(board.isEmpty(c1, c2, board.getBoard()));
		}

		TEST_METHOD(insert)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 0, 0, 0, 0, 0, 0, 0, 0,
			4, 0, 6, 7, 8, 0, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			0, 6, 5, 0, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 0, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			int c1 = 0;
			int c2 = 1;
			board.insert(c1, c2, 1);
			Assert::AreEqual(board.getBoard()[c1][c2], 1);
		}

		TEST_METHOD(isSolvedTrue)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 2, 4, 5, 5, 3, 8, 9, 9,
			4, 2, 6, 7, 8, 4, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			3, 6, 5, 8, 9, 7, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 1, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			int a = 0;
			int b = 0;
			Assert::IsTrue(board.isSolved(board.getBoard(), a, b));
		}


		TEST_METHOD(isSolvedFalse)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 2, 4, 5, 5, 3, 8, 9, 9,
			4, 2, 6, 7, 8, 4, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			3, 6, 5, 0, 9, 0, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 1, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			int a = 0;
			int b = 0;
			Assert::IsFalse(board.isSolved(board.getBoard(), a, b));
		}

		TEST_METHOD(generateSolvedBoard)
		{
			Sudoku board("easy");
			int a = 0;
			int b = 0;
			board.generateSolvedBoard();
			Assert::IsTrue(board.isSolved(board.getBoard(), a, b));
		}

		TEST_METHOD(generateStartBoard)
		{
			Sudoku board("easy");
			int a = 0;
			int b = 0;
			board.generateSolvedBoard();
			board.generateStartBoard();
			Assert::IsFalse(board.isSolved(board.getBoard(), a, b));
		}

		TEST_METHOD(solveTrue)
		{
			Sudoku board("hard");
			int sudokuBoard[rows][columns] =
			{
			2, 0, 3,  0, 0, 6,  0, 0, 0,
			4, 0, 6,  7, 8, 9,  1, 2, 3,
			7, 8, 9,  1, 2, 3,  4, 5, 6,

			3, 2, 1,  5, 4, 7,  6, 9, 8,
			5, 4, 7,  6, 9, 8,  2, 3, 1,
			6, 9, 8,  2, 3, 1,  5, 4, 7,

			8, 6, 2,  3, 1, 4,  9, 7, 5,
			1, 3, 4,  9, 7, 5,  8, 6, 2,
			9, 7, 5,  8, 6, 2,  3, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = sudokuBoard[i][j];
			board.fillBoard(arr);
			Assert::IsTrue(board.solve(0, 0, board.getBoard()));
		}

		TEST_METHOD(solveFalse)
		{
			Sudoku board("easy");
			int s[rows][columns] = {
			1, 2, 4, 5, 0, 3, 8, 9, 9,
			4, 2, 6, 7, 8, 4, 1, 2, 3,
			7, 8, 9, 1, 2, 3, 4, 5, 6,
			2, 1, 4, 3, 6, 5, 8, 9, 7,
			3, 6, 5, 3, 9, 2, 2, 4, 1,
			8, 9, 7, 2, 1, 4, 3, 6, 5,
			5, 1, 1, 6, 4, 2, 9, 7, 8,
			6, 4, 8, 9, 7, 1, 5, 3, 2,
			9, 7, 2, 5, 3, 8, 6, 1, 4
			};
			int** arr;
			arr = new int* [9];
			for (int i = 0; i < 9; i++)
				arr[i] = new int[9];
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					arr[i][j] = s[i][j];
			board.fillBoard(arr);
			Assert::IsFalse(board.solve(0, 0, board.getBoard()));
		}
	};
}