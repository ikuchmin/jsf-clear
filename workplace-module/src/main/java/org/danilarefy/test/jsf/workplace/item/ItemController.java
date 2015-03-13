package org.danilarefy.test.jsf.workplace.item;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.danilarefy.test.jsf.model.Item;
import ru.osslabs.library.fias.persistence.dao.AddressObjectDao;
import ru.osslabs.library.fias.persistence.dao.GenericDao;
import ru.osslabs.library.fias.persistence.dao.PersistException;
import ru.osslabs.library.fias.persistence.dao.Socr;
import ru.osslabs.library.fias.persistence.dao.postgresql.PostgreSqlDaoFactory;
import ru.osslabs.library.fias.persistence.dao.sql.AbstractSqlDaoFactory;
import ru.osslabs.library.fias.persistence.domain.AddressObjectShort;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ItemController implements Serializable {

    AddressObjectDao<AddressObjectShort> dao;

    @Inject
    private Item item;

    public ItemController() {
        Properties props = new Properties();
        DataSource dataSource = new HikariDataSource(new HikariConfig(props));

        try {
            dao = new PostgreSqlDaoFactory<>(dataSource).getDao(AddressObjectShort.class);
        } catch (PersistException e) {
            e.printStackTrace();
        }

    }

    public Item getItem() {
        return item;
    }

    public List<String> complete(String query) {
        try {
            return dao.getObjectsByShortName(query, Socr.CITY, false)
                    .map(AddressObjectShort::getOffname).collect(Collectors.toList());
        } catch (PersistException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
