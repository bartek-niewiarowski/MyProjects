from .objects import Car, Log, Alligator, Turtle, Checkpoint
from .frog import Frog
from .tracks import Road, River
from .level import Level


'''
Klasa levelFactory odpowiada za inicjacje oraz przekazanie do funkcji Main,
obiektów poziomów koniecznych do przeprowadzenia rozgrywki.
'''


class levelFactory:

    def get_level():
        levels = [
            levelFactory.level_1(),
            levelFactory.level_2(),
            levelFactory.level_3(),
            levelFactory.level_4()
        ]
        levelFactory.load_images()
        return levels

    def load_images():
        Car.load_image(Car)
        Alligator.load_image(Alligator)
        Turtle.load_image(Turtle)
        Log.load_image(Log)
        Frog.load_image(Frog)

    def level_1():
        frog = Frog(5, 750, 400)

        car = Car(3, 20)
        car1 = Car(3, 230)
        car2 = Car(3, 400)
        car3 = Car(3, 600)

        log_1 = Log(2, 100)
        log_2 = Log(2, 300)
        turtle_1 = Turtle(2, 400)
        turtle_2 = Turtle(2, 450)
        turtle_3 = Turtle(2, 700)
        ali1 = Alligator(2, 600)

        car20 = Car(5, 100)
        car21 = Car(5, 360)
        car22 = Car(5, 420)
        car23 = Car(5, 700)

        log_21 = Log(4, 1)
        log_22 = Log(4, 500)
        turtle_21 = Turtle(4, 200)
        turtle_22 = Turtle(4, 250)
        ali2 = Alligator(4, 350)

        car30 = Car(3, 70)
        car31 = Car(3, 160)
        car32 = Car(3, 300)
        car33 = Car(3, 550)

        log_31 = Log(2, 200)
        log_32 = Log(2, 400)
        turtle_31 = Turtle(2, 40)
        turtle_32 = Turtle(2, 90)
        ali3 = Alligator(2, 700)
        ali31 = Alligator(2, 310)

        riv1 = River(50, [ali1], [log_1, log_2],
                     [turtle_1, turtle_2, turtle_3])
        riv2 = River(100, [ali2], [log_21, log_22],
                     [turtle_21, turtle_22])
        riv3 = River(150, [ali3, ali31], [log_31, log_32],
                     [turtle_31, turtle_32])
        road_1 = Road(350, [car, car1, car2, car3])
        road_2 = Road(300, [car20, car21, car22, car23])
        road_3 = Road(250, [car30, car31, car32, car33])

        checkpoint = Checkpoint(20, 40)
        checkpoint1 = Checkpoint(190, 40)
        checkpoint2 = Checkpoint(360, 40)
        checkpoint3 = Checkpoint(530, 40)
        checkpoint4 = Checkpoint(700, 40)
        checkpoints = [checkpoint, checkpoint1, checkpoint2,
                       checkpoint3, checkpoint4]
        level_1 = Level(frog, [road_1, road_2, road_3], [riv1, riv2, riv3],
                        checkpoints, 30)
        return level_1

    def level_2():
        frog2 = Frog(5, 750, 400)

        car2 = Car(5, 20)
        car21 = Car(5, 230)
        car22 = Car(5, 400)
        car23 = Car(5, 600)

        log_21 = Log(4, 100)
        log_22 = Log(4, 300)
        turtle_21 = Turtle(4, 400)
        turtle_22 = Turtle(4, 450)
        turtle_23 = Turtle(4, 700)
        ali21 = Alligator(4, 600)

        car220 = Car(7, 100)
        car221 = Car(7, 360)
        car222 = Car(7, 420)
        car223 = Car(7, 700)

        log_221 = Log(6, 1)
        log_222 = Log(6, 500)
        turtle_221 = Turtle(6, 200)
        turtle_222 = Turtle(6, 250)
        ali22 = Alligator(6, 350)

        car230 = Car(5, 70)
        car231 = Car(5, 160)
        car232 = Car(5, 300)
        car233 = Car(5, 550)

        log_231 = Log(4, 200)
        log_232 = Log(4, 400)
        turtle_231 = Turtle(4, 40)
        turtle_232 = Turtle(4, 90)
        ali23 = Alligator(4, 700)
        ali231 = Alligator(4, 310)

        riv21 = River(50, [ali21], [log_21, log_22], [turtle_21, turtle_22,
                      turtle_23])
        riv22 = River(100, [ali22], [log_221, log_222],
                      [turtle_221, turtle_222])
        riv23 = River(150, [ali23, ali231], [log_231, log_232],
                      [turtle_231, turtle_232])
        road_21 = Road(350, [car2, car21, car22, car23])
        road_22 = Road(300, [car220, car221, car222, car223])
        road_23 = Road(250, [car230, car231, car232, car233])

        checkpoint = Checkpoint(20, 40)
        checkpoint1 = Checkpoint(190, 40)
        checkpoint2 = Checkpoint(360, 40)
        checkpoint3 = Checkpoint(530, 40)
        checkpoint4 = Checkpoint(700, 40)
        checkpoints = [checkpoint, checkpoint1, checkpoint2,
                       checkpoint3, checkpoint4]

        level_2 = Level(frog2, [road_21, road_22, road_23],
                        [riv21, riv22, riv23], checkpoints, 30)
        return level_2

    def level_3():
        frog3 = Frog(5, 750, 400)

        car32 = Car(6, 20)
        car321 = Car(6, 230)
        car322 = Car(6, 400)
        car323 = Car(6, 600)

        log_321 = Log(5, 100)
        log_322 = Log(5, 300)
        turtle_321 = Turtle(5, 400)
        turtle_322 = Turtle(5, 450)
        turtle_323 = Turtle(5, 700)
        ali321 = Alligator(5, 600)

        car3220 = Car(8, 100)
        car3221 = Car(8, 360)
        car3222 = Car(8, 420)
        car3223 = Car(8, 700)

        log_3221 = Log(7, 1)
        log_3222 = Log(7, 500)
        turtle_3221 = Turtle(7, 200)
        turtle_3222 = Turtle(7, 250)
        ali322 = Alligator(7, 350)

        car3230 = Car(6, 70)
        car3231 = Car(6, 160)
        car3232 = Car(6, 300)
        car3233 = Car(6, 550)

        log_3231 = Log(5, 200)
        log_3232 = Log(5, 400)
        turtle_3231 = Turtle(5, 40)
        turtle_3232 = Turtle(5, 90)
        ali323 = Alligator(5, 700)
        ali3231 = Alligator(5, 310)

        riv321 = River(50, [ali321], [log_321, log_322],
                       [turtle_321, turtle_322, turtle_323])
        riv322 = River(100, [ali322], [log_3221, log_3222],
                       [turtle_3221, turtle_3222])
        riv323 = River(150, [ali323, ali3231], [log_3231, log_3232],
                       [turtle_3231, turtle_3232])
        road_321 = Road(350, [car32, car321, car322, car323])
        road_322 = Road(300, [car3220, car3221, car3222, car3223])
        road_323 = Road(250, [car3230, car3231, car3232, car3233])

        checkpoint = Checkpoint(20, 40)
        checkpoint1 = Checkpoint(190, 40)
        checkpoint2 = Checkpoint(360, 40)
        checkpoint3 = Checkpoint(530, 40)
        checkpoint4 = Checkpoint(700, 40)
        checkpoints = [checkpoint, checkpoint1, checkpoint2,
                       checkpoint3, checkpoint4]

        level_3 = Level(frog3, [road_321, road_322, road_323],
                        [riv321, riv322, riv323], checkpoints, 30)
        return level_3

    def level_4():
        frog4 = Frog(5, 750, 400)

        car432 = Car(8, 20)
        car4321 = Car(8, 230)
        car4322 = Car(8, 400)
        car4323 = Car(8, 600)

        log_4321 = Log(7, 100)
        log_4322 = Log(7, 300)
        turtle_4321 = Turtle(7, 400)
        turtle_4322 = Turtle(7, 450)
        turtle_4323 = Turtle(7, 700)
        ali4321 = Alligator(7, 600)

        car43220 = Car(10, 100)
        car43221 = Car(10, 360)
        car43222 = Car(10, 420)
        car43223 = Car(10, 700)

        log_43221 = Log(9, 1)
        log_43222 = Log(9, 500)
        turtle_43221 = Turtle(9, 200)
        turtle_43222 = Turtle(9, 250)
        ali4322 = Alligator(9, 350)

        car43230 = Car(8, 70)
        car43231 = Car(8, 160)
        car43232 = Car(8, 300)
        car43233 = Car(8, 550)

        log_43231 = Log(7, 200)
        log_43232 = Log(7, 400)
        turtle_43231 = Turtle(7, 40)
        turtle_43232 = Turtle(7, 90)
        ali4323 = Alligator(7, 700)
        ali43231 = Alligator(7, 310)

        riv4321 = River(50, [ali4321], [log_4321, log_4322],
                        [turtle_4321, turtle_4322, turtle_4323])
        riv4322 = River(100, [ali4322], [log_43221, log_43222],
                        [turtle_43221, turtle_43222])
        riv4323 = River(150, [ali4323, ali43231], [log_43231, log_43232],
                        [turtle_43231, turtle_43232])
        road_4321 = Road(350, [car432, car4321, car4322, car4323])
        road_4322 = Road(300, [car43220, car43221, car43222, car43223])
        road_4323 = Road(250, [car43230, car43231, car43232, car43233])

        checkpoint = Checkpoint(20, 40)
        checkpoint1 = Checkpoint(190, 40)
        checkpoint2 = Checkpoint(360, 40)
        checkpoint3 = Checkpoint(530, 40)
        checkpoint4 = Checkpoint(700, 40)
        checkpoints = [checkpoint, checkpoint1, checkpoint2, checkpoint3,
                       checkpoint4]

        level_4 = Level(frog4, [road_4321, road_4322, road_4323],
                        [riv4321, riv4322, riv4323], checkpoints, 30)
        return level_4
