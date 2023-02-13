from frogger_game.tracks import Road, River, Final_line
from frogger_game.objects import Checkpoint, Car, Turtle, Alligator, Log
from frogger_game.frog import Frog


def test_init_road():
    car = Car(10, 100)
    car1 = Car(10, 250)
    car2 = Car(10, 380)
    road = Road(50, [car, car1, car2])
    assert road.y() == 50
    assert len(road.cars()) == 3
    assert road.cars()[0] == car


def test_move_cars():
    car = Car(10, 100)
    car1 = Car(10, 250)
    car2 = Car(10, 380)
    road = Road(50, [car, car1, car2])
    road.move_cars(800)
    road.cars()[0].x() == 147


def test_scale_road():
    car = Car(10, 100)
    car1 = Car(10, 250)
    car2 = Car(10, 380)
    road = Road(50, [car, car1, car2])
    road.scale_road(2, 2)
    road.y() == 100


def test_check_safety_on_the_road_true():
    frog = Frog(5, 120, 50)
    car = Car(10, 100)
    car1 = Car(10, 250)
    car2 = Car(10, 380)
    road = Road(50, [car, car1, car2])
    assert road.check_safety_on_the_road(frog, 450) is True


def test_check_safety_on_the_road_false():
    frog = Frog(5, 170, 50)
    car = Car(10, 100)
    car1 = Car(10, 250)
    car2 = Car(10, 380)
    road = Road(50, [car, car1, car2])
    assert road.check_safety_on_the_road(frog, 450) is False


def test_init_river():
    ali = Alligator(2, 100)
    log = Log(2, 300)
    turtle = Turtle(2, 200)
    river = River(100, [ali], [log], [turtle])
    assert river.y() == 100
    assert len(river.alligators()) == 1
    assert len(river.turtles()) == 1
    assert len(river.logs()) == 1


def test_move_objects():
    ali = Alligator(2, 100)
    log = Log(2, 300)
    turtle = Turtle(2, 200)
    river = River(100, [ali], [log], [turtle])
    river.move_objects(800)
    assert river.alligators()[0].x() == 102


def test_scale_river():
    ali = Alligator(2, 100)
    log = Log(2, 300)
    turtle = Turtle(2, 200)
    river = River(100, [ali], [log], [turtle])
    river.scale_river(1.5, 1.5)
    assert river.y() == 150
    assert river.alligators()[0].x() == 150
    river.move_objects(800)
    assert river.alligators()[0].x() == 153


def test_check_safety_in_river_true():
    frog = Frog(5, 330, 100)
    ali = Alligator(2, 100)
    log = Log(2, 300)
    turtle = Turtle(2, 200)
    river = River(100, [ali], [log], [turtle])
    assert river.check_safety_in_river(frog, 450) == log


def test_check_safety_in_river_false():
    frog = Frog(5, 500, 100)
    ali = Alligator(2, 100)
    log = Log(2, 300)
    turtle = Turtle(2, 150)
    river = River(100, [ali], [log], [turtle])
    assert river.check_safety_in_river(frog, 450) is None


def test_init_final_line():
    checkpoint = Checkpoint(20, 40)
    final = Final_line(0, checkpoints=[checkpoint])
    assert final.y() == 0
    assert len(final.checkpoints()) == 1
