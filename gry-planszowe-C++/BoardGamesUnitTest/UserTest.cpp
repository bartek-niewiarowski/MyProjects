#include "CppUnitTest.h"
#include "..\BoardGamesLib\User.h"
#include "..\BoardGamesLib\Stats.h"

// -------------------------------------------------- Bartlomiej Niewiarowski ----------------------------------------------------------

using namespace Microsoft::VisualStudio::CppUnitTestFramework;
using namespace std;

namespace UserTests
{
	TEST_CLASS(UserTests)
	{
	public:
		TEST_METHOD(TestInitUser)
		{
			user first;
			Assert::AreEqual(0, first.get_x());
			Assert::AreEqual(0, first.get_y());
			Assert::AreEqual(0, first.get_points());
		}
		// USER ACCOUNT
		TEST_METHOD(TestInitUserAccountDefault)
		{
			user_account first;
			std::string name = "user_1";
			time_t t = 0;
			Assert::AreEqual(0, first.get_x());
			Assert::AreEqual(0, first.get_y());
			Assert::AreEqual(0, first.get_points());
			Assert::AreEqual(name, first.get_name());
			Assert::AreEqual(t, first.get_current_sesion());
		}

		TEST_METHOD(TestInitUserAccount)
		{
			user_account first("Player2002");
			std::string name = "Player2002";
			time_t t = 0;
			Assert::AreEqual(0, first.get_x());
			Assert::AreEqual(0, first.get_y());
			Assert::AreEqual(0, first.get_points());
			Assert::AreEqual(name, first.get_name());
			Assert::AreEqual(t, first.get_current_sesion());
		}

		TEST_METHOD(TestInitUserAccountWithStats)
		{
			stats Sudoku("Sudoku", 200, 50), TTC("Tic Tac Toe", 1000, 20), Crosswords("Crosswords", 200, 30);
			time_t t = 200;
			std::string title = "Sudoku";
			user_account first("Player2002", Sudoku, TTC, Crosswords);

			Assert::AreEqual(t, first.get_sudoku_stats().get_time());
			Assert::AreEqual(title, first.get_sudoku_stats().get_title());
			Assert::AreEqual(50, first.get_sudoku_stats().get_points());

			t = 1000;
			title = "Tic Tac Toe";
			Assert::AreEqual(t, first.get_ttc_stats().get_time());
			Assert::AreEqual(title, first.get_ttc_stats().get_title());
			Assert::AreEqual(20, first.get_ttc_stats().get_points());

			t = 200;
			title = "Crosswords";
			Assert::AreEqual(t, first.get_crosswords_stats().get_time());
			Assert::AreEqual(title, first.get_crosswords_stats().get_title());
			Assert::AreEqual(30, first.get_crosswords_stats().get_points());
		}

		TEST_METHOD(TestUserAccountSetName)
		{
			user_account first;
			std::string name = "Bartek";
			first.set_name(name);
			Assert::AreEqual(name, first.get_name());
		}

		TEST_METHOD(TestUserSetSudokuStats)
		{
			user_account first;
			stats Sudoku("Sudoku", 2500, 400);

			first.set_sudoku_stats(Sudoku);

			time_t t = 2500;
			std::string title = "Sudoku";

			Assert::AreEqual(t, first.get_sudoku_stats().get_time());
			Assert::AreEqual(title, first.get_sudoku_stats().get_title());
			Assert::AreEqual(400, first.get_sudoku_stats().get_points());
		}

		TEST_METHOD(TestUserSetTTCStats)
		{
			user_account first;
			stats TTC("Tic Tac Toe", 2500, 400);

			first.set_ttc_stats(TTC);

			time_t t = 2500;
			std::string title = "Tic Tac Toe";

			Assert::AreEqual(t, first.get_ttc_stats().get_time());
			Assert::AreEqual(title, first.get_ttc_stats().get_title());
			Assert::AreEqual(400, first.get_ttc_stats().get_points());
		}

		TEST_METHOD(TestUserSetCrosswordsStats)
		{
			user_account first;
			stats Sudoku("Crosswords", 2500, 400);

			first.set_crosswords_stats(Sudoku);

			time_t t = 2500;
			std::string title = "Crosswords";

			Assert::AreEqual(t, first.get_crosswords_stats().get_time());
			Assert::AreEqual(title, first.get_crosswords_stats().get_title());
			Assert::AreEqual(400, first.get_crosswords_stats().get_points());
		}
	};
}