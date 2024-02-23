# Реализовать консольное приложение заметки, с сохранением, чтением, добавлением, редактированием и удалением заметок. Заметка должна содержать идентификатор, заголовок, тело заметки и дату/время создания или последнего изменения заметки. Сохранение заметок необходимо сделать в формате json или csv формат (разделение полей рекомендуется делать через точку с запятой). Реализацию пользовательского интерфейса студент может делать как ему удобнее, можно делать как параметры запуска программы (команда, данные), можно делать как запрос команды с консоли и последующим вводом данных, как-то ещё, на усмотрение студента
# При чтении списка заметок реализовать фильтрацию по дате.

import json
import datetime

# Функция для загрузки заметок из файла (если файл существует)
def load_notes():
    try:
        with open('notes.json', 'r') as file:
            notes = json.load(file)
    except FileNotFoundError:
        notes = []
    return notes

# Функция для сохранения заметок в файл
def save_notes(notes):
    with open('notes.json', 'w') as file:
        json.dump(notes, file, indent=4)

# Функция для добавления новой заметки
def add_note(notes, identifier, title, body):
    timestamp = str(datetime.datetime.now())
    notes.append({
        'id': identifier,
        'title': title,
        'body': body,
        'timestamp': timestamp
    })
    save_notes(notes)
    print('Заметка добавлена.')

# Функция для чтения заметок
def read_notes(notes, filter_date=None):
    if filter_date:
        filtered_notes = [note for note in notes if note['timestamp'].split()[0] == filter_date]
        if filtered_notes:
            for note in filtered_notes:
                print(f"ID: {note['id']}, Заголовок: {note['title']}, Дата/время: {note['timestamp']}")
                print(f"Тело заметки: {note['body']}\n")
        else:
            print('Нет заметок за указанную дату.')
    else:
        for note in notes:
            print(f"ID: {note['id']}, Заголовок: {note['title']}, Дата/время: {note['timestamp']}")
            print(f"Тело заметки: {note['body']}\n")

# Функция для редактирования заметки
def edit_note(notes, identifier, new_title, new_body):
    for note in notes:
        if note['id'] == identifier:
            note['title'] = new_title
            note['body'] = new_body
            note['timestamp'] = str(datetime.datetime.now())
            save_notes(notes)
            print('Заметка отредактирована.')
            return
    print('Заметка с указанным ID не найдена.')

# Функция для удаления заметки
def delete_note(notes, identifier):
    for note in notes:
        if note['id'] == identifier:
            notes.remove(note)
            save_notes(notes)
            print('Заметка удалена.')
            return
    print('Заметка с указанным ID не найдена.')

# Основной код
def main():
    notes = load_notes()
    while True:
        command = input('Введите команду (add/read/edit/delete/exit): ')
        if command == 'add':
            identifier = input('Введите идентификатор заметки: ')
            title = input('Введите заголовок заметки: ')
            body = input('Введите тело заметки: ')
            add_note(notes, identifier, title, body)
        elif command == 'read':
            filter_date = input('Введите дату для фильтрации (yyyy-mm-dd) или нажмите Enter для вывода всех заметок: ')
            read_notes(notes, filter_date)
        elif command == 'edit':
            identifier = input('Введите идентификатор заметки для редактирования: ')
            new_title = input('Введите новый заголовок заметки: ')
            new_body = input('Введите новое тело заметки: ')
            edit_note(notes, identifier, new_title, new_body)
        elif command == 'delete':
            identifier = input('Введите идентификатор заметки для удаления: ')
            delete_note(notes, identifier)
        elif command == 'exit':
            break
        else:
            print('Некорректная команда. Повторите ввод.')

if __name__ == '__main__':
    main()