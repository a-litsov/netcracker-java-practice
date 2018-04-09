# MyLinkedList
**MyLinkedList** - реализация *односвязного* списка на Java.
## Производительность
### Добавление элемента в конец
![Append chart](https://raw.githubusercontent.com/alitsov/netcracker-java-practice/master/src/resources/MyLinkedList/AppendComparison.png)
### Поиск элемента по значению
![IndexOf chart](https://raw.githubusercontent.com/alitsov/netcracker-java-practice/master/src/resources/MyLinkedList/IndexOfComparison.png)
### Вставка элементов в случайную позицию
![Add chart](https://raw.githubusercontent.com/alitsov/netcracker-java-practice/master/src/resources/MyLinkedList/AddComparison.png)
### Удаление элементов по индексу
![Remove chart](https://raw.githubusercontent.com/alitsov/netcracker-java-practice/master/src/resources/MyLinkedList/RemoveComparison.png)

Из приведенных графиков видно, что в каждом случае порядок роста функций совпадает. Тем не менее, если в первых двух
случаях графики не сильно отличаются друг от друга, оставшиеся два графика наглядно показывают, в чём одно из преимуществ
двусвязных списков перед односвязными: зная индекс элемента, с которым будем работать, можно определить, в какой половине
списка он находится и начать проход этого списка с нужного конца. Поскольку все элементы выбирались случайно, становится
очевидным, почему разрыв во времени оказался примерно двукратным (в среднем, каждый второй элемент оказался в "дальней"
от начала списка половине).
