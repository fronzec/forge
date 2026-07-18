import QtQuick
import QtQuick.Controls 2.5
import QtQuick.Layouts 1.3

ApplicationWindow {
    width: 300
    height: 200
    visible: true
    title: qsTr("Hello World")
    color: "gray"

    readonly property list<string> texts: ["1", "2", "3", "4"]

    function setText(){
        var i = Math.round(Math.random() * 3)
        text.text = texts[i]
    }

    ColumnLayout {
        anchors.fill: parent

        Text {
            id: text
            text: qsTr("Hello World")
            Layout.alignment: Qt.AlignHCenter
        }
        Button {
            text: "Click me"
            Layout.alignment: Qt.AlignHCenter
            onClicked: setText()
        }
    }
}
