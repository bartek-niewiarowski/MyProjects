#include <array>
#include <exception>
#include "CppUnitTest.h"
#include "../BoardGamesLib/tile.h"


using namespace Microsoft::VisualStudio::CppUnitTestFramework;

template<>
inline std::wstring Microsoft::VisualStudio::CppUnitTestFramework::ToString<Tile>(const Tile& t)
{
	std::wstring result;
	result += L"{";
	result += ToString(t);
	result += L"}";
	return result;
}


namespace uiUnitTest
{
	TEST_CLASS(uiUnitTest)
	{
	public:
		
		TEST_METHOD(placeholder)
		{
			Assert::IsTrue(true == true);
		}
		

	};
}
