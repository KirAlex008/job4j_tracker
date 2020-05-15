package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Указатель ячейки для новой заявки.
     */

    /**
     * Метод реализующий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        this.items.add(item);
        return item;
    }

    private int indexOf(String id) {
        int rsl = -1;
        for (Item element : items) {
            rsl++;
            if (element.getId().equals(id)) {
                break;
            }
        }
        return rsl;
    }

    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String key) {
        List<Item> forback = new ArrayList<>();
        for (Item element : items) {
            if ((element.getName() != null) && (element.getName().equals(key))) {
                forback.add(element);
            }
        }
        return forback;
    }


    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            item.setId(id);
            this.delete(id);
            items.add(index, item);
        }
        return rsl;
    }

    public boolean delete(String id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            items.remove(index);
        }
        return rsl;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

}
