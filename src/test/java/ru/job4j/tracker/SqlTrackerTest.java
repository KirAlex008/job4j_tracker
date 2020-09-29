package ru.job4j.tracker;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name"));
            int result = tracker.findByName("name").size();
            assertThat(result, is(1));
        }
    }
    @Test
    public void replaceItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name");
            tracker.add(item);
            tracker.replace(item.getId(), new Item("name2"));
            String result = tracker.findById(item.getId()).getName();
            assertThat(result, is("name2"));
        }
    }

    @Test
    public void deleteItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name");
            tracker.add(item);
            tracker.delete(item.getId());
            Item result = tracker.findById(item.getId());
            assertThat(result, is(IsNull.nullValue()));
        }
    }

    @Test
    public void findAllItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("name1");
            tracker.add(item1);
            Item item2 = new Item("name2");
            tracker.add(item2);
            tracker.findAll();
            List<Item> expectedList = new ArrayList();
            expectedList.add(item1);
            expectedList.add(item2);
            List<Item> listUnderTest = tracker.findAll();
            Assert.assertThat(listUnderTest,
                    IsIterableContainingInOrder.contains(expectedList.toArray()));
        }
    }

    @Test
    public void findByNameItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("name1");
            tracker.add(item1);
            Item item2 = new Item("name1");
            tracker.add(item2);
            Item item3 = new Item("name3");
            tracker.add(item3);
            List<Item> expectedList = new ArrayList();
            expectedList.add(item1);
            expectedList.add(item2);
            List<Item> listUnderTest = tracker.findByName("name1");
            Assert.assertThat(listUnderTest,
                    IsIterableContainingInOrder.contains(expectedList.toArray()));
        }
        }

    @Test
    public void findByIdItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name");
            tracker.add(item);
            Item forTest = item;
            Item result = tracker.findById(item.getId());
            assertThat(result, is(forTest));
        }
    }
}