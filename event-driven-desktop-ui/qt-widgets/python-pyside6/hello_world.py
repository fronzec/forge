import random
from PySide6 import QtWidgets, QtCore


class MyWidget(QtWidgets.QWidget):
    """
    A simple GUI widget that displays a random greeting when a button is clicked.
    This file uses QT Widgets
    """

    def __init__(self):
        super().__init__()

        self.hello = ["Hallo Welt", "Hei maailma", "Hola Mundo", "Привет мир"]
        self.button = QtWidgets.QPushButton("Click me!")
        self.text = QtWidgets.QLabel("Hello World",
                                     alignment=QtCore.Qt.AlignmentFlag.AlignCenter)
        self.layout = QtWidgets.QVBoxLayout(self)
        self.layout.addWidget(self.text)
        self.layout.addWidget(self.button)

        self.button.clicked.connect(self.magic)

    @QtCore.Slot()
    def magic(self):
        """
        Randomly changes the displayed text when the button is clicked.
        """
        self.text.setText(random.choice(self.hello))
