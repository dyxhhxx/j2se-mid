package JDBC;

import java.util.List;

public interface DAO {
    public void add(Hero hero);
    public void update(Hero hero);
    public void delete(Hero hero);
    public Hero get(int id);
    public List<Hero> list();
    public List<Hero> list(int start,int count);
}
