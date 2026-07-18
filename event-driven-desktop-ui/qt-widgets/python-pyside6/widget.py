# This Python file uses the following encoding: utf-8
import sys
from PySide6.QtWidgets import QApplication

from hello_world import MyWidget

if __name__ == "__main__":
    app = QApplication([])
    window = MyWidget()
    window.resize(800, 600)
    window.show()
    sys.exit(app.exec())
