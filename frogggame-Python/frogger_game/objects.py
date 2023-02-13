import pygame
import os.path
from .config import std_car_width, std_alligator_width, std_turtle_width, std_log_width, standard_height

'''
Klasa Moveable
Są to wszystkie struktury ruchome, poruszające sie po
drogach i rzekach: auta, kłody, aligatory, żółwie
Atrybuty:
image - wczytana tekstura, wszystkie użyte tekstury
znajdują sie w folderze Textures
speed - szybkość obiketu
x - położenie obiektu, nie może być większy niż szerokość okna
'''


class Moveable:
    def __init__(self, speed, x):
        self._x = x
        self._speed = speed

    def speed(self):
        return self._speed

    def x(self):
        return self._x

    def lenght(self):
        return self._lenght

    def set_speed(self, new_speed):
        self._speed = new_speed

    def move(self, width):
        if self.x() >= width:
            self._x = 0
        self._x += self._speed

    def scale_object(self, width):
        self._speed = self._speed * width
        self._x = self._x * width
        self._lenght = self._lenght * width


'''
Klasa Car, dziedziczy po klasie Moveable, są to auta poruszające sie po drodze.
Kiedy frogger zderzy sie z obiektem klasy Car, wywyolywana
jest funkcja klasy frogger frog.die()
'''


class Car(Moveable):
    def __init__(self, speed, x):
        super().__init__(speed, x)
        self._lenght = std_car_width

    def load_image(cls):
        path = os.path.abspath('textures_and_game_data/car.png')
        carIMG = pygame.image.load(path)
        cls._image = carIMG

    def image(cls):
        return cls._image

    def scale_image(cls, width, hight):
        new_dimensions = (std_car_width*width, standard_height*hight)
        cls._image = pygame.transform.scale(cls._image, new_dimensions)


'''
Klasa Alligtor dziedziczy po klasie Moveable, są to aligatory
poruszające sie po rzece, alligatory zachowują sie jak woda, w momencie kolizji
z nimi na obiekcie frog, wywoływana jest funkcja die
'''


class Alligator(Moveable):
    def __init__(self, speed, x):
        super().__init__(speed, x)
        self._lenght = std_alligator_width

    def load_image(cls):
        path = os.path.abspath('textures_and_game_data/alligator.png')
        aligatorIMG = pygame.image.load(path)
        cls._image = aligatorIMG

    def image(cls):
        return cls._image

    def scale_image(cls, width, hight):
        new_dimensions = (std_alligator_width*width, standard_height*hight)
        cls._image = pygame.transform.scale(cls._image, new_dimensions)


'''
Klasy Log i Turtle dziedziczą po klasie Moveable, obiekty tych dwóch
klas umożliwiają przejście przez rzekę, w momencie kolizji obiketu klasy
frogger wywoływana jest funkcja move_with_object
'''


class Log(Moveable):
    def __init__(self, speed, x):
        super().__init__(speed, x)
        self._lenght = std_log_width

    def image(cls):
        return cls._image

    def load_image(cls):
        path = os.path.abspath('textures_and_game_data/log.png')
        logIMG = pygame.image.load(path)
        cls._image = logIMG

    def scale_image(cls, width, hight):
        new_dimensions = (std_log_width*width, standard_height*hight)
        cls._image = pygame.transform.scale(cls._image, new_dimensions)


class Turtle(Moveable):
    def __init__(self, speed, x):
        super().__init__(speed, x)
        self._lenght = std_turtle_width

    def image(cls):
        return cls._image

    def load_image(cls):
        path = os.path.abspath('textures_and_game_data/turtle.png')
        turtleIMG = pygame.image.load(path)
        cls._image = turtleIMG

    def scale_image(cls, width, hight):
        new_dimensions = (std_turtle_width*width, standard_height*hight)
        cls._image = pygame.transform.scale(cls._image, new_dimensions)


'''
Klasa Checkpoint, to miejsca na mapie do których mamy dojść
aby zapewnić bezpieczeństwo dla forggera,
w każdym poziomie wszystkie checkpointy znajdują się w tych samych miejscach.
'''


class Checkpoint:
    def __init__(self, x, lenght):
        self._x = x
        self._lenght = lenght

    def image(cls):
        return cls._image

    def x(self):
        return self._x

    def lenght(self):
        return self._lenght

    def set_x(self, new_x):
        self._x = new_x
