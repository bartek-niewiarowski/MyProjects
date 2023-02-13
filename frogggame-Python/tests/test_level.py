from frogger_game.frog import Frog
from frogger_game.objects import Log, Car, Checkpoint, Alligator, Turtle
from frogger_game.tracks import Road, River
from frogger_game.level import Level
import time
import pygame


def test_init_level():
    frog = Frog(5, 750, 400)
    car = Car(10, 200)
    log = Log(5, 200)
    ali = Alligator(5, 600)
    turtle = Turtle(5, 20)
    checkpoint = Checkpoint(100, 40)
    road_1 = Road(50, [car])
    road_2 = Road(100, [car])
    road_3 = Road(150, [car])
    river_1 = River(250, [ali], [log], [turtle])
    river_2 = River(300, [ali], [log], [turtle])
    river_3 = River(350, [ali], [log], [turtle])
    level = Level(frog, [road_1, road_2, road_3], [river_1, river_2, river_3], [checkpoint], 30)
    assert level.frog() == frog
    assert len(level.roads()) == 3
    assert len(level.rivers()) == 3
    assert len(level.checkpoints()) == 1
    assert level.time() == 30


def test_move_all_elements():
    frog = Frog(5, 400, 200)
    car = Car(10, 200)
    log = Log(5, 200)
    ali = Alligator(5, 600)
    turtle = Turtle(5, 20)
    checkpoint = Checkpoint(100, 40)
    road_1 = Road(50, [car])
    river_1 = River(250, [ali], [log], [turtle])
    level = Level(frog, [road_1], [river_1], [checkpoint], 30)
    level.move_all_elements((800, 450))
    assert car.x() == 210
    assert log.x() == 205


def test_scale_level():
    frog = Frog(5, 400, 200)
    car = Car(10, 200)
    log = Log(5, 200)
    ali = Alligator(5, 600)
    turtle = Turtle(5, 20)
    Frog.load_image(Frog)
    Car.load_image(Car)
    Log.load_image(Log)
    Alligator.load_image(Alligator)
    Turtle.load_image(Turtle)
    checkpoint = Checkpoint(100, 40)
    road_1 = Road(50, [car])
    river_1 = River(250, [ali], [log], [turtle])
    level = Level(frog, [road_1], [river_1], [checkpoint], 30)
    level.scale_level(2, 2)
    assert level.frog().x() == 800
    assert car.x() == 400
    assert car.speed() == 20
    assert checkpoint.x() == 200


def test_check_colision_with_cars_die():
    frog = Frog(5, 210, 50)
    car = Car(10, 200)
    log = Log(5, 200)
    ali = Alligator(5, 600)
    turtle = Turtle(5, 20)
    checkpoint = Checkpoint(100, 40)
    road_1 = Road(50, [car])
    road_2 = Road(100, [car])
    road_3 = Road(150, [car])
    river_1 = River(250, [ali], [log], [turtle])
    river_2 = River(300, [ali], [log], [turtle])
    river_3 = River(350, [ali], [log], [turtle])
    level = Level(frog, [road_1, road_2, road_3], [river_1, river_2, river_3], [checkpoint], 30)
    level.check_colisions_with_cars((800, 450))
    assert frog.lives() == 4
    assert frog.x() == 750
    assert frog.y() == 400


def test_check_colision_with_cars_false():
    frog = Frog(5, 400, 200)
    car = Car(10, 200)
    log = Log(5, 200)
    ali = Alligator(5, 600)
    turtle = Turtle(5, 20)
    checkpoint = Checkpoint(100, 40)
    road_1 = Road(50, [car])
    road_2 = Road(100, [car])
    road_3 = Road(150, [car])
    river_1 = River(250, [ali], [log], [turtle])
    river_2 = River(300, [ali], [log], [turtle])
    river_3 = River(350, [ali], [log], [turtle])
    level = Level(frog, [road_1, road_2, road_3], [river_1, river_2, river_3], [checkpoint], 30)
    assert level.check_colisions_with_cars((800, 450)) is False


def test_check_saefty_in_river_die():
    frog = Frog(5, 630, 250)
    car = Car(10, 200)
    log = Log(5, 200)
    ali = Alligator(5, 600)
    turtle = Turtle(5, 20)
    checkpoint = Checkpoint(100, 40)
    road_1 = Road(50, [car])
    road_2 = Road(100, [car])
    road_3 = Road(150, [car])
    river_1 = River(250, [ali], [log], [turtle])
    river_2 = River(300, [ali], [log], [turtle])
    river_3 = River(350, [ali], [log], [turtle])
    level = Level(frog, [road_1, road_2, road_3], [river_1, river_2, river_3], [checkpoint], 30)
    level.check_safety_in_river((800, 450))
    assert frog.lives() == 4
    assert frog.x() == 750
    assert frog.y() == 400


def test_check_saefty_in_river_safe():
    frog = Frog(5, 240, 250)
    car = Car(10, 200)
    log = Log(5, 200)
    ali = Alligator(5, 600)
    turtle = Turtle(5, 20)
    checkpoint = Checkpoint(100, 40)
    road_1 = Road(50, [car])
    road_2 = Road(100, [car])
    road_3 = Road(150, [car])
    river_1 = River(250, [ali], [log], [turtle])
    river_2 = River(300, [ali], [log], [turtle])
    river_3 = River(350, [ali], [log], [turtle])
    level = Level(frog, [road_1, road_2, road_3], [river_1, river_2, river_3], [checkpoint], 30)
    assert level.check_safety_in_river((800, 450)) is False
    assert frog.lives() == 5
    assert frog.x() == log.x() + log.lenght() / 2
    assert frog.y() == 250


def test_win_true():
    frog = Frog(5, 120, 0)
    checkpoint = Checkpoint(100, 40)
    level = Level(frog, [], [], [checkpoint], 30)
    assert level.check_win((800, 450), time.time()) == 600


def test_win_false():
    frog = Frog(5, 120, 100)
    checkpoint = Checkpoint(100, 40)
    level = Level(frog, [], [], [checkpoint], 30)
    assert level.check_win((800, 450), time.time()) == 0
