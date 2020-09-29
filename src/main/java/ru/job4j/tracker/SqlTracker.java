package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

   /* @Override
    public Item add(Item item) {
        ResultSet rs;
        try (PreparedStatement st = cn.prepareStatement("insert into items(name) values(?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            while (rs.next()) {
                int id = rs.getInt(1);
                String stId = Integer.toString(id);
                item.setId(stId);
                System.out.println(item.getName() + " " + item.getId() + " TEST");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return item;
    }*/

    @Override
    public Item add(Item item) {
        try (final PreparedStatement statement = this.cn
                .prepareStatement("insert into items (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            //statement.setString(2, item.getDesc());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getString(1));
                    return item;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new user");
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean rsl = false;
        try (PreparedStatement st = cn.prepareStatement("update items as u set name=?  where u.id=?")) {
            st.setString(1, item.getName());
            st.setInt(2, Integer.parseInt(id));
            int repl = st.executeUpdate();
            if (repl > 0) {
                rsl = true;
            }
            } catch(Exception e){
                throw new IllegalStateException(e);
            }
            return rsl;
        }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        try (PreparedStatement st = cn.prepareStatement("delete from items where id=?")) {
            st.setInt(1, Integer.parseInt(id));
            int repl = st.executeUpdate();
            if (repl > 0) {
                rsl = true;
            }
        } catch(Exception e){
            throw new IllegalStateException(e);
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        ResultSet rs;
        try (Statement st = cn.createStatement()) {
            rs = st.executeQuery("SELECT * FROM items");
            while (rs.next()) {
                int id = rs.getInt(1);
                String stId = Integer.toString(id);
                String name = rs.getString(2);
                Item item = new Item(name);
                item.setId(stId);
                list.add(item);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement st = cn.prepareStatement("SELECT * FROM items where name=?")) {
            st.setString(1, key);
            rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String stId = Integer.toString(id);
                String name = rs.getString(2);
                Item item = new Item(name);
                item.setId(stId);
                list.add(item);
            }
        } catch(Exception e){
            throw new IllegalStateException(e);
        }
        return list;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        ResultSet rs;
        try (PreparedStatement st = cn.prepareStatement("SELECT * FROM items where id=?")) {
            st.setInt(1, Integer.parseInt(id));
            rs = st.executeQuery();
            while (rs.next()) {
                int idFromTab = rs.getInt(1);
                String stId = Integer.toString(idFromTab);
                String name = rs.getString(2);
                item = new Item(name);
                item.setId(stId);
            }
        } catch(Exception e){
            throw new IllegalStateException(e);
        }
        return item;
    }
}