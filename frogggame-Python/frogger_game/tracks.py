from .objects import Car, Log, Turtle, Alligator
from .config import const_division

"""
Klasa Road
Atrybuty:
Cars - auta poruszajace się po drodze, każde auto na wybranym pasie
porusza sie z tą samą predkoscia aby nie nastąpywało wyprzedzanie
y - wspolrzedna y drogi, kazda droga ma inna współrzedną y
"""


class Road:
    def __init__(self, y, cars):
        self._y = y
        if cars:
            self._cars = cars
        else:
            self._cars = []

    def y(self):
        return self._y

    def cars(self):
        return self._cars

    def move_cars(self, width):
        for car in self.cars():
            car.move(width)

    def draw_road(self, surface):
        for car in self.cars():
            x = car.x()
            y = self.y()
            surface.blit(Car.image(Car), (x, y))

    def scale_road(self, width, hight):
        self._y = self._y * hight
        for car in self.cars():
            car.scale_object(width)

    def check_safety_on_the_road(self, frog, hight):
        for car in self.cars():
            if frog.is_overlaping(car.x(), car.lenght(), self.y(), hight / const_division):
                return True
        return False


"""
Klasa River
Atrybuty:
Alligators, Logs, Turtles - elementy ruchome, poruszające się na jednym
pasie rzeki kazdy element porusza sie z ta sama szybkośćią
y - współrzędna y rzeki, każda rzeka ma inną współrzędną
"""


class River:
    def __init__(self, y, alligators, logs, turtles):
        self._y = y
        self._alligators = alligators
        self._logs = logs
        self._turtles = turtles

    def y(self):
        return self._y

    def alligators(self):
        return self._alligators

    def logs(self):
        return self._logs

    def turtles(self):
        return self._turtles

    def move_objects(self, width):
        for alligator in self.alligators():
            alligator.move(width)

        for log in self.logs():
            log.move(width)

        for turtle in self.turtles():
            turtle.move(width)

    def draw_river(self, surface):
        for aligator in self.alligators():
            x = aligator.x()
            y = self.y()
            surface.blit(Alligator.image(Alligator), (x, y))

        for log in self.logs():
            x = log.x()
            y = self.y()
            surface.blit(Log.image(Log), (x, y))

        for turtle in self.turtles():
            x = turtle.x()
            y = self.y()
            surface.blit(Turtle.image(Turtle), (x, y))

    def scale_river(self, width, hight):
        self._y = self._y * hight
        for aligator in self.alligators():
            aligator.scale_object(width)

        for log in self.logs():
            log.scale_object(width)

        for turtle in self.turtles():
            turtle.scale_object(width)

    def check_safety_in_river(self, frog, hight):
        for log in self.logs():
            if frog.is_overlaping(log.x(), log.lenght(), self.y(), hight / const_division):
                return log
        for turtle in self.turtles():
            x = turtle.x()
            len = turtle.lenght()
            if frog.is_overlaping(x, len, self.y(), hight / const_division):
                return turtle
        return None


"""
Klasa final_line
Składa się z checkpointów do których trzeba dotrzeć aby ukończyć poziom
Checkpointy znajdują sie w tych samych miejscach na mapie, zawsze jest ich 5,
jedynie współrzedne zmieniają sie pod wpływem skalowania mapy
"""


class Final_line:
    def __init__(self, y, checkpoints):
        self._y = y
        self._checkpoints = checkpoints

    def y(self):
        return self._y

    def checkpoints(self):
        return self._checkpoints
