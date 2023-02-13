cmake_minimum_required(VERSION 3.17.0)
project(BoardGames VERSION 1.0)


include(FetchContent)
set(SFML_STATIC_LIBRARIES TRUE)
set(BUILD_SHARED_LIBS OFF)


FetchContent_Declare(
        sfml
        GIT_REPOSITORY "https://github.com/SFML/SFML.git"
        GIT_TAG        "2.5.1"
)


if(NOT sfml_POPULATED)
    FetchContent_Populate(sfml)
    add_subdirectory(${sfml_SOURCE_DIR} ${sfml_BINARY_DIR})
endif()

FetchContent_MakeAvailable(sfml)


set_target_properties(sfml-graphics PROPERTIES CXX_STANDARD 17 CXX_STANDARD_REQUIRED YES CXX_EXTENSIONS NO)

#==========================================
#   BoardGamesLib
#==========================================

file(GLOB_RECURSE BoardGamesLib_SRC "BoardGamesLib/*.h" "BoardGamesLib/*.cpp")
#file(GLOB_RECURSE BoardGamesLib_SRC "tile.h" "tile.cpp")

add_library(BoardGamesLib ${BoardGamesLib_SRC})

set_property(TARGET BoardGamesLib PROPERTY CXX_STANDARD 17)

#==========================================
#   BoardGamesApp
#==========================================

file(GLOB_RECURSE BoardGamesApp_SRC "BoardGamesApp/*.h" "BoardGamesApp/*.cpp")

add_executable(BoardGamesApp ${BoardGamesApp_SRC})

target_link_libraries(BoardGamesApp BoardGamesLib)

set_property(TARGET BoardGamesApp PROPERTY CXX_STANDARD 17)

#==========================================
#   BoardGamesUnitTest
#==========================================

file(GLOB_RECURSE BoardGamesUnitTest_SRC "BoardGamesUnitTest/*.h" "BoardGamesUnitTest/*.cpp")

add_library(BoardGamesUnitTest SHARED ${BoardGamesUnitTest_SRC})

target_link_libraries(BoardGamesUnitTest BoardGamesLib)

set_property(TARGET BoardGamesUnitTest PROPERTY CXX_STANDARD 17)

target_link_libraries(BoardGamesApp sfml-graphics sfml-system sfml-window) 
target_link_libraries(BoardGamesLib sfml-graphics sfml-system sfml-window) 



