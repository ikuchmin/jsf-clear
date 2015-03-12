package org.danilarefy.test.jsf.workplace.item;

import org.danilarefy.test.jsf.model.Item;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ItemController implements Serializable {

    @Inject
    private Item item;

    public Item getItem() {
        return item;
    }
}
